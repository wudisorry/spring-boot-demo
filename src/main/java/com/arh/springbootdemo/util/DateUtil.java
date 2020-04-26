package com.arh.springbootdemo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author chenli
 * @Date 2020/4/25
 **/
public class DateUtil {

    public static final String[] CHINESE_YMD_STR = new String[]{"年", "月", "日", "－", "、"};
    public static final String[] CHINESE_YMD_REPLACE_CHAR = new String[]{"-", "-", "-", "-", "-"};
    public static final String[] ENGLISH_M_STR = new String[]{"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december", "jan.", "feb.", "mar.", "apr.", "jun.", "jul.", "aug.", "sep.", "oct.", "nov.", "dec.", "jan", "feb", "mar", "apr", "jun", "jul", "aug", "sep", "oct", "nov", "dec", "sept"};
    public static final String[] ENGLISH_M_REPLACE_CHAR = new String[]{"-01-", "-02-", "-03-", "-04-", "-05-", "-06-", "-07-", "-08-", "-09-", "-10-", "-11-", "-12-", "-01-", "-02-", "-03-", "-04-", "-06-", "-07-", "-08-", "-09-", "-10-", "-11-", "-12-", "-01-", "-02-", "-03-", "-04-", "-06-", "-07-", "-08-", "-09-", "-10-", "-11-", "-12-", "-09-"};

    public static final Pattern NOW_DATE_PATTERN = Pattern.compile("现\\s*在|至\\s*今|当\\s*前|目\\s*前|今天?|Present|Today|Now|So[ ]Far", 2);

    public static final Pattern DATE_PATTERN_1 = Pattern.compile("(19|20)\\d{2}\\s*年?\\s*(\\p{Punct})\\s*(1[0-2]|0?[1-9])\\s*月?\\s*\\2\\s*(3[0-1]|[1-2][0-9]|0?[1-9])\\s*日?");
    public static final Pattern DATE_PATTERN_2 = Pattern.compile("(19|20)\\d{2}\\s*年\\s*(1[0-2]|0?[1-9])\\s*月\\s*(3[0-1]|[1-2][0-9]|0?[1-9])\\s*日?");
    public static final Pattern DATE_PATTERN_3 = Pattern.compile("(19|20)\\d{2}\\s*年?\\s*\\p{Punct}\\s*(1[0-2]|0?[1-9])\\s*月?");
    public static final Pattern DATE_PATTERN_4 = Pattern.compile("((19|20)\\d{2}\\s*年\\s*(1[0-2]|0?[1-9])\\s*月?)");
    public static final Pattern DATE_PATTERN_5 = Pattern.compile("(19|20)\\d{2}\\s*年?");
    public static final Pattern DATE_PATTERN_6 = Pattern.compile("(3[0-1]|[1-2][0-9]|0?[1-9])\\s*(\\p{Punct})\\s*(1[0-2]|0?[1-9])\\s*\\2\\s*(19|20)\\d{2}");
    public static final Pattern DATE_PATTERN_7 = Pattern.compile("(1[0-2]|0?[1-9])\\s*\\p{Punct}\\s*(19|20)\\d{2}");
    public static final Pattern DATE_PATTERN_8 = Pattern.compile("(3[0-1]|[1-2][0-9]|0?[1-9])\\s+(January|February|March|April|May|June|July|August|September|October|November|December|(Jan|Feb|Mar|Apr|Jun|Jul|Aug|Sep|Oct|Nov|Dec|Sept)\\.?)\\s+(19|20)\\d{2}");
    public static final Pattern DATE_PATTERN_9 = Pattern.compile("(3[0-1]|[1-2][0-9]|0?[1-9])\\s*(\\p{Punct})\\s*(January|February|March|April|May|June|July|August|September|October|November|December|(Jan|Feb|Mar|Apr|Jun|Jul|Aug|Sep|Oct|Nov|Dec|Sept)\\.?)\\s*\\2\\s*(19|20)\\d{2}");
    public static final Pattern DATE_PATTERN_10 = Pattern.compile("(January|February|March|April|May|June|July|August|September|October|November|December|(Jan|Feb|Mar|Apr|Jun|Jul|Aug|Sep|Oct|Nov|Dec|Sept)\\.?)\\s+([Oo][Ff]\\s+)?(19|20)\\d{2}");
    public static final Pattern DATE_PATTERN_11 = Pattern.compile("(January|February|March|April|May|June|July|August|September|October|November|December|(Jan|Feb|Mar|Apr|Jun|Jul|Aug|Sep|Oct|Nov|Dec|Sept)\\.?)\\s*\\p{Punct}\\s*(19|20)\\d{2}");
    public static final Pattern DATE_PATTERN_12 = Pattern.compile("(\\d{2})\\s*\\p{Punct}\\s*(1[0-2]|0?[1-9])");
    public static final Pattern DATE_PATTERN_13 = Pattern.compile("(19|20)\\d{2}\\s*年?\\s*(\\p{Punct})\\s*(1[0-2]|0?[1-9])\\s*月?\\s*\\2\\s*(3[0-1]|[1-2][0-9]|0?[1-9])\\s*日?\\s+\\d{2}\\p{Punct}\\d{2}");
    public static final Pattern DATE_PATTERN_14 = Pattern.compile("(19|20)\\d{2}\\s*年?\\s*(\\p{Punct})\\s*(1[0-2]|0?[1-9])\\s*月?\\s*\\2\\s*(3[0-1]|[1-2][0-9]|0?[1-9])\\s*日?\\s+\\d{2}\\p{Punct}\\d{2}\\p{Punct}\\d{2}");
    public static final Pattern DATE_PATTERN_15 = Pattern.compile("(19|20)\\d{2}\\s*年?\\s*(\\p{Punct})\\s*(1[0-2]|0?[1-9])\\s*月?\\s*\\2\\s*(3[0-1]|[1-2][0-9]|0?[1-9])\\s*日?\\s+\\d{2}\\p{Punct}\\d{2}\\p{Punct}\\d{2}\\p{Punct}\\d{1}");
    public static final Pattern DATE_PATTERN_16 = Pattern.compile("(19|20)\\d{2}(1[0-2]|0?[1-9])");
    public static final Pattern DATE_PATTERN_17 = Pattern.compile("(19|20)\\d{2}(1[0-2]|0?[1-9])(3[0-1]|[1-2][0-9]|0?[1-9])");

