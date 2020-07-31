package com.arh.springbootdemo.listener;

import com.arh.springbootdemo.util.PropertyBeanWithAnnoConfigP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MyContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    public static final Logger logger = LoggerFactory.getLogger(MyContextRefreshedEventListener.class);

    @Autowired
    private PropertyBeanWithAnnoConfigP p;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.debug("Spring应用上下文中的Bean均已完成初始化");
        logger.debug(p.getTestName());
    }
}
