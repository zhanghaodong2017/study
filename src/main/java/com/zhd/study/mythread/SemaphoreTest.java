package com.zhd.study.mythread;

import java.util.concurrent.*;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-16 21:54
 */
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);


        ExecutorService service = new ThreadPoolExecutor(10, 12,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        for (int i = 0; i < 10; i++) {

            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(semaphore.availablePermits() + "," + semaphore.drainPermits());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }
            });
        }


    }
}
