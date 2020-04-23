package com.arh.springbootdemo.domain;

import java.io.Serializable;

/**
 * @Description
 * @Author chenli
 * @Date 2019/10/28
 **/

public class Student implements Serializable {
    private Integer id;

    private String name;

    private String info;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
