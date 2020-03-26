package com.zhd.study.classLoad;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-27 14:43
 */
public class ClassB extends ClassA {

    public static String getName() {
        System.out.println("ClassB");
        return "ClassB";
    }

    @Override
    public String getAge() {
        try {
            super.getAge();
            System.out.println("ClassB");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ClassB";
    }
}
