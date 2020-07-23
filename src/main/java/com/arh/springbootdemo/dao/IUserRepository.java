package com.arh.springbootdemo.dao;


import com.arh.springbootdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * @Description
 * @Author chenli
 * @Date 2020/4/24
 **/
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);

    User findByNameOrBirthday(String name, Date birthday);
}
