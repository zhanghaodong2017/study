package com.zhd.study.mythread;

import java.util.concurrent.Callable;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-15 15:28
 */
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("处理任务中、、");
        Thread.sleep(2000);
        return 123;
    }
}
