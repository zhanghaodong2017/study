package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description 替换空格
 * @date: 2020-04-08 16:51
 */
public class StringReplace {

    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer("zhang hao dong ");

        String result = replaceSpace(buffer);
        System.out.println(result);
    }

    public static String replaceSpace(StringBuffer buffer) {
        if (buffer == null || buffer.length() == 0) {
            return "";
        }
        int length = buffer.length();
        for (int i = 0; i < length; i++) {
            if (buffer.charAt(i) == ' ') {
                buffer.append("  ");
            }
        }
        int p1 = length - 1, p2 = buffer.length() - 1;

        while (p1 >= 0 && p1 <= p2) {
            char c = buffer.charAt(p1--);
            if (c == ' ') {
                buffer.setCharAt(p2--, '0');
                buffer.setCharAt(p2--, '2');
                buffer.setCharAt(p2--, '%');
            } else {
                buffer.setCharAt(p2--, c);
            }
        }
        return buffer.toString();
    }
}
