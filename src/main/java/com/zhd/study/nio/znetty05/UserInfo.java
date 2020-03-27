package com.zhd.study.nio.znetty05;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-27 22:03
 */
@Data
@AllArgsConstructor
public class UserInfo implements Serializable {
    private String username;
    private int age;
    private String address;

    public byte[] codeC() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(username.getBytes("utf-8"));
            buffer.putInt(age);
            buffer.put(address.getBytes("utf-8"));
            buffer.flip();

            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            return bytes;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] codeC(ByteBuffer buffer) {
        try {
            buffer.clear();
            buffer.put(username.getBytes("utf-8"));
            buffer.putInt(age);
            buffer.put(address.getBytes("utf-8"));
            buffer.flip();

            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            return bytes;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
