package com.zhd.study.designPattern;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-27 14:21
 */
public class Singleton {
    public static Object getInstance(boolean flag) {
        if (flag) {
            return new LazyHolder[2];
        }
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
        getInstance(true);
        System.out.println("----");
        getInstance(false);
    }

    private static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();
        static {
            System.out.println("LazyHolder.<clinit>");
        }
    }
}
