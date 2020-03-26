package com.zhd.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-23 11:33
 */
public class FileNio {

    public static final int DEFAULT_BUFFER = 1024;

    public static void main(String[] args) {
        //写入文件
        String fileName = "/Users/zhanghaodong/work/myproject/study/nio.txt";
//        String content = "hello nio\n";
        String content = read("/Users/zhanghaodong/Documents/zhd/asctivity/PowerInviteUserCustomerIface.java");
        System.out.println(content);
//        write(fileName, content);
//        String result = read(fileName);
//        System.out.println(result);
    }


    public static void write(String fileName, String content) {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName, true);
            FileChannel channel = outputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER);
            byte[] bytes = content.getBytes("utf-8");
            for (int i = 0; i < bytes.length; i++) {
                buffer.put(bytes[i]);
                if (!buffer.hasRemaining()) {
                    buffer.flip();
                    channel.write(buffer);
                    buffer.clear();
                }
            }
            buffer.flip();
            channel.write(buffer);

            channel.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String read(String fileName) {
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            FileChannel channel = inputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER);

            StringBuffer sb = new StringBuffer();

            while (true) {
                int count = channel.read(buffer);
                if (count <= -1) {
                    break;
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    sb.append((char) buffer.get());
                }
                buffer.compact();
            }

            channel.close();
            inputStream.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

