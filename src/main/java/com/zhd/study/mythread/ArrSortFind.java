package com.zhd.study.mythread;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-17 15:50
 */
public class ArrSortFind {

    public static void main(String[] args) {
        int[] arr = {20, 1, 3, 5, 6, 8, 9, 10};
//        int target = 9;
//
//        int index = find(arr, target);
//        System.out.println(index);


        maopao(arr);
        System.out.println(Arrays.toString(arr));

        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());

        CopyOnWriteArraySet arraySet = new CopyOnWriteArraySet();

        arraySet.addAll(null);

        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);


        String element = queue.peek();

        PriorityBlockingQueue<String> blockingQueue = new PriorityBlockingQueue<>(10);
    }

    private static void maopao(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }
        int temp;
        for (int i = arr.length - 1; i > 0; i--) {
            boolean flag = true;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    private static int find(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
//        return find(arr, 0, arr.length - 1, target);
        int start = 0, end = arr.length - 1, index = -1;

        while (start <= end) {
            int midle = (end + start) / 2;
            if (arr[midle] == target) {
                return midle;
            }
            if (arr[midle] > target) {
                end = midle - 1;
            }
            if (arr[midle] < target) {
                start = midle + 1;
            }
        }
        return index;
    }

    /**
     * 查询第一个匹配的数据
     *
     * @param arr
     * @param target
     * @return
     */
    private static int findFirst(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int start = 0, end = arr.length - 1, index = -1;

        while (start <= end) {
            int midle = (end + start) / 2;
            if (arr[midle] == target) {
                while (arr[start] < target) {
                    start = (start + midle) / 2;
                }
                return midle;
            }
            if (arr[midle] > target) {
                end = midle - 1;
            }
            if (arr[midle] < target) {
                start = midle + 1;
            }
        }
        return index;
    }

    /**
     * 查询最后一个匹配的数据
     *
     * @param arr
     * @param target
     * @return
     */
    private static int findLast(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int start = 0, end = arr.length - 1, index = -1;

        while (start <= end) {
            int midle = (end + start) / 2;
            if (arr[midle] == target) {
                return midle;
            }
            if (arr[midle] > target) {
                end = midle - 1;
            }
            if (arr[midle] < target) {
                start = midle + 1;
            }
        }
        return index;
    }


    private static int find(int[] arr, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        if (start == end) {
            if (arr[start] == target) {
                return start;
            } else {
                return -1;
            }
        }

        int midle = start + (end - start) / 2;
        if (arr[midle] == target) {
            return midle;
        }
        if (arr[midle] > target) {
            return find(arr, start, midle - 1, target);
        }
        if (arr[midle] < target) {
            return find(arr, midle + 1, end, target);
        }
        return -1;
    }

}
