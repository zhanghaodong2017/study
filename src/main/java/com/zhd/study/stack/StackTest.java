package com.zhd.study.stack;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-11 14:12
 */
public class StackTest {

    public static void main(String[] args) {
        String expression = "10+18*2-20/4";
        int result = calculator(expression);
        System.out.println(result);
    }

    public static int calculator(String expression) {
        if (StringUtils.isBlank(expression)) {
            throw new RuntimeException("表达式不合法");
        }
        expression = expression.replace(" ", "");//去除空格

        String[] split = expression.split("[+-/*]");
        System.out.println(Arrays.toString(split));
        return 0;
    }
}
