package com.zhd.study.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AnnotationResolver {

//    private static Pattern PATTERN = Pattern.compile("(#\\{([^\\}]+)\\})");

    private static Pattern PATTERN = Pattern.compile("\\#\\{.*\\}");
    /**
     * 解析注解上的值
     *
     * @param str 需要解析的字符串
     * @return
     */
    public static String resolver(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = PATTERN.matcher(str);
        while (matcher.find()) {
            String fParseStr = matcher.group().trim();
            System.out.println(fParseStr);
            str = str.replaceAll("\\#\\{.*\\}", "?");
        }
        return str;
    }

}
