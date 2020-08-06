package com.arh.springbootdemo.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author chenli
 * @Date 2020/8/6
 **/
@Configuration
@ConditionalOnProperty(name = "spring.use.dynamic", havingValue = "true", matchIfMissing = false)
public class DynamicDataSourceConfig {

    @Bean("dataSource")
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object,Object> targetDataSources = new HashMap<>();

        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }
}
