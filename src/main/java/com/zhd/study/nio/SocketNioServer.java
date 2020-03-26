package com.zhd.study.nio;

import java.io.IOException;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-23 14:43
 */
public class SocketNioServer {


    public static void main(String... args) throws IOException {
        int port = 8888;

        MultiplexerTimeServerHandler server = new MultiplexerTimeServerHandler(port);
        new Thread(server, "nio-001").start();
    }

}
