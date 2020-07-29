package com.arh.springbootdemo;

import com.arh.springbootdemo.listener.MyApplicationEnvPreparedEventListener;
import com.arh.springbootdemo.util.ApplicationContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        //极简启动
        //ApplicationContext ac = SpringApplication.run(SpringBootDemoApplication.class, args);

        SpringApplication springApplication = new SpringApplication(SpringBootDemoApplication.class);
        //使用SpringApplication对象，比如注册监听器
        springApplication.addListeners(new MyApplicationEnvPreparedEventListener());
        ApplicationContext ac = springApplication.run(args);
        ApplicationContextUtil.setAc(ac);
    }

}
