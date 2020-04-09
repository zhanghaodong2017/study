package com.zhd.study.suanfa;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: zhanghaodong
 * @description 回文数组
 * @date: 2020-04-08 19:15
 */
public class HuiWen {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        Integer length = Integer.valueOf(line1);
        String[] split = line2.split(" ");
        int[] arr = new int[length];
        int sum = 0;
        for (int i = 0; i < length; i++) {
            arr[i] = Integer.valueOf(split[i]);
            sum += arr[i];
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(sum);
        int middle = (length + 1) / 2;


    }
}
