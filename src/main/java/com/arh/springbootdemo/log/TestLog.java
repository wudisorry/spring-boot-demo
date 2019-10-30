package com.arh.springbootdemo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arh.springbootdemo.domain.Student;

/**
 * @Description
 * @Author chenli
 * @Date 2019/10/28
 **/
@Configuration
public class TestLog {

    private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    @Bean
    public Student test() {
        logger.debug("debug级别");
        logger.info("info级别");
        logger.warn("warn级别");
        logger.error("error级别");
        return new Student();
    }
}
