package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-09 16:23
 */
public class BinaryIntTest {

    public static void main(String[] args) {
        int target = -100;
        int binaryInt = getBinaryInt(target);
        System.out.println(target + ":" + binaryInt);

    }

    private static int getBinaryInt(int target) {
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            if ((target & (1 << i)) != 0) {
                result++;
            }
        }
        return result;
    }
}
