package com.arh.springbootdemo.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author chenli
 * @Date 2020/6/4
 **/
@Component
@ConfigurationProperties(prefix = "anno.configuration.properties")
public class PropertyBeanWithAnnoConfigP {
    private String testName;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
