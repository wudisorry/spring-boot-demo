package com.arh.springbootdemo.configuration;

/**
 * @Description
 * @Author chenli
 * @Date 2020/8/6
 **/
public class DynamicDataSourceContext {

    public static final String BUSINESS_KEY = "business";

    public static final String USER_KEY = "user";

    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setDataSourceKey(String key) {
        threadLocal.set(key);
    }

    public static String getDataSourceKey() {
        return threadLocal.get();
    }

    public static void clearDataSourceKey() {
        threadLocal.remove();
    }
}
