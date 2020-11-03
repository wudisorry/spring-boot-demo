package com.arh.springbootdemo.cglib;

import com.arh.springbootdemo.entity.User;

/**
 * @Description
 * @Author chenli
 * @Date 2020/6/11
 **/
public class TargetBean {

    private User user;

    private String myName;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMyName() {
        System.out.println("调用这方法的class：" + this.getClass());
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public void sayHello() {
        System.out.println("调用这方法的class：" + this.getClass());
        System.out.println("hello don!");
    }

    public final void finalSayHello() {
        System.out.println("final 调用这方法的class：" + this.getClass());
        System.out.println("final hello don!");
    }
}
