package com.zhd.study.nio.aio;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 16:28
 */
public class TimeServer {
    public static void main(String[] args) {
        AsyncTimeServerHandler serverHandler = new AsyncTimeServerHandler(8080);
        new Thread(serverHandler, "serverHandler").start();
    }
}
