package com.arh.springbootdemo.schedule;

import com.arh.springbootdemo.dao.IScheduleCronRepository;
import com.arh.springbootdemo.entity.ScheduleCron;
import com.arh.springbootdemo.service.IScheduleCronService;
import com.arh.springbootdemo.util.ApplicationContextUtil;
import com.arh.springbootdemo.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * @Description See @EnableScheduling for detailed usage examples
 * @Author chenli
 * @Date 2020/7/29
 **/
//@Configuration
public class DynamicScheduleConfig implements SchedulingConfigurer {

    public static final Logger logger = LoggerFactory.getLogger(DynamicScheduleConfig.class);

    @Resource(name = "scheduleCronServiceImpl")
    private IScheduleCronService scheduleCronService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        List<ScheduleCron> scheduleCronList = scheduleCronService.queryActiveScheduleCron();
        if (CollectionUtil.isNotEmpty(scheduleCronList)) {
            taskRegistrar.setScheduler(taskScheduler());
            for (ScheduleCron scheduleCron : scheduleCronList) {
                try {
                    Class c = Class.forName(scheduleCron.getClassName());
                    Object obj = applicationContext.getBean(c);
                    if (obj instanceof IMyTaskService) {
                        IMyTaskService taskService = (IMyTaskService) obj;
                        Runnable runnable = () -> taskService.execute(scheduleCron);
                        Trigger trigger = triggerContext -> {
                            CronTrigger cronTrigger = new CronTrigger(scheduleCron.getCronExpression());
                            return cronTrigger.nextExecutionTime(triggerContext);
                        };
                        taskRegistrar.addTriggerTask(runnable, trigger);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    scheduleCron.setStatus(1);
                    scheduleCron.setExceptionMsg(e.getMessage());
                    scheduleCronService.saveOrUpdateScheduleCron(scheduleCron);
                }


            }


        }

    }

    //@Bean(destroyMethod = "shutdown")
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(42);
    }
}
