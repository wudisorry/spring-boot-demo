package com.arh.springbootdemo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description
 * @Author chenli
 * @Date 2020/8/6
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String key = DynamicDataSourceContext.getDataSourceKey();
        logger.debug("当前线程切换的数据源key：{}", key);
        return key;
    }
}
