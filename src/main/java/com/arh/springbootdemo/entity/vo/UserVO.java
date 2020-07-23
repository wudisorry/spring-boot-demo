package com.arh.springbootdemo.entity.vo;

import com.arh.springbootdemo.entity.User;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author chenli
 * @Date 2020/7/23
 **/
public class UserVO implements Serializable {

    private Integer id;

    private String name;

    private Date birthday;

    private String email;

    private String info;

    public UserVO() {
    }

    public UserVO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.birthday = user.getBirthday();
        this.email = user.getEmail();
        this.info = user.getInfo();
    }

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
