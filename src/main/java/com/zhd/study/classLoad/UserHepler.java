package com.zhd.study.classLoad;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-27 14:25
 */
public class UserHepler {
    static {
        System.out.println("执行static方法");
    }

    private String name;

    public UserHepler() {
        System.out.println("执行构造方法UserHepler()");
    }

    public UserHepler(String name) {
        System.out.println("执行构造方法UserHepler(String name)");
        this.name = name;
    }
}
