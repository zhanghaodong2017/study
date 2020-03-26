package com.zhd.study.jvm;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-30 20:02
 */
public class UserData {

    private byte[] b;

    public UserData(byte[] b) {
        this.b = b;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("UserData.finalize");
        super.finalize();
    }
}
