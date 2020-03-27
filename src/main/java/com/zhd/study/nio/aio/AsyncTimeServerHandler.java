package com.zhd.study.nio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 16:29
 */
public class AsyncTimeServerHandler implements Runnable {

    public CountDownLatch countDownLatch;
    public AsynchronousServerSocketChannel serverSocketChannel;

    public AsyncTimeServerHandler(int port) {
        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        countDownLatch = new CountDownLatch(1);

        serverSocketChannel.accept(this, new AcceptCompletionHandler());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