    public static int getAge(Date birthday) {

        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow < monthBirth) {
            age--;
        } else if (monthNow == monthBirth && dayOfMonthNow < dayOfMonthBirth) {
            age--;
        }

        return age;
    }

    public static String format(Date date, String patten) {
        if (date == null) {
            return null;
        } else {
            if (StringUtil.isEmpty(patten)) {
                patten = "yyyy-MM-dd";
            }

            SimpleDateFormat format = new SimpleDateFormat(patten);
            return format.format(date);
        }
    }

    public static String formatYMD(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(date);
        }
    }

    public static String formatYMDHMS(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(date);
        }
    }

    public static Date parseDate(String str) throws Exception {
        if (StringUtil.isEmpty(str)) {
            return null;
        } else {
            str = StringUtil.deleteWhitespace(str);
            if (NOW_DATE_PATTERN.matcher(str).matches()) {
                return null;
            } else {
                str = cleanDate(str, false);
                DateFormat format = null;
                if (DATE_PATTERN_14.matcher(str).matches()) {
                    format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                } else if (DATE_PATTERN_13.matcher(str).matches()) {
                    format = new SimpleDateFormat("yyyy-MM-dd HH-mm");
                } else if (!DATE_PATTERN_1.matcher(str).matches() && !DATE_PATTERN_2.matcher(str).matches()) {
                    if (!DATE_PATTERN_3.matcher(str).matches() && !DATE_PATTERN_4.matcher(str).matches()) {
                        if (DATE_PATTERN_5.matcher(str).matches()) {
                            format = new SimpleDateFormat("yyyy");
                        } else if (DATE_PATTERN_6.matcher(str).matches()) {
                            format = new SimpleDateFormat("dd-MM-yyyy");
                        } else if (DATE_PATTERN_7.matcher(str).matches()) {
                            format = new SimpleDateFormat("MM-yyyy");
                        } else if (DATE_PATTERN_8.matcher(str).matches()) {
                            format = new SimpleDateFormat("dd MM yyyy");
                        } else if (DATE_PATTERN_9.matcher(str).matches()) {
                            format = new SimpleDateFormat("dd-MM-yyyy");
                        } else if (DATE_PATTERN_10.matcher(str).matches()) {
                            format = new SimpleDateFormat("MM yyyy");
                        } else if (DATE_PATTERN_11.matcher(str).matches()) {
                            format = new SimpleDateFormat("MM-yyyy");
                        } else if (DATE_PATTERN_12.matcher(str).matches()) {
                            format = new SimpleDateFormat("yy-MM");
                        } else if (DATE_PATTERN_16.matcher(str).matches()) {
                            format = new SimpleDateFormat("yyyyMM");
                        } else if (DATE_PATTERN_17.matcher(str).matches()) {
                            format = new SimpleDateFormat("yyyyMMdd");
                        }
                    } else {
                        format = new SimpleDateFormat("yyyy-MM");
                    }
                } else {
                    format = new SimpleDateFormat("yyyy-MM-dd");
                }

                return format != null ? format.parse(str) : null;
            }
        }
    }

    public static String cleanDate(String str, boolean isCheckNow) {
        if (StringUtil.isEmpty(str)) {
            return str;
        } else {
            if (isCheckNow) {
                str = StringUtil.deleteWhitespace(str);
                if (NOW_DATE_PATTERN.matcher(str).matches()) {
                    return str;
                }
            }

            str = str.toLowerCase();
            str = StringUtil.replaceEach(str, CHINESE_YMD_STR, CHINESE_YMD_REPLACE_CHAR);
            str = StringUtil.replaceEach(str, ENGLISH_M_STR, ENGLISH_M_REPLACE_CHAR);
            str = StringUtil.deleteWhitespace(str);
            str = str.replaceAll("\\p{Punct}{1,}|[Oo][Ff]", "-");
            str = cleanup(str);
            return str;
        }
    }

    private static String cleanup(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        } else {
            int sz = str.length();
            char[] chs = new char[sz];
            int count = 0;

            int i;
            for (i = 0; i < sz; ++i) {
                if (Character.isDigit(str.charAt(i))) {
                    chs[count++] = str.charAt(i);
                } else if (str.charAt(i) == '-') {
                    chs[count++] = str.charAt(i);
                }
            }

            i = count - 1;
            if (i > 0 && chs[i] == '-') {
                --count;
            }

            if (count != sz) {
                str = new String(chs, 0, count);
            }

            while (str.startsWith("-")) {
                str = str.substring("-".length());
            }

            while (str.endsWith("-")) {
                str = str.substring(0, str.length() - "-".length());
            }

            return str;
        }
    }

}
