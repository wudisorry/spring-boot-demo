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
            //sendRequestService.singleDoGet("https://www.baidu.com/", null);
            Map<String, String> map = new HashMap<>();
//            map.put("remremberMeInput", "true");
//            map.put("timeSign", "2019-10-30 17:14:09");
//            map.put("corpCode", "dayeesz");
//            map.put("userName", "superdm");
//            map.put("password", "6056F1C687624020EF034EB04C97E24C");
//            map.put("remremberMe", "true");
//            sendRequestService.singleDoPost("http://current.wintalent.cn/wt/login", map);
//            sendRequestService.singleDoGet("https://www.baidu.com/", map);
            sendRequestService.testGet();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
