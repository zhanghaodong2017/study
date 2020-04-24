package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description 删除连续重复的节点
 * @date: 2020-04-13 16:22
 */
public class ChongFuNode {

    public static void main(String[] args) {
        ListNode pHead = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(5)))))));
        ListNode listNode = deleteDuplication(pHead);
        printNode(listNode);

    }

    public static ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode next = pHead.next;
        if (pHead.val == next.val) {
            while (next != null && pHead.val == next.val) {
                next = next.next;
            }
            return deleteDuplication(next);
        } else {
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }

    public static void printNode(ListNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        printNode(node.next);
    }
}
