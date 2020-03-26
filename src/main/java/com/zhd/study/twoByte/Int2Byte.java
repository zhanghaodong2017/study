package com.zhd.study.twoByte;

import java.util.Arrays;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-02-24 10:19
 */
public class Int2Byte {
    public static void main(String[] args) {
        Integer a = 323;
        System.out.println(Arrays.toString(toLH(a)));
        System.out.println(Arrays.toString(toHH(a)));
        System.out.println(toInt(toLH(a)));
        System.out.println(toInt2(toHH(a)));
        System.out.println(Arrays.toString("abc".getBytes()));

    }

    //大端模式:高字节保存在内存的低地址中,这和我们的阅读习惯一致
    public static byte[] toHH(int n) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) (n & 0xff);
        bytes[2] = (byte) (n >> 8 & 0xff);
        bytes[1] = (byte) (n >> 16 & 0xff);
        bytes[0] = (byte) (n >> 24 & 0xff);
        return bytes;
    }

    //小端模式:高字节保存在内存的高地址中
    public static byte[] toLH(int n) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (n & 0xff);
        bytes[1] = (byte) (n >> 8 & 0xff);
        bytes[2] = (byte) (n >> 16 & 0xff);
        bytes[3] = (byte) (n >> 24 & 0xff);
        return bytes;
    }


    public static int toInt(byte[] bytes) {
        int a = 0;
        for (int i = 0; i < bytes.length; i++) {
            a += ((int) bytes[i]) << (8 * i);
        }
        return a;
    }

    public static int toInt2(byte[] bytes) {
        int a = 0;
        for (int i = 0; i < bytes.length; i++) {
            a += ((int) bytes[i]) << (8 * (3 - i));
        }
        return a;
    }
}
