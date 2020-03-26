package com.zhd.study.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-24 14:07
 */
public class CharBufferTest {

    public static void main(String[] args) {
        testElementView();

    }

    private static void test1() {
        CharBuffer buffer = CharBuffer.allocate(64);
        showBuffer(buffer);
        buffer.put("abcd");
        showBuffer(buffer);
        //设置mark位置为3
        buffer.position(3).mark().position(5);
        buffer.put("ef");
        showBuffer(buffer);
        buffer.reset();
        showBuffer(buffer);

        buffer.flip();
        showBuffer(buffer);
        char c1 = buffer.get();
        System.out.println(c1);
        char c2 = buffer.get();
        System.out.println(c2);
        showBuffer(buffer);
        //clear后的状态
        buffer.clear();
        showBuffer(buffer);
        buffer.put("abc");
        buffer.flip();
        //第一种读取方法
        char c11 = buffer.get();
        char c22 = buffer.get();
        char c33 = buffer.get();
        buffer.clear();
        //第二种读取方法
        buffer.put("abc");
        buffer.flip();
        char[] chars = new char[buffer.remaining()];
        buffer.get(chars);
        System.out.println(chars);
        buffer.compact();
        showBuffer(buffer);
    }

    public static void showBuffer(CharBuffer buffer) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buffer.limit(); i++) {
            char c = buffer.get(i);
            if (c == 0) {
                c = '.';
            }
            sb.append(c);
        }
        System.out.printf("position=%d, limit=%d, capacity=%d, content=%s\n",
                buffer.position(), buffer.limit(), buffer.capacity(), sb.toString());
    }

    private static void testCompact() {
        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put("abcde");
        buffer.flip();
        //先读取两个字符
        buffer.get();
        buffer.get();
        showBuffer(buffer);
        //压缩
        buffer.compact();
        //继续写入
        buffer.put("fghi");
        buffer.flip();
        showBuffer(buffer);
        //从头读取后续的字符
        char[] chars = new char[buffer.remaining()];
        buffer.get(chars);
        System.out.println(chars);
    }

    private static void testDuplicate() {
        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put("abcde");
        CharBuffer buffer1 = buffer.duplicate();
        buffer1.clear();
        buffer1.put("alex");
        showBuffer(buffer);
        showBuffer(buffer1);
    }

    private static void testSlice() {
        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put("abcdefghij");
        buffer.position(5);
        CharBuffer slice = buffer.slice();
        showBuffer(buffer);
        showBuffer(slice);
    }

    private static void testElementView() {
        ByteBuffer buffer = ByteBuffer.allocate(12);
        //存入四个字节,0x00000042
        buffer.put((byte) 0x00).put((byte) 0x00).put((byte) 0x00).put((byte) 0x42);
        buffer.position(0);
        //转换为IntBuffer，并取出一个int（四个字节）
        IntBuffer intBuffer = buffer.asIntBuffer();
        int i = intBuffer.get();
        System.out.println(Integer.toHexString(i));
    }

}
