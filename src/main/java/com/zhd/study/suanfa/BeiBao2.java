package com.zhd.study.suanfa;

import java.util.Arrays;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-24 16:29
 */
public class BeiBao2 {

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 5, 7, 9};
        int[] value = {1, 2, 15, 5, 8, 10};

        int sumWeight = 10;

        int knapsack3 = knapsack3(arr, value, arr.length, sumWeight);
//        int knapsack2 = knapsack2(arr, arr.length, sumWeight);
//        System.out.println(knapsack2);
        System.out.println(knapsack3);
    }


    public static int knapsack3(int[] items, int[] value, int n, int w) {
        if (items == null || items.length == 0) {
            return 0;
        }
        int[] states = new int[w + 1]; // 默认值0

        if (items[0] <= w) {
            states[items[0]] = value[0];
        }

        System.out.println(Arrays.toString(states));
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j] > 0 || j == 0) {
                    states[j + items[i]] = Math.max((states[j] + value[i]), states[j + items[i]]);
                }
                System.out.println(Arrays.toString(states));
            }
        }
        int maxValue = Integer.MIN_VALUE;
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] > maxValue) {
                maxValue = states[i];
            }
        }
        return maxValue;
    }

    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值false
        System.out.println(Arrays.toString(states));
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j] == true) {
                    states[j + items[i]] = true;
                }
                System.out.println(Arrays.toString(states));
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) {
                return i;
            }
        }
        return 0;
    }

    //weight:物品重量，n:物品个数，w:背包可承载重量
    public static int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1]; // 默认值false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第i个物品放入背包
                if (states[i - 1][j] == true) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= w - weight[i]; ++j) {//把第i个物品放入背包
                if (states[i - 1][j] == true) {
                    states[i][j + weight[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n - 1][i] == true) {
                return i;
            }
        }
        return 0;
    }


}
