package com.zhd.study.jvm;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-30 11:08
 */
public class StackOnTest {
    public static void alloc() {
        byte[] by = new byte[2];
        by[0] = 1;
    }

    public static void main(String[] args) {
        /*long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);*/

        for (int i = 0; i < 100000; i++) {
            UserData userData = new UserData(new byte[1024]);
//            System.out.println(userData);

        }


    }
}
