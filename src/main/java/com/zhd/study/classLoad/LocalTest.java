package com.zhd.study.classLoad;


/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-27 14:24
 */
public class LocalTest {
    public static void main(String[] args) {
        UserHepler userHepler = new UserHepler();

        String name = ClassB.getName();
        String name2 = ClassB.getName2();
        System.out.println("name:"+name);
        System.out.println("name2:"+name2);
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
            }
        };
        thread.setContextClassLoader(userHepler.getClass().getClassLoader());

        thread.start();
    }
}
