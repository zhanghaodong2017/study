package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description 数组中重复的数字
 * @date: 2020-04-08 17:09
 */
public class RepetitiveNum {
    public static void main(String[] args) {
        int[][] arr = {{1, 5, 9, 19},
                {2, 7, 11, 21},
                {4, 8, 13, 25}};
        int target = 13;

        System.out.println(find(arr, target));
    }

    public static boolean find(int[][] arr, int target) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return false;
        }
        int x = arr.length - 1, y = 0, clo = arr[0].length;

        //从左下角开始
        while (x >= 0 && y < clo) {

            if (arr[x][y] == target) {
                return true;
            } else if (arr[x][y] > target) {
                x--;
            } else {
                y++;
            }
        }

        return false;
    }
}
