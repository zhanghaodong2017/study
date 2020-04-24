package com.zhd.study.suanfa;

import java.math.BigInteger;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-10 19:23
 */
public class DoublePow {
    public static void main(String[] args) {
        double result = Math.pow(3.4D, 4);
        System.out.println(result);
        System.out.println(pow(3.4D, 4));

        printMax(4);
    }

    public static void printMax(int n) {
        if (n <= 0) {
            return;
        }
        BigInteger maxInt = powInt(10, n);
        for (BigInteger i = BigInteger.valueOf(1); maxInt.subtract(i).compareTo(BigInteger.ZERO) > 0; i = i.add(BigInteger.ONE)) {
            System.out.println(i);
        }

    }

    public static BigInteger powInt(int x, int n) {
        if (n <= 1) {
            return BigInteger.valueOf(x);
        }
        return BigInteger.valueOf(x).multiply(powInt(x, n - 1));

    }

    public static double pow(double x, int n) {
        if (n <= 1) {
            return x;
        }
        return pow(x, n - 1) * x;

    }

}
