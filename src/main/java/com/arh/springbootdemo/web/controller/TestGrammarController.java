package com.arh.springbootdemo.web.controller;

import com.arh.springbootdemo.util.ApplicationContextUtil;
import com.arh.springbootdemo.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author chenli
 * @Date 2020/3/30
 **/
@RestController
public class TestGrammarController {

    private static final Logger logger = LoggerFactory.getLogger(TestGrammarController.class);



    @RequestMapping("/tg")
    public void tg(){
        logger.debug("start test property");
        PropertyUtil propertyUtil1 = new PropertyUtil();
        logger.debug(propertyUtil1.getName());
        PropertyUtil propertyUtil2 = (PropertyUtil) ApplicationContextUtil.getAc().getBean("propertyUtil");
        logger.debug(propertyUtil2.getName());
    }

}
