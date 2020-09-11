package com.arh.springbootdemo.web.controller;

import com.arh.springbootdemo.service.IScheduleCronService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author chenli
 * @Date 2020/8/1
 **/
@RestController
@RequestMapping("/scheduleCron")
public class EditScheduleCronController {

    @Resource(name = "scheduleCronServiceImpl")
    private IScheduleCronService scheduleCronService;

    @RequestMapping("/add/{name}")
    public void addScheduleCron(@PathVariable("name") String name) {

        scheduleCronService.addDefaultScheduleCron();
    }
}
