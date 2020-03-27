package com.zhd.study.nio.duanzhan;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class DuanNioSocketServer extends Thread {
    ServerSocketChannel serverSocketChannel = null;
    Selector selector = null;
    SelectionKey selectionKey = null;


    public static void main(String args[]) throws IOException {
        DuanNioSocketServer server = new DuanNioSocketServer();
        server.initServer();
        server.start();

    }

    public void initServer() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    @Override
    public void run() {
        while (true) {
            try {
                int selectKey = selector.select();
                if (selectKey > 0) {
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keySet.iterator();
                    while (iter.hasNext()) {
                        SelectionKey selectionKey = iter.next();
                        iter.remove();

                        if (selectionKey.isAcceptable()) {
                            accept(selectionKey);
                        }
                        if (selectionKey.isReadable()) {
                            read(selectionKey);
                        }
                        if (selectionKey.isWritable()) {
//                            write(selectionKey);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    serverSocketChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }

    public void accept(SelectionKey key) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("is acceptable");
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void read(SelectionKey selectionKey) {
        System.out.println("read事件");

        try {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(50);
            int len = channel.read(byteBuffer);
            if (len > 0) {

                byteBuffer.flip();
                byte[] byteArray = new byte[byteBuffer.limit()];
                byteBuffer.get(byteArray);
                System.out.println("NioSocketServer receive from client:" + new String(byteArray));

            }
            selectionKey.interestOps(SelectionKey.OP_READ);
//            selectionKey.interestOps(SelectionKey.OP_WRITE);
        } catch (IOException e) {
            try {
                serverSocketChannel.close();
                selectionKey.cancel();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void write(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        String httpResponse = "HTTP/1.1 200 OK\r\n" + "Content-Length: 38\r\n" + "Content-Type: text/html\r\n" + "\r\n"
                + "<html><body>Hello World!</body></html>";
        System.out.println("response from server to client");
        try {
            ByteBuffer byteBuffer = ByteBuffer.wrap(httpResponse.getBytes());
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            selectionKey.cancel();
        } catch (IOException e) {
            try {
                selectionKey.cancel();
                serverSocketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}