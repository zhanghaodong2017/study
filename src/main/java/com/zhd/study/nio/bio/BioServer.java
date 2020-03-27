package com.zhd.study.nio.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 13:50
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new BioThreadHandler(socket)).start();
        }
    }

}
