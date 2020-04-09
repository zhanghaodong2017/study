package com.zhd.study.suanfa;

import java.util.Stack;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-09 10:06
 */
public class TwoStack {

    private Stack<Integer> stack1 = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();

    public static void main(String[] args) {
        TwoStack stack = new TwoStack();

        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(stack.pop());
        }
    }

    public void push(int node) {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }

    public int pop() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

}
