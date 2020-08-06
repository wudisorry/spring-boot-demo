package com.arh.springbootdemo.configuration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description
 * @Author chenli
 * @Date 2020/8/6
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }
}
