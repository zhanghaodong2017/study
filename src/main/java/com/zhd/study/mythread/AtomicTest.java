package com.zhd.study.mythread;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-16 16:09
 */
public class AtomicTest {


    public static void main(String[] args) {
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        int i = array.addAndGet(2, 10);
        System.out.println(i);


        AtomicReference<Member> ref = new AtomicReference<>();
        Member memA = new Member("张三", 20);
        Member memB = new Member("李四", 30);
        ref.set(memA);
        // 对象引用变更只得依靠地址比较“==”
        ref.compareAndSet(memA, memB);
        System.out.println(ref);  // 结果：name = 李四、age = 30

        AtomicLongFieldUpdater.newUpdater(AtomicTest.class.getClass(), "");
//
//        AbstractOwnableSynchronizer;
//        AbstractQueuedSynchronizer;
//        AbstractQueuedLongSynchronizer;


    }
}


class Member {
    private String name;
    private int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name = " + this.name + "、age = " + this.age;
    }
}