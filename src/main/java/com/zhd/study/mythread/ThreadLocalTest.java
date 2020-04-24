package com.zhd.study.mythread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-21 21:43
 */
public class ThreadLocalTest {

    public static void main(String[] args)  {
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("main");


        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            int temp = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    String str1 = local.get();
                    System.out.println("before:" + str1);
                    local.set("num-" + temp);
                    String str2 = local.get();
                    System.out.println("after:" + str2);
                }
            });
        }

        service.shutdown();

    }
}
