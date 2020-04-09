package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-08 20:10
 */
public class Lujing {

    /**
     * @param m
     * @param n
     * @return 递归方法：根据题意我们可以分析得出，计F(m,n)为到达横坐标为m,纵坐标为n的路径数，则
     * F(m,n) = F(m-1,n) + F(m,n-1)
     * 当m = 1,n = 1时，此时就是起始位置，直接返回1;
     * 当m = 1 时，n为任意值时，则F(1,n) = F(1,n - 1)
     * 当n = 1 时，m为任意值，则F(m,1) = F(m - 1,1)
     */
    public static int recUniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return recUniquePaths(m - 1, n) + recUniquePaths(m, n - 1);
    }

    public static void main(String[] args) {
        System.out.println(recUniquePaths(5, 5));
    }
}
