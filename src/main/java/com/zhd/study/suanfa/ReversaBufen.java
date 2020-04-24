package com.zhd.study.suanfa;

import java.util.Scanner;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-13 18:30
 */
public class ReversaBufen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String nodeStr = sc.nextLine();
        String kStr = sc.nextLine();
        int k = Integer.valueOf(kStr);

        ListNode head = bulid(nodeStr);

        ListNode newHead = reversa(head, k);

        printListNode(newHead);
    }

    private static ListNode reversa(ListNode head, int k) {
        if (rank(head, k)) {
            return head;
        }
        ListNode first = null;
        ListNode next;
        ListNode temp = head;
        //反转
        for (int i = 0; i < k; i++) {
            next = temp.next;
            temp.next = first;
            first = temp;
            temp = next;
        }
        head.next = reversa(temp, k);
        return first;

    }

    private static boolean rank(ListNode head, int k) {
        if (k <= 1 || head == null) {
            return true;
        }

        ListNode temp = head;
        while (--k > 0) {
            if (temp == null || temp.next == null) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    private static void printListNode(ListNode head) {
        boolean isFirst = true;
        while (head != null) {
            if (isFirst) {
                isFirst = false;
            } else {
                System.out.print(" ");
            }
            System.out.print(head.val);
            head = head.next;
        }

    }

    private static ListNode bulid(String nodeStr) {
        if (nodeStr == null || "".equals(nodeStr.trim())) {
            return null;
        }
        String[] arr = nodeStr.split(" ");
        ListNode head = null, tail = null;

        for (int i = 0; i < arr.length; i++) {
            if (head == null) {
                head = new ListNode(Integer.valueOf(arr[i]));
                tail = head;
            } else {
                tail.next = new ListNode(Integer.valueOf(arr[i]));
                tail = tail.next;
            }
        }
        return head;

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
