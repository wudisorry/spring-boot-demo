package com.arh.springbootdemo.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arh.springbootdemo.service.ISendRequestService;

/**
 * @Description
 * @Author chenli
 * @Date 2019/10/29
 **/

@RestController
public class TestSendRequestController {

    private static final Logger logger = LoggerFactory.getLogger(TestSendRequestController.class);

    @Autowired
    private ISendRequestService sendRequestService;

    @RequestMapping("/testSend")
    public String send() {
        try {
           
            sendRequestService.testGet();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
