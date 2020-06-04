package com.arh.springbootdemo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author chenli
 * @Date 2019/10/26
 **/

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/say")
    public String sayHello(@RequestParam(name = "name", defaultValue = "Don") String name) {
        logger.info("come in HelloController");
        return "hello," + name;
    }
}
