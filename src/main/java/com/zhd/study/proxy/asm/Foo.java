package com.zhd.study.proxy.asm;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-29 12:44
 */
public class Foo {
    private String name;

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = a + b;
        System.out.println(c);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
