package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-09 12:43
 */
public class FeiBoNaQi {
    public static int[] FIB = new int[40];

    public static void main(String[] args) {
        proOperate();

        for (int i = 1; i < 39; i++) {
            System.out.println(fibonacci3(i));
        }
    }

    private static void proOperate() {
        FIB[0] = 1;
        FIB[1] = 1;
        for (int i = 2; i < 40; i++) {
            FIB[i] = FIB[i - 1] + FIB[i - 2];
        }
    }

    public static int feibo(int n) {
        if (n <= 2) {
            return 1;
        }
        return feibo(n - 1) + feibo(n - 2);

    }

    // 时间复杂度O(n),空间复杂度O(n)
    public static int fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        int[] fib = new int[n];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n - 1];

    }

    // 时间复杂度O(n),空间复杂度O(1)
    public static int fibonacci2(int n) {
        if (n <= 2) {
            return 1;
        }
        int pre1 = 1, pre2 = 1, result = 0;

        for (int i = 2; i < n; i++) {
            result = pre1 + pre2;
            pre2 = pre1;
            pre1 = result;
        }
        return result;

    }

    // 时间复杂度O(1),空间复杂度O(1)
    public static int fibonacci3(int n) {
        return FIB[n - 1];

    }
}
