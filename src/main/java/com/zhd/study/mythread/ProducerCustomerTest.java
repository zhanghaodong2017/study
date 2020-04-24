package com.zhd.study.mythread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-17 19:32
 */
public class ProducerCustomerTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        new Thread(new Producer(queue)).start();

        new Thread(new Customer(queue)).start();

        TimeUnit.SECONDS.sleep(30);
        System.exit(0);
    }
}

class Producer implements Runnable {

    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        while (true) {
            try {
                queue.put("wo shi shengchanz:" + System.currentTimeMillis());
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Customer implements Runnable {

    private BlockingQueue<String> queue;

    public Customer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = queue.take();
                System.out.println("客户端处理:" + data);
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}