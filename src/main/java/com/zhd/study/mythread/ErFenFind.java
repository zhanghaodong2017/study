package com.zhd.study.mythread;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-23 14:30
 */
public class ErFenFind {
    public static void main(String[] args) {


        int[] arr = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 6, 6, 6, 6, 6};
//        int[] arr = {1, 2};
        int n = 3;
        int result = find(arr, n);
        System.out.println(result);

    }

    public static int find(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr[0] > n || arr[arr.length - 1] < n) {
            return 0;
        }

        int head = 0, tail = arr.length - 1, mid = 0;

        while (head <= tail) {
            mid = (tail + head) / 2;
            int value = arr[mid];
            if (value > n) {
                tail = mid - 1;
            } else if (value < n) {
                head = mid + 1;
            } else {
                break;
            }
        }

        head = mid;
        tail = mid;

        while (head >= 0) {
            if (arr[head] < n) {
                break;
            } else {
                head--;
            }
        }
        while (tail < arr.length) {
            if (arr[tail] > n) {
                break;
            } else {
                tail++;
            }
        }

        return tail - head - 1;
    }
}
