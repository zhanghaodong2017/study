package com.zhd.study.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-23 14:55
 */
public class BioClient {

    public static void main(String[] args) throws IOException {
        // 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 8080;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
        String message = "你好  bike-world";
        socket.getOutputStream().write(message.getBytes("UTF-8"));
        outputStream.close();
        socket.close();

    }
}
