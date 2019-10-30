package com.arh.springbootdemo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arh.springbootdemo.util.PropertyUtil;
import com.arh.springbootdemo.util.SomeProperties;

/**
 * @Description
 * @Author chenli
 * @Date 2019/10/26
 **/

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private PropertyUtil propertyUtil;

    @Autowired
    private SomeProperties someProperties;

    @RequestMapping("/say")
    public String sayHello(@RequestParam(name = "name", defaultValue = "Don") String name) {
        System.out.println(propertyUtil.getName());
        System.out.println(someProperties.getSomeName());
        logger.info("come in HelloController");
        return "hello," + name;
    }
}
