package com.arh.springbootdemo.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author chenli
 * @Date 2019/10/28
 **/
@Component
@ConfigurationProperties(prefix = "don")
public class SomeProperties {

    private String someName;

    public String getSomeName() {
        return someName;
    }

    public void setSomeName(String someName) {
        this.someName = someName;
    }
}
