package com.zhd.study.nio.znettyFile;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-29 15:43
 */
public class BizException extends Exception {

    private HttpResponseStatus rspCode;

    public BizException() {
    }

    public BizException(HttpResponseStatus rspCode) {
        this.rspCode = rspCode;
    }

    public BizException(String message) {
        super(message);
    }

    public HttpResponseStatus getRspCode() {
        return rspCode;
    }

    public void setRspCode(HttpResponseStatus rspCode) {
        this.rspCode = rspCode;
    }
}
