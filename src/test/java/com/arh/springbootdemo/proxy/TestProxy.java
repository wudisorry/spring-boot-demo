package com.arh.springbootdemo.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class TestProxy {

    @Test
    public void test() {
        Class<TargetInterface> clazz = TargetInterface.class;
        TargetInterface targetInterface = (TargetInterface) Proxy.newProxyInstance(MyProxyHandler.class.getClassLoader(), new Class[]{clazz}, new MyProxyHandler());
        targetInterface.showName();
    }
}
