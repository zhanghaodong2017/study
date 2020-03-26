package com.zhd.study.classLoad;

import java.lang.reflect.Method;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-27 12:26
 */
public class ClassLoaderTest2 {
    public static void main(String[] args) throws Exception {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    new Hello().sayHello();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyTwoClassLoader myTwoClassLoader = new MyTwoClassLoader();
        Class<?> aClass = myTwoClassLoader.loadClass("com.zhd.study.classLoad.Hello");
        Object obj = aClass.newInstance();
        Method sayHelloMethod = aClass.getMethod("sayHello");
        sayHelloMethod.invoke(obj);
    }


}
