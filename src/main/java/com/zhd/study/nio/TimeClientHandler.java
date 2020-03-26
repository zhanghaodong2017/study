package com.zhd.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-26 15:12
 */
public class TimeClientHandler implements Runnable {
    private SocketChannel socketChannel;
    private Selector selector;
    private volatile boolean stop;


    public TimeClientHandler(String host, int port) {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            socketChannel.connect(new InetSocketAddress(host, port));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void writeToChannel(SocketChannel channel, String content) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
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

    @Override
    public void run() {

        try {

            while (!stop) {
                int count = selector.select();
                if (count == 0) {
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    handleInput(key);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) {
            return;
        }
        SocketChannel sc = (SocketChannel) key.channel();
        if (key.isConnectable()) {
            if (sc.finishConnect()) {
                //在通道上注册感兴趣事件为读
                sc.register(selector, SelectionKey.OP_READ);
                writeToChannel(sc, "我刚来");//发送命令
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

    private String readFromChannel(SocketChannel channel) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count;
            StringBuffer stringBuffer = new StringBuffer();
            while ((count = channel.read(buffer)) > 0) {
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
}
