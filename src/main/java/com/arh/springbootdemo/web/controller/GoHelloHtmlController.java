package com.arh.springbootdemo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author chenli
 * @Date 2019/10/28
 **/
@Controller
public class GoHelloHtmlController {

    private static final Logger logger = LoggerFactory.getLogger(GoHelloHtmlController.class);

    @RequestMapping("goHelloHtmlController")
    public String sayHello() {
        return "hello";
    }
}
