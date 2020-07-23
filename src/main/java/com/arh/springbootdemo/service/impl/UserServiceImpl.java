package com.arh.springbootdemo.service.impl;

import com.arh.springbootdemo.dao.IUserRepository;
import com.arh.springbootdemo.entity.User;
import com.arh.springbootdemo.exception.MyServiceException;
import com.arh.springbootdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * @Description
 * @Author chenli
 * @Date 2020/6/11
 **/

@Transactional
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    public UserServiceImpl() {
        System.out.println("实例化了！！");
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
        //throw new MyServiceException("就是想报错");
    }

    @Override
    public User queryUserById(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        User u = byId.orElse(new User());
        return u;
    }
}
