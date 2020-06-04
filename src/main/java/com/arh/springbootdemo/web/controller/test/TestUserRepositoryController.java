package com.arh.springbootdemo.web.controller.test;

import com.arh.springbootdemo.dao.IUserRepository;
import com.arh.springbootdemo.entity.User;
import com.arh.springbootdemo.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestUserRepositoryController {

    private static final Logger logger = LoggerFactory.getLogger(TestUserRepositoryController.class);

    @Autowired
    private IUserRepository userRepository;

    @RequestMapping("/addUser")
    public void sayHello() {
        try {
            User user = new User();
            user.setName("tom");
            user.setBirthday(DateUtil.parseDate("1998-02-02"));
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
