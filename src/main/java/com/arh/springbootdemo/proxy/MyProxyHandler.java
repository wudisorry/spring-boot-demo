package com.arh.springbootdemo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxyHandler implements InvocationHandler {

    private Object target;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理----start----");
        //proxy类型是com.sun.proxy.$Proxy
        System.out.println(method);
        //method.invoke(target,args);
        System.out.println("jdk动态代理----end----");
        return null;
    }
}
