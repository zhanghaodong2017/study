package com.zhd.study.exception;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-29 10:15
 */
public class MyException extends Exception {
    private int code;

    public MyException(int code, String message) {
        super(message);
        this.code = code;
    }
}
