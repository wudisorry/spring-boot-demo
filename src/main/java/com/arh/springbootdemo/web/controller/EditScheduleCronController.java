package com.arh.springbootdemo.web.controller;

import com.arh.springbootdemo.dao.IScheduleCronRepository;
import com.arh.springbootdemo.entity.ScheduleCron;
import com.arh.springbootdemo.schedule.PrintTextTaskServiceImpl;
import com.arh.springbootdemo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author chenli
 * @Date 2020/8/1
 **/
@RestController
@RequestMapping("/scheduleCron")
public class EditScheduleCronController {

    @Autowired
    private IScheduleCronRepository scheduleCronRepository;

    @RequestMapping("/add/{name}")
    public void addScheduleCron(@PathVariable("name") String name) {

        ScheduleCron scheduleCron = new ScheduleCron();
        scheduleCron.setName(name);

        scheduleCron.setCronExpression("*/10 * * * * ?");

        scheduleCron.setClassName(PrintTextTaskServiceImpl.class.getName());
        scheduleCronRepository.save(scheduleCron);
    }
}
