package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-23 14:30
 */
public class ErFenFind {
    public static void main(String[] args) {


        int[] arr = {2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4};
//        int[] arr = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 6, 6, 6, 6, 6};
        int n = 3;
//        int result = findIndex(arr, n);
//        int result1 = findMaxIndex(arr, n);
//        int result2 = findMinIndex(arr, n);
//        System.out.println(result);
//        System.out.println(result1);
//        System.out.println(result2);
        int num = findNum(arr, n);
        System.out.println(num);


    }

    public static int findNum(int[] arr, int n) {
        int index = findIndex(arr, n);
        if (index < 0) {
            return 0;
        }
        int maxIndex = findMaxIndex(arr, n);
        int minIndex = findMinIndex(arr, n);
        return minIndex - maxIndex - 1;

    }


    public static int findIndex(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr[0] > n || arr[arr.length - 1] < n) {
            return -1;
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
                return mid;
            }
        }

        return -1;
    }

    //小于n的最大值，-1 表示不存在n
    public static int findMaxIndex(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr[0] >= n) {
            return -1;
        }

        int head = 0, tail = arr.length - 1, mid = 0;

        while (head <= tail) {
            mid = (tail + head) / 2;
            int value = arr[mid];
            if (arr[mid] < n && arr[mid + 1] == n) {
                return mid;
            }
            if (value >= n) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }
        return -1;
    }

    //等于n的最大下标，-1 表示不存在n
    public static int findIndexMax(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr[0] >= n) {
            return -1;
        }

        int head = 0, tail = arr.length - 1, mid = 0;

        while (head <= tail) {
            mid = (tail + head) / 2;
            int value = arr[mid];
            if (arr[mid - 1] < n && arr[mid] == n) {
                return mid;
            }
            if (value >= n) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }
        return -1;
    }

    //大于n的最小值，-1 表示不存在n
    public static int findMinIndex(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr[arr.length - 1] <= n) {
            return arr.length;
        }

        int head = 0, tail = arr.length - 1, mid = 0;

        while (head <= tail) {
            mid = (tail + head) / 2;
            int value = arr[mid];
            if (arr[mid - 1] == n && arr[mid] > n) {
                return mid;
            }
            if (value > n) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }
        return -1;
    }
}
