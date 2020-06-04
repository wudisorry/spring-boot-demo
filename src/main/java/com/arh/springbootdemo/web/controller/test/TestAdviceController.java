package com.arh.springbootdemo.web.controller.test;

import com.arh.springbootdemo.service.IUseAdviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author chenli
 * @Date 2020/6/4
 **/
@Controller
@RequestMapping("/test")
public class TestAdviceController {

    private static final Logger logger = LoggerFactory.getLogger(TestAdviceController.class);

    @Autowired
    private IUseAdviceService service;

    @RequestMapping("/advice")
    public void testAdvice() {
        service.sayHello();
    }
}
