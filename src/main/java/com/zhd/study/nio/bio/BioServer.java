package com.zhd.study.nio.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 13:50
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService executorService = new ThreadPoolExecutor(10, 100, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(new BioThreadHandler(socket));
        }
    }

}
