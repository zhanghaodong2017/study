package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description 单链表反转
 * @date: 2020-04-08 12:25
 */
public class ReversalNode {

    public static void main(String[] args) {
        Node<Integer> node5 = new Node<>(5, null);
        Node<Integer> node4 = new Node<>(4, node5);
        Node<Integer> node3 = new Node<>(3, node4);
        Node<Integer> node2 = new Node<>(2, node3);
        Node<Integer> node1 = new Node<>(1, node2);

        Node reversal = reversal(node1);
        printNode(reversal);
    }

    public static Node reversal(Node node) {
        //null或者只有一个元素
        if (node == null || node.next == null) {
            return node;
        }
        Node<Integer> temp = null;
        Node<Integer> next = null;
        while (node != null) {
            next = node.next;
            node.next = temp;
            temp = node;
            node = next;
        }
        return temp;
    }

    public static void printNode(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.item);
        printNode(node.next);
    }
}
