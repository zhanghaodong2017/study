package com.zhd.study.nio.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 16:39
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel asynchronousSocketChannel;

    public ReadCompletionHandler(AsynchronousSocketChannel asynchronousSocketChannel) {
        this.asynchronousSocketChannel = asynchronousSocketChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        byte[] reqBytes = new byte[buffer.remaining()];
        buffer.get(reqBytes);
        try {
            String reqBody = new String(reqBytes, "utf-8");
            System.out.println("客户端请求信息：" + reqBody);
            doWrite("服务端时间-" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doWrite(String rspBody) {
        try {
            byte[] rspBytes = rspBody.getBytes("utf-8");
            ByteBuffer rspBuffer = ByteBuffer.allocate(rspBytes.length);
            rspBuffer.put(rspBytes);
            rspBuffer.flip();
            asynchronousSocketChannel.write(rspBuffer, rspBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    //如果没有发送完，继续发
                    if (buffer.hasRemaining()) {
                        asynchronousSocketChannel.write(buffer, buffer, this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        asynchronousSocketChannel.close();
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
    public void failed(Throwable exc, ByteBuffer buffer) {
        exc.printStackTrace();
    }
}
