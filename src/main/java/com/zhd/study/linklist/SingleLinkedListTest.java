package com.zhd.study.linklist;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-09 20:49
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        SingleLinkedList<String> array = new SingleLinkedList<>();

        for (int i = 0; i < 10; i++) {
            array.add("num-" + i);
        }
        System.out.println(array.toString());
        System.out.println(array.size());

        for (int i = 0; i < 10; i++) {
            String first = array.removeFirst();
            System.out.println(first);
        }
        System.out.println(array.toString());
        System.out.println(array.size());

        for (int i = 0; i < 10; i++) {
            array.add("kip-" + i);
        }
        System.out.println(array.toString());
        System.out.println(array.size());

        for (int i = 0; i < 10; i++) {
            String first = array.removeLast();
            System.out.println(first);
        }
        System.out.println(array.toString());
        System.out.println(array.size());

        for (int i = 0; i < 10; i++) {
            array.add("uth-" + i);
        }
        System.out.println(array.toString());
        System.out.println(array.size());

        System.out.println(array.get(9));


    }
}
