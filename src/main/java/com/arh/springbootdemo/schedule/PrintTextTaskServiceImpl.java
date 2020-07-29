package com.arh.springbootdemo.schedule;

import com.arh.springbootdemo.entity.ScheduleCron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/29
 **/
@Component
public class PrintTextTaskServiceImpl implements IMyTaskService {

    public static final Logger logger = LoggerFactory.getLogger(PrintTextTaskServiceImpl.class);

    @Override
    public void execute(ScheduleCron scheduleCron) {
        logger.debug(scheduleCron.getName());
        logger.debug(scheduleCron.getTaskExplain());
    }
}
