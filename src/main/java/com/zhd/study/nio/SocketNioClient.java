package com.zhd.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-23 14:43
 */
public class SocketNioClient {

    public static void main(String[] args) throws Exception {
        client();

    }


    public static void client() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
            if (socketChannel.finishConnect()) {
                TimeUnit.SECONDS.sleep(1);
                String info = "I'm th information from client- 张浩东";
                buffer.clear();
                buffer.put(info.getBytes());
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println(buffer);
                    socketChannel.write(buffer);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
