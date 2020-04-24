package com.zhd.study.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
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
                StringBuffer stringBuffer = new StringBuffer();
                while (channel.read(buffer) > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    stringBuffer.append(new String(bytes, "utf-8"));
                    buffer.clear();
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
                Selector selector = Selector.open();
                schannel.register(selector, SelectionKey.OP_CONNECT);

                while (true) {
                    selector.select();//阻塞
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        handleInput(selector, key);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void handleInput(Selector selector, SelectionKey key) throws IOException, InterruptedException {
            if (!key.isValid()) {
                return;
            }
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect()) {
                    //在通道上注册感兴趣事件为读
                    sc.register(selector, SelectionKey.OP_READ);
                    writeToChannel(sc, "我是" + name);
                } else {
                    //连接失败，退出
                    System.exit(1);
                }
            }
            if (key.isReadable()) {
                String msg = readFromChannel(sc);
                System.out.println("服务端返回：" + msg);
                sc.close();
            }
        }

    }

}
