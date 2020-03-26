package com.zhd.study.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-25 10:10
 */
public class ChannelExample {
    public static void main(String[] args) throws IOException {
        testFilePosition();
    }

    private static void testFilePosition() throws IOException {
        final String filepath = "/Users/zhanghaodong/work/myproject/study/a.txt";
        FileOutputStream fos = new FileOutputStream(filepath);
        StringBuilder sb = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            sb.append(c);
        }
        fos.write(sb.toString().getBytes());
        fos.flush();
        fos.close();

        RandomAccessFile file = new RandomAccessFile(filepath, "rw");
        FileChannel channel = file.getChannel();
        channel.position(100);
        channel.write((ByteBuffer) ByteBuffer.allocate(1).put((byte) 76).flip());
        System.out.println("file position in RandomAccessFile is :" + file.getFilePointer());
        System.out.println("file length is:"+file.length());
    }


    private static void testChannelCreate() throws IOException {
        final String filepath = "/Users/zhanghaodong/work/myproject/study/a.txt";

        RandomAccessFile randomAccessFile = new RandomAccessFile(filepath, "rw");
        FileChannel readAndWriteChannel = randomAccessFile.getChannel();

        FileInputStream fis = new FileInputStream(filepath);
        FileChannel readChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream(filepath, true);
        FileChannel writeChannel = fos.getChannel();

        String content = "hello world\n";
        fos.write(content.getBytes());
        byte[] bytes = new byte[1024];
        int count = fis.read(bytes);
        System.out.println(new String(bytes, 0, count));
        ByteBuffer buffer = ByteBuffer.allocate(64);

        readChannel.read(buffer);

        buffer.flip();
        StringBuffer stringBuffer = new StringBuffer();
        while (buffer.hasRemaining()) {
            stringBuffer.append(buffer.getChar());
        }
        System.out.println(stringBuffer.toString());

        buffer.clear();

        buffer.put(content.getBytes());
        buffer.flip();
        writeChannel.write(buffer);


        readAndWriteChannel.close();
        readChannel.close();
        writeChannel.close();
    }

    private static void testChannel() {
        ReadableByteChannel readableByteChannel = Channels.newChannel(System.in);
        WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            while (readableByteChannel.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    writableByteChannel.write(buffer);
                }
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
