package com.arh.springbootdemo.service;

import com.arh.springbootdemo.entity.User;

/**
 * @Description
 * @Author chenli
 * @Date 2020/6/10
 **/
public interface IUserService {

    void addUser(User user);

    User queryUserById(Integer id);
}
