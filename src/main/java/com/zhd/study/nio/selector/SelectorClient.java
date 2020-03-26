package com.zhd.study.nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-26 10:50
 */
public class SelectorClient {


    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 8, 15, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(2048), new NamedThreadFactory("LOGGER"));

        executorService.submit(new Client("client1"));
        executorService.submit(new Client("client2"));
        executorService.submit(new Client("client3"));
        executorService.submit(new Client("client4"));

        executorService.shutdown();
    }

    private static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        private NamedThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + POOL_NUMBER.getAndIncrement() + "-" + name + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }

    }

    private static class Client extends Thread {
        private String name;
        private Random random = new Random(47);
        public Client(String name) {
            this.name = name;
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
