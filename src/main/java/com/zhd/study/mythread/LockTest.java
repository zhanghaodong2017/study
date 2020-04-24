package com.zhd.study.mythread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-16 11:32
 */
public class LockTest {

    public static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws Exception {
        Thread t1 = new TestThread();
        Thread t2 = new TestThread();
        t1.start();
        t2.start();

        Condition condition = lock.newCondition();

        condition.await();
    }


}

class TestThread extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                LockTest.lock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                LockTest.lock.unlock();
            }
        }
    }
}