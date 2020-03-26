package com.zhd.study.reflect;

import java.lang.reflect.Method;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-30 08:17
 */
public class ReflectTest {

    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.zhd.study.reflect.ReflectTest");
        Object o = aClass.newInstance();
        Method method = aClass.getMethod("target", int.class);
        Method method2 = aClass.getMethod("target", int.class);
        method.invoke(o,0);
        System.out.println(method2 == method);
    }
}
