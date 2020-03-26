package com.zhd.study.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-26 10:50
 */
public class SelectorClient {


    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        executorService.submit(new Client("client1"));
        executorService.submit(new Client("client2"));
        executorService.submit(new Client("client3"));
        executorService.submit(new Client("client4"));

        executorService.shutdown();
    }


    private static class Client extends Thread {
        private String name;
        private Random random = new Random(47);

        public Client(String name) {
            this.name = name;
        }

        private static void writeToChannel(SocketChannel channel, String content) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            try {
                buffer.clear();
                buffer.put(content.getBytes("utf-8"));
                buffer.flip();
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
                buffer.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static String readFromChannel(SocketChannel channel) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            try {
                int count;
                StringBuffer stringBuffer = new StringBuffer();
                while ((count = channel.read(buffer)) > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    stringBuffer.append(new String(bytes, "utf-8"));
                    buffer.clear();
                }
                if (count < 0) {
                    channel.close();
                }
                return stringBuffer.toString();
            } catch (IOException e) {
                System.out.println("错误了：" + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void run() {
            try {
                SocketChannel schannel = SocketChannel.open();
                schannel.configureBlocking(false);
                schannel.connect(new InetSocketAddress(8080));
                while (!schannel.finishConnect()) {
                    TimeUnit.MILLISECONDS.sleep(100);//睡眠0.1秒
                }
                //完成连接了
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                for (int i = 0; i < 3; i++) {
                    TimeUnit.MILLISECONDS.sleep(100 * random.nextInt(10));
                    buffer.clear();
                    String msg = "我是" + name + "; 信号id：" + i;
                    buffer.put(msg.getBytes("utf-8"));
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        schannel.write(buffer);
                    }
                    buffer.clear();
                }

                schannel.close();//关闭通道

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
