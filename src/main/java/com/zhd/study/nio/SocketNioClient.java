package com.zhd.study.nio;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-23 14:43
 */
public class SocketNioClient {

    public static void main(String... args) {
        String host = "127.0.0.1";
        int port = 8888;

        new Thread(new TimeClientHandler(host, port), "client-001").start();
    }
}
