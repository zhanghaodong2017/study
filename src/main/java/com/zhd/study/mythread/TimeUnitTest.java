package com.zhd.study.mythread;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-16 15:23
 */
public class TimeUnitTest {

    public static void main(String[] args) {
        long times = TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS);
        long times2 = TimeUnit.MINUTES.convert(3, TimeUnit.DAYS);
        System.out.println("3天的毫秒数：" + times);
        System.out.println("3天的分钟数：" + times2);
    }
}
