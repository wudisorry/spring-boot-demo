package com.arh.springbootdemo.log;

import com.arh.springbootdemo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description
 * @Author chenli
 * @Date 2019/10/28
 **/
@Configuration
public class TestLog {

    private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    @Bean
    public User test() {
        logger.debug("debug级别");
        logger.info("info级别");
        logger.warn("warn级别");
        logger.error("error级别");
        return new User();
    }
}
