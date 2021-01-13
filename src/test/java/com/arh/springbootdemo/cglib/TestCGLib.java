package com.arh.springbootdemo.cglib;

import com.arh.springbootdemo.entity.User;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author chenli
 * @Date 2020/6/11
 **/
public class TestCGLib {

    @Test
    public void test() {
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Chen_code\\spring-boot-demo\\src\\main\\java\\com\\arh\\springbootdemo\\cglib");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetBean.class);
        enhancer.setCallback(new MyMethodInterceptor());
        TargetBean proxyBean = (TargetBean) enhancer.create();
        proxyBean.sayHello();
        proxyBean.finalSayHello();
        proxyBean.getMyName();
    }

    @Test
    public void testClass() throws Exception{
        Class<User> clazz = User.class;
        Method method = clazz.getMethod("getName");
        System.out.println(method.getDeclaringClass());
    }
}
