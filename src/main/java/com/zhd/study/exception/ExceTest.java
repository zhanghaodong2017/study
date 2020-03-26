package com.zhd.study.exception;

import static com.zhd.study.exception.Consts.MY_EXCEPTION;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-29 10:14
 */
public class ExceTest {
    public static void main(String[] args) {
        try {
            fun1();
        } catch (MyException e) {
            e.printStackTrace();
            throw new RuntimeException("nre");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("over");
        }
    }

    private static void fun1() throws MyException {
//        throw new MyException(102, "网络异常了");
        throw MY_EXCEPTION;
    }
}
