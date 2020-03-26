package com.zhd.study.classLoad;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-27 14:43
 */
public class ClassA {

    private boolean boo = true;
    private char ch = 'c';
    private byte b = 1;
    private short s = 1;
    private int i = 1;
    private long lo = 1L;
    private float c = 0.2f;
    private double d = 0.2d;
    private String str = "b";
    private Hello hello = new Hello();

    public static String getName2() {
        System.out.println("ClassA");
        return "ClassA";
    }

    public String getAge() {
        System.out.println("ClassA");
        return "ClassA";
    }

    public String getAddress() {
        System.out.println("ClassA");
        return "ClassA";
    }
}
