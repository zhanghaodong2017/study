package com.zhd.study.suanfa;

import java.util.Arrays;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-24 17:41
 */
public class MinDistDPTest {
    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 9},
                          {2, 1, 3, 4},
                          {5, 2, 6, 7},
                          {6, 8, 4, 3}};
        int n = 4;
        int minP = minDistDP(matrix, n);
        System.out.println(minP);
    }

    private static int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            states[0][i] = sum + matrix[0][i];
            sum = states[0][i];
        }
        sum = 0;
        for (int j = 0; j < n; j++) {
            states[j][0] = sum + matrix[j][0];
            sum = states[j][0];
        }

        for (int k = 1; k < n; k++) {

            for (int g = 1; g < n; g++) {
                int min = min(states[k - 1][g], states[k][g - 1]) + matrix[k][g];
                states[k][g] = min;
            }
        }

        print(states);

        return states[n - 1][n - 1];
    }

    private static void print(int[][] states) {
        for (int[] arr : states) {
            System.out.println(Arrays.toString(arr));
        }
    }

    private static int min(int a, int b) {
        return a > b ? b : a;
    }
}
