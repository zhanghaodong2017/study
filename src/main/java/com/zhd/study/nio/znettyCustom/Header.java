package com.zhd.study.nio.znettyCustom;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-30 13:49
 */
@Data
public class Header {
    /**
     * 0xABEF +主版本号+次版本号，2+1+1
     */
    private int crcCode = 0xABEF0101;
    /**
     * 整个消息长度  请求头+消息体
     */
    private int length;
    /**
     * 回话ID
     */
    private long sessionID;
    /**
     * 0.业务请求消息
     * 1.业务响应消息
     * 2.业务ONE_WAY消息（既是请求又是响应消息）
     * 3.握手请求
     * 4.握手响应
     * 5.心跳请求
     * 6.心跳响应
     */
    private byte type;
    /**
     * 优先级 0-255
     */
    private byte priority;
    /**
     * 消息头
     */
    private Map<String, Object> attachment = new HashMap<>();

}
