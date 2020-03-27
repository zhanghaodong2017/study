package com.zhd.study.nio.bio;

import org.apache.commons.io.IOUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 14:00
 */
public class BioClient {
    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            OutputStream outputStream = socket.getOutputStream();//得到一个输出流，用于向服务器发送数据
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");//将写入的字符编码成字节后写入一个字节流
            System.out.println("请输入数据：");
            Scanner sc = new Scanner(System.in);
            String data = sc.nextLine();
            writer.write(data);
            writer.flush();//刷新缓冲

            socket.shutdownOutput();//只关闭输出流而不关闭连接
            //获取服务器端的响应数据

            String clientMsg = IOUtils.toString(socket.getInputStream(), "utf-8");
            //输出服务器端响应数据
            System.out.println("客户端接收：" + clientMsg);

            close(writer);
            close(outputStream);
            close(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
