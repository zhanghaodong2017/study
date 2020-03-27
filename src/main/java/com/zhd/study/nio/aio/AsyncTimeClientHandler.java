package com.zhd.study.nio.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 16:29
 */
public class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {
    private AsynchronousSocketChannel socketChannel;
    private CountDownLatch countDownLatch;
    private String hostname;
    private int port;

    public AsyncTimeClientHandler(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socketChannel = AsynchronousSocketChannel.open();
            countDownLatch = new CountDownLatch(1);
            socketChannel.connect(new InetSocketAddress(hostname, port), this, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Void result, AsyncTimeClientHandler attachment) {
        try {
            byte[] reqBytes = "你好".getBytes("utf-8");
            ByteBuffer buffer = ByteBuffer.allocate(reqBytes.length);
            buffer.put(reqBytes);
            buffer.flip();

            socketChannel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {

                    if (buffer.hasRemaining()) {
                        socketChannel.write(buffer, buffer, this);
                    } else {
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        socketChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                                try {
                                    attachment.flip();
                                    byte[] rspBytes = new byte[attachment.remaining()];
                                    attachment.get(rspBytes);
                                    String rspBody = new String(rspBytes, "utf-8");
                                    System.out.println("服务端返回消息：" + rspBody);
                                    countDownLatch.countDown();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {
                                try {
                                    socketChannel.close();
                                    countDownLatch.countDown();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        socketChannel.close();
                        countDownLatch.countDown();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
        try {
            socketChannel.close();
            countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
