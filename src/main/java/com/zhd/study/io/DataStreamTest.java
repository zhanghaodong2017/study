package com.zhd.study.io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.Arrays;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-07 14:50
 */
public class DataStreamTest {

    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("/Users/zhanghaodong/work/myproject/study/target/classes/com/zhd/study/atm/GetIpUtil.class");
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] bytes = new byte[8];
        dataInputStream.read(bytes);
        System.out.println(Arrays.toString(bytes));
        System.out.println(toHexString(bytes));
        int ch = dataInputStream.readUnsignedShort();
        System.out.println(Integer.toHexString(ch));
    }


    private static String toHexString(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            String str = Integer.toHexString(b & 0xFF);
            if (buffer.length() > 0) {
                buffer.append(" ");
            }
            buffer.append(str);

        }
        return buffer.toString();

    }
}
