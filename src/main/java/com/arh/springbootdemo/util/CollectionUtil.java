package com.arh.springbootdemo.util;

import java.util.Map;

/**
 * @Description
 * @Author chenli
 * @Date 2019/10/30
 **/
public class CollectionUtil {

    public static boolean isEmpty(Map map) {
        if (map == null) {
            return true;
        }
        return map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return map != null && !map.isEmpty();
    }
}
