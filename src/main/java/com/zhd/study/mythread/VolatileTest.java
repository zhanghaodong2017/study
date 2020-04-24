package com.zhd.study.mythread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-17 10:51
 */
public class VolatileTest {

    public static ExecutorService service = new ThreadPoolExecutor(200, 200,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    public static volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 200; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {

                    System.out.println(num++);
                }
            });
        }


        LockSupport.parkNanos(1000000000);
        ReentrantLock lock = new ReentrantLock();

        lock.wait();
        lock.notify();
        lock.notifyAll();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();
        thread.wait();
        thread.notify();
        thread.notifyAll();

    }
}
