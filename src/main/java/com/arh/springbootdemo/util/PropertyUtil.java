package com.arh.springbootdemo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author chenli
 * @Date 2019/10/26
 **/

@Component
public class PropertyUtil {

    @Value("${com.arh.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
