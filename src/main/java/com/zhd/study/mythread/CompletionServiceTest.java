package com.zhd.study.mythread;

import java.util.concurrent.*;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-17 14:47
 */
public class CompletionServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1 << 10));

        CompletionService<String> completionService = new ExecutorCompletionService<>(service);


        for (int i = 0; i < 15; i++) {
            int temp = i;
            completionService.submit(() -> {
                return Thread.currentThread().getName() + " - x = " + temp;
            });
        }

        for (int i = 0; i < 15; i++) {
            System.out.println(completionService.take().get());
        }


        SynchronousQueue<String> queue = new SynchronousQueue<>();
        service.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String take = queue.take();
                        System.out.println(take);
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        for (int i = 0; i < 10; i++) {
            queue.put("zhangsan" + i);
        }
        service.shutdown();


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        thread.start();




    }
}
