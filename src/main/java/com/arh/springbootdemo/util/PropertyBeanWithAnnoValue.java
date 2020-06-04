package com.arh.springbootdemo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author chenli
 * @Date 2020/6/4
 **/
@Component
@PropertySource("classpath:msg.properties")
public class PropertyBeanWithAnnoValue {

    @Value("${com.arh.propertyBeanWithAnnoValue.testName}")
    private String testName;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
