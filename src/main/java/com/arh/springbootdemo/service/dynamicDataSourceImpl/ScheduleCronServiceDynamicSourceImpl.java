package com.arh.springbootdemo.service.dynamicDataSourceImpl;

import com.arh.springbootdemo.configuration.DynamicDataSourceContext;
import com.arh.springbootdemo.configuration.DynamicDataSourceSwitch;
import com.arh.springbootdemo.dao.IScheduleCronRepository;
import com.arh.springbootdemo.entity.ScheduleCron;
import com.arh.springbootdemo.schedule.PrintTextTaskServiceImpl;
import com.arh.springbootdemo.service.IScheduleCronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ConditionalOnProperty(name = "use.dynamicDataSource", havingValue = "true", matchIfMissing = false)
@DynamicDataSourceSwitch(dataSourceKey = DynamicDataSourceContext.BUSINESS_KEY)
@Transactional
public class ScheduleCronServiceDynamicSourceImpl implements IScheduleCronService {

    @Autowired
    private IScheduleCronRepository scheduleCronRepository;

    @Override
    public List<ScheduleCron> queryActiveScheduleCron() {
        return scheduleCronRepository.findByStatusOrStatusNull(0);
    }

    @Override
    public void addDefaultScheduleCron() {
        ScheduleCron scheduleCron = new ScheduleCron();
        scheduleCron.setName("defaultName");
        scheduleCron.setCronExpression("*/10 * * * * ?");
        scheduleCron.setClassName(PrintTextTaskServiceImpl.class.getName());
        scheduleCronRepository.save(scheduleCron);
    }

    @Override
    public void saveOrUpdateScheduleCron(ScheduleCron scheduleCron) {
        scheduleCronRepository.save(scheduleCron);
    }
}
