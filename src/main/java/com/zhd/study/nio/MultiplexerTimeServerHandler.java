package com.zhd.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-26 14:58
 */
public class MultiplexerTimeServerHandler implements Runnable {
    private Selector selector = null;
    private ServerSocketChannel servChannel = null;
    private volatile boolean stop;


    public MultiplexerTimeServerHandler(int port) {
        try {
            servChannel = ServerSocketChannel.open();
            servChannel.bind(new InetSocketAddress(port));
            servChannel.configureBlocking(false);
            selector = Selector.open();
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器监听" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                int count = selector.select();
                if (count == 0) {
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //多路复用器关闭后，所有注册的channel和pipe等资源都会自动去注册并关闭
        // ，所有不需要重复释放资源
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
        if (key.isAcceptable()) {
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = channel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            String msg = readFromChannel(socketChannel);
            System.out.println("客户端：" + msg);
            writeToChannel(socketChannel, "你好，收到信息：" + msg);
            socketChannel.close();
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

    private void writeToChannel(SocketChannel channel, String content) {
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

}
