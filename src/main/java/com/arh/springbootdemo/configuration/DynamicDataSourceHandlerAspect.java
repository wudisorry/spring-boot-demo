package com.arh.springbootdemo.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Order(1)
public class DynamicDataSourceHandlerAspect {

    @Before("@annotation(com.arh.springbootdemo.configuration.DynamicDataSourceSwitch) || @within(com.arh.springbootdemo.configuration.DynamicDataSourceSwitch)")
    public void doDefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DynamicDataSourceSwitch annotationObj = method.getAnnotation(DynamicDataSourceSwitch.class);
        if (annotationObj == null) {
            annotationObj = joinPoint.getTarget().getClass().getAnnotation(DynamicDataSourceSwitch.class);
        }
        if (annotationObj == null) {
            return;
        }
        String key = annotationObj.dataSourceKey();
        DynamicDataSourceContext.setDataSourceKey(key);
    }

    @After("@annotation(com.arh.springbootdemo.configuration.DynamicDataSourceSwitch) || @within(com.arh.springbootdemo.configuration.DynamicDataSourceSwitch)")
    public void doAfter() {
        DynamicDataSourceContext.clearDataSourceKey();
    }
}
