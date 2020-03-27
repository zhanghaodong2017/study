package com.zhd.study.nio.aio;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 16:48
 */
public class TimeClient {
    public static void main(String[] args) {

        AsyncTimeClientHandler clientHandler = new AsyncTimeClientHandler("127.0.0.1", 8080);
        new Thread(clientHandler).start();
    }
}
