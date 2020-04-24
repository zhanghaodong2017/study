package com.zhd.study.mythread;

import java.util.Arrays;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-16 12:56
 */
public class KMPTest {

    public static void main(String[] args) {
        String parent = "abcabcabcabcd";
        String child = "abcabcd";

        int index = kmp(parent, child);
        System.out.println(index);


    }

    private static int baoli(String parent, String child) {
        if (parent == null || child == null) {
            return -1;
        }
        if (parent.equals("") && child.equals("")) {
            return 0;
        }
        if (parent.equals("") || child.equals("")) {
            return -1;
        }
        if (child.length() > parent.length()) {
            return -1;
        }

        for (int i = 0; i <= parent.length() - child.length(); i++) {
            for (int j = 0; j < child.length(); j++) {
                if (parent.charAt(i + j) != child.charAt(j)) {
                    break;
                }
                if (j == child.length() - 1) {
                    return i;
                }
            }

        }

        return -1;
    }

    private static int baoli222(String parent, String child) {
        if (parent == null || child == null) {
            return -1;
        }
        if (parent.equals("") && child.equals("")) {
            return 0;
        }
        if (parent.equals("") || child.equals("")) {
            return -1;
        }
        if (child.length() > parent.length()) {
            return -1;
        }
        int i = 0, j = 0;

        while (i < parent.length() && j < child.length()) {
            if (parent.charAt(i) == child.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == child.length()) {
            return i - j;
        } else {
            return -1;
        }

    }

    private static int kmp(String parent, String child) {
        if (parent == null || child == null) {
            return -1;
        }
        if (parent.equals("") && child.equals("")) {
            return 0;
        }
        if (parent.equals("") || child.equals("")) {
            return -1;
        }
        if (child.length() > parent.length()) {
            return -1;
        }
        int[] next = getNext(child);
        System.out.println(Arrays.toString(next));
        int i = 0, j = 0;

        while (i < parent.length() && j < child.length()) {
            if (j == -1 || parent.charAt(i) == child.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j]; // j回到指定位置;
            }
        }

        if (j == child.length()) {
            return i - j;
        } else {
            return -1;
        }

    }

    public static int[] getNext(String ps) {

        char[] p = ps.toCharArray();

        int[] next = new int[p.length];

        next[0] = -1;

        int j = 0;

        int k = -1;

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {

                if (p[++j] == p[++k]) { // 当两个字符相等时要跳过

                    next[j] = next[k];

                } else {

                    next[j] = k;

                }

            } else {

                k = next[k];

            }

        }

        return next;

    }

}
