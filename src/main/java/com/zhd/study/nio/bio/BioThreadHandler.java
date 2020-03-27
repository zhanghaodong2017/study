package com.zhd.study.nio.bio;

import org.apache.commons.io.IOUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 13:51
 */
public class BioThreadHandler implements Runnable {

    private Socket socket;

    public BioThreadHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();

            String clientMsg = IOUtils.toString(inputStream, "utf-8");
            System.out.println("客户端的请求：" + clientMsg);

            socket.shutdownInput();//关闭输入流

            //响应客户端请求
            String content = "你好，我收到你的信息:" + clientMsg;
            outputStream = socket.getOutputStream();
            outputStream.write(content.getBytes("utf-8"));
            outputStream.flush();//清空缓冲区数据

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream);
            close(outputStream);
            close(socket);
        }
    }

    private void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
