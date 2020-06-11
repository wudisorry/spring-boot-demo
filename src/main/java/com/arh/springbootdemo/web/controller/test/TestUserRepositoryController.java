package com.arh.springbootdemo.web.controller.test;

import com.arh.springbootdemo.dao.IUserRepository;
import com.arh.springbootdemo.entity.User;
import com.arh.springbootdemo.service.IUserService;
import com.arh.springbootdemo.util.ApplicationContextUtil;
import com.arh.springbootdemo.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/testUser")
public class TestUserRepositoryController {

    private static final Logger logger = LoggerFactory.getLogger(TestUserRepositoryController.class);

    @Autowired
    private IUserRepository userRepository;

    @Resource(name = "userServiceImplWithSubmitManually")
    private IUserService userServiceWithSubmitManually;

    @Resource(name = "userServiceImpl")
    private IUserService userService;

    @RequestMapping("/addByRepo")
    public void testAddUserByRepo() {
        try {
            User user = new User();
            user.setName("tom");
            user.setBirthday(DateUtil.parseDate("1998-02-02"));
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/addByManual")
    public void testAddUserByManual() {
        try {
            User user = new User();
            user.setId(200);
            user.setName("tom");
            user.setBirthday(DateUtil.parseDate("1998-02-02"));
            userServiceWithSubmitManually.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/add")
    public void testAddUser() {
        ApplicationContextUtil.getAc().getBean("userServiceImpl");
        try {
            User user = new User();
            user.setName("tom");
            user.setBirthday(DateUtil.parseDate("1998-02-02"));
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
