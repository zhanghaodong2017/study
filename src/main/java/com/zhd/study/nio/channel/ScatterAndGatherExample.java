package com.zhd.study.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-25 19:44
 */
public class ScatterAndGatherExample {

    public static void main(String[] args) throws Exception {
        ByteBuffer buffer1 = ByteBuffer.allocate(5);
        buffer1.put("hello".getBytes("GBK")).flip();
        ByteBuffer buffer2 = ByteBuffer.allocate(5);
        buffer2.put("world".getBytes("GBK")).flip();
        ByteBuffer buffer3 = ByteBuffer.allocate(64);
        buffer3.put("哈哈哈".getBytes("GBK")).flip();

        ByteBuffer[] buffers = {buffer1, buffer2, buffer3};


        try {
            final String filepath = "/Users/zhanghaodong/work/myproject/study/scatter.txt";
            //gather example
            RandomAccessFile file = new RandomAccessFile(filepath, "rw");
            FileChannel channel = file.getChannel();
            channel.write(buffers);
            channel.force(false);
            channel.close();

            showFileContent(filepath);

            //scatter example
            buffer1.clear();
            buffer2.clear();
            buffer3.clear();

            file = new RandomAccessFile(filepath, "r");
            channel = file.getChannel();
            channel.read(buffers);
            String str1 = getBufferContent(buffer1);
            String str2 = getBufferContent(buffer2);
            String str3 = getBufferContent(buffer3);
            System.out.println("buffer1 :" + str1);
            System.out.println("buffer2 :" + str2);
            System.out.println("buffer3 :" + str3);
            channel.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getBufferContent(ByteBuffer buffer) throws UnsupportedEncodingException {
        buffer.flip();
        System.out.println(buffer);
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return new String(bytes, "GBK");
    }

    private static void showFileContent(String filepath) {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            byte[] bytes = new byte[1024];
            int len = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = fis.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            String str = baos.toString("GBK");
            System.out.println("file content:");
            System.out.println(str);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
