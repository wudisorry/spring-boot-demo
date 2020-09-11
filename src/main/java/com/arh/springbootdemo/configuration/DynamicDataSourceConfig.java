package com.arh.springbootdemo.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author chenli
 * @Date 2020/8/6
 **/
@Configuration
@ConditionalOnProperty(name = "use.dynamicDataSource", havingValue = "true", matchIfMissing = false)
public class DynamicDataSourceConfig {

    @Autowired
    private DruidDataSourceProperties druidDataSourceProperties;

    @Value("${datasource.business.username}")
    private String businessUserName;

    @Value("${datasource.business.password}")
    private String businessPassword;

    @Value("${datasource.business.url}")
    private String businessUrl;

    @Value("${datasource.user.username}")
    private String userUserName;

    @Value("${datasource.user.password}")
    private String userPassword;

    @Value("${datasource.user.url}")
    private String userUrl;

    //在注解Bean中指定名字，默认使用方法名的
    @Bean("dataSource")
    public DynamicDataSource dynamicDataSource() throws SQLException {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map<String, Object> businessDataSourceMap = new HashMap<>();
        businessDataSourceMap.put("username", businessUserName);
        businessDataSourceMap.put("password", businessPassword);
        businessDataSourceMap.put("url", businessUrl);
        DataSource businessDataSource = buildDataSource(businessDataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(businessDataSource);

        Map<String, Object> userDataSourceMap = new HashMap<>();
        userDataSourceMap.put("username", userUserName);
        userDataSourceMap.put("password", userPassword);
        userDataSourceMap.put("url", userUrl);
        DataSource userDataSource = buildDataSource(userDataSourceMap);

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceContext.BUSINESS_KEY, businessDataSource);
        targetDataSources.put(DynamicDataSourceContext.USER_KEY, userDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    private DataSource buildDataSource(Map<String, Object> dataSourceMap) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        String userName = (String) dataSourceMap.get("username");
        String password = (String) dataSourceMap.get("password");
        String url = (String) dataSourceMap.get("url");

        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(druidDataSourceProperties.getDriverClassName());
        druidDataSource.setInitialSize(druidDataSourceProperties.getInitialSize());
        druidDataSource.setMinIdle(druidDataSourceProperties.getMinIdle());
        druidDataSource.setMaxActive(druidDataSourceProperties.getMaxActive());
        druidDataSource.setMaxWait(druidDataSourceProperties.getMaxWait());
        druidDataSource.setValidationQuery(druidDataSourceProperties.getValidationQuery());
        druidDataSource.setFilters(druidDataSourceProperties.getFilters());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setUseGlobalDataSourceStat(druidDataSourceProperties.isUseGlobalDataSourceStat());
        druidDataSource.setConnectionProperties(druidDataSourceProperties.getConnectionProperties());

        return druidDataSource;
    }


}
