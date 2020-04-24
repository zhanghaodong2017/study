package com.zhd.study.mythread;

import java.util.concurrent.*;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-16 22:42
 */
public class CompletableFutureTest {

    public static ExecutorService service = new ThreadPoolExecutor(10, 20,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());


    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<String> future = new CompletableFuture<>();

        for (int i = 0; i < 10; i++) {
            int temp = i;
            service.submit(new Runnable() {
                @Override
                public void run() {

                    try {
                        System.out.println("进入" + temp);
                        String cmd = future.get();
                        if ("start".equals(cmd)) {
                            System.out.println(temp + ",开始打炮");
                        }
                        if ("cancel".equals(cmd)) {
                            System.out.println(temp + ",取消了指令");
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        TimeUnit.SECONDS.sleep(2);
        future.complete("cancel");

        service.shutdown();
    }
}
