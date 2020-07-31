package com.arh.springbootdemo;

import com.arh.springbootdemo.dao.IUserRepository;
import com.arh.springbootdemo.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author chenli
 * @Date 2020/4/24
 **/
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private IUserRepository userRepository;

    @Test
    public void test() throws Exception {
        User user = new User();
        user.setName("tom");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        List<User> userList1 = new ArrayList<>(userList);
//        user.setBirthday();
//        userRepository.save()
    }
}
