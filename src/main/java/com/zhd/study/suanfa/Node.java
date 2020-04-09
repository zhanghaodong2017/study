package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-08 12:24
 */
public class Node<E> {

    E item;
    Node<E> next;

    Node(E element, Node<E> next) {
        this.item = element;
        this.next = next;
    }
}
