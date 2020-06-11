package com.arh.springbootdemo.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * @Description
 * @Author chenli
 * @Date 2020/6/11
 **/
public class TestCGLib {

    @Test
    public void test(){
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Chen_code\\spring-boot-demo\\src\\main\\java\\com\\arh\\springbootdemo\\cglib");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetBean.class);
        enhancer.setCallback(new MyMethodInterceptor());
        TargetBean proxyBean = (TargetBean) enhancer.create();
        proxyBean.sayHello();
        proxyBean.finalSayHello();
        proxyBean.getMyName();
    }
}
