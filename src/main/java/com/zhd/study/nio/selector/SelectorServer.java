package com.zhd.study.nio.selector;

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
 * @date: 2020-03-26 10:36
 */
public class SelectorServer {

    private static ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(8080));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            //1.register
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("register channel , channel number is:" + selector.keys().size());


            while (true) {
                int count = selector.select();//阻塞
                if (count == 0) {
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        ServerSocketChannel sscTemp = (ServerSocketChannel) key.channel();
                        //得到一个连接好的SocketChannel，并把它注册到Selector上，兴趣操作为READ
                        SocketChannel sc = sscTemp.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                        System.out.println("register channel , channel number is:" + selector.keys().size());
                    }
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        String msg = readFromChannel(channel);
                        System.out.println("客户端请求：" + msg);

                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readFromChannel(SocketChannel channel) {
        try {
            buffer.clear();
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
            e.printStackTrace();
        }
        return null;
    }
}
