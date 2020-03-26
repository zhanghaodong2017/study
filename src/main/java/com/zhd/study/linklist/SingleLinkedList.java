package com.zhd.study.linklist;

import java.io.Serializable;
import java.util.*;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-09 20:25
 */
public class SingleLinkedList<E> implements List<E>, Deque<E>, Cloneable, Serializable {
    private int size;

    private SingleLinkedList.Node first = new Node("s", null);//哨兵
    private SingleLinkedList.Node last = first;


    public SingleLinkedList() {

    }

    @Override
    public void addFirst(E e) {
        Node newNode = new Node(e, first.next);
        first.next = newNode;
        ++size;
    }

    @Override
    public void addLast(E e) {
        linkLast(e);
    }

    @Override
    public boolean offerFirst(E e) {
        //TODO
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        //TODO
        return false;
    }

    @Override
    public E removeFirst() {
        if (size == 0) {
            return null;
        }
        Node targetNode = first.next;
        first.next = targetNode.next;
        targetNode.next = null;
        size--;
        if (size == 0) {
            last = first;
        }
        return (E) targetNode.item;
    }

    @Override
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        Node targetNode = last;
        Node previousNode = getNode(size - 2);
        last = previousNode;
        last.next = null;
        --size;
        return (E) targetNode.item;
    }

    private Node getNode(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("越界");
        }
        Node node = this.first;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public E pollFirst() {
        return removeFirst();
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        Node node = getNode(index);
        return (E) node.item;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    private void linkLast(E e) {
        Node node = new Node(e, null);
        this.last.next = node;
        this.last = node;
        ++size;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        SingleLinkedList.Node node = this.first.next;
        for (int i = 0; i < size; i++) {
            buffer.append("[");
            buffer.append(node.item + "");
            buffer.append("] ");
            node = node.next;
        }
        return buffer.toString();
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
