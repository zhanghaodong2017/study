package com.zhd.study.mythread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-17 20:00
 */
public class ConcurrentLinkedQueueTest {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<String> linkedQueue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 100; i++) {
            int temp = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    linkedQueue.add("link-" + temp);
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(5);

        for (int i = 0; i < 100; i++) {
            System.out.println(linkedQueue.poll());
        }



    }
}
