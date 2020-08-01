package com.arh.springbootdemo.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author chenli
 * @Date 2020/4/25
 **/
public class StringUtil extends StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String deleteWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            int sz = str.length();
            char[] chs = new char[sz];
            int count = 0;

            for (int i = 0; i < sz; ++i) {
                char ch = str.charAt(i);
                if (!Character.isWhitespace(ch) && ch != '\t') {
                    chs[count++] = ch;
                }
            }

            if (count == sz) {
                return str;
            } else {
                return new String(chs, 0, count);
            }
        }
    }

    public static List<String> getAtUser(String str) {
        Pattern p = Pattern.compile("(?<=@).*?(?= )");
        Matcher m = p.matcher(str);
        List<String> result = new ArrayList<>();
        while (m.find()) {
            if (StringUtils.isNoneBlank(m.group().trim())) {
                result.add(m.group().trim());
            }
        }
        return result;
    }

    public static boolean hasLength(String str) {
        return str != null && str.length() > 0;
    }
}
