package com.zhd.study.nio.znetty05;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 22:05
 */
public class StreamTest {

    public static void main(String[] args) throws IOException {
        test2();
        test3();

    }

    private static void test3() {
        long start = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        for (int i = 0; i < 1000000; i++) {
            UserInfo userInfo = new UserInfo("张三", 16, "上海");
            userInfo.codeC(buffer);
        }
        long end = System.currentTimeMillis();
        System.out.println("test3用时：" + (end - start));

    }

    private static void test2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            test1();
        }
        long end = System.currentTimeMillis();
        System.out.println("test2用时：" + (end - start));

    }

    private static void test1() {
        try {
            UserInfo userInfo = new UserInfo("张三", 16, "上海");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(baos);
            outputStream.writeObject(userInfo);
            outputStream.flush();
            byte[] bytes = baos.toByteArray();
//        System.out.println(bytes.length);
//        System.out.println(userInfo.codeC().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
