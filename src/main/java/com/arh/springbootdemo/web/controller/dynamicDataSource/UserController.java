package com.arh.springbootdemo.web.controller.dynamicDataSource;

import com.arh.springbootdemo.entity.User;
import com.arh.springbootdemo.service.IUserService;
import com.arh.springbootdemo.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 验证：
 * Conditional类注解对Component类注解是否有效
 * 切换数据源是否成功
 */
@ConditionalOnProperty(name = "use.dynamicDataSource", havingValue = "true", matchIfMissing = false)
@RestController
@RequestMapping("/dynamicDataSource/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userServiceDynamicSourceImpl")
    private IUserService userServiceDynamicImpl;

//    @Resource(name = "userServiceImpl")
//    private IUserService userServiceImpl;


    @RequestMapping("/add")
    public String addUser() throws Exception {
        //logger.debug("userServiceImpl是否为空：" + userServiceImpl == null ? "true" : "false");
        User user = new User();
        user.setName("tom");
        user.setBirthday(DateUtil.parseDate("1998-02-02"));
        user.setEmail("123321@qq.com");
        user.setInfo("没什么其他信息了！！！");
        userServiceDynamicImpl.addUser(user);
        return "success";
    }
}
