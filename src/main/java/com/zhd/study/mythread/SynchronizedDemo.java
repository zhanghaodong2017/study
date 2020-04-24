package com.zhd.study.mythread;

import java.util.concurrent.locks.Lock;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-16 10:19
 */
public class SynchronizedDemo {

    public static synchronized void method3333() {
        System.out.println("Method 1 start");
    }

    public void method() {
        synchronized (this) {
            System.out.println("Method 1 start");
        }
    }

    public synchronized void method2222() {
        System.out.println("Method 1 start");


    }
}