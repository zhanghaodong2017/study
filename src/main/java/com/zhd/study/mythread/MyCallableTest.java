package com.zhd.study.mythread;

import java.util.concurrent.*;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-15 15:29
 */
public class MyCallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable mc = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<>(mc);
        Thread thread = new Thread(ft);
        thread.start();
        System.out.println(ft.get());

        ExecutorService executorService = new ThreadPoolExecutor(1, 10,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable());
            Future<?> future = executorService.submit(new MyRunnable());
            future.cancel(true);
        }
        executorService.shutdown();


    }
}
