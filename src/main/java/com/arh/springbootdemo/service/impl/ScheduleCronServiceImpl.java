package com.arh.springbootdemo.service.impl;

import com.arh.springbootdemo.dao.IScheduleCronRepository;
import com.arh.springbootdemo.entity.ScheduleCron;
import com.arh.springbootdemo.schedule.PrintTextTaskServiceImpl;
import com.arh.springbootdemo.service.IScheduleCronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("scheduleCronServiceImpl")
@Transactional
public class ScheduleCronServiceImpl implements IScheduleCronService {

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
