package com.arh.springbootdemo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Description 不能使用@Component，因为此事件发布阶段Spring容器还没开始运行
 * @Author chenli
 * @Date 2020/7/29
 **/
public class MyApplicationEnvPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MyApplicationEnvPreparedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        logger.debug("监听到ApplicationEnvironmentPreparedEvent事件");
    }
}
