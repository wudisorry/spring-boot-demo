package com.arh.springbootdemo.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author chenli
 * @Date 2020/6/11
 **/
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before:" + method.getName());
        System.out.println(o.getClass());
        Object returnObj = methodProxy.invokeSuper(o, objects);
        System.out.println("after:" + method.getName());
        return returnObj;
    }
}
