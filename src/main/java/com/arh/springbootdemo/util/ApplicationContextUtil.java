package com.arh.springbootdemo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @Description
 * @Author chenli
 * @Date 2020/3/30
 **/
public class ApplicationContextUtil {

    public static ApplicationContext ac;

    public static ApplicationContext getAc() {
        return ac;
    }

    public static void setAc(ApplicationContext ac) {
        ApplicationContextUtil.ac = ac;
    }
}
