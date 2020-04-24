package com.zhd.study.suanfa;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-24 14:42
 */
public class HuffmanTree {
    private Node root;
    private Node[] nodes;//构建哈夫曼树

    public void createHuffman(int[] weights) {
        //优先队列，用于辅助构建哈夫曼树
        Queue<Node> nodeQueue = new PriorityQueue<>();
        nodes = new Node[weights.length];
        //构建森林，初始化nodes数组
        for (int i = 0; i < weights.length; i++) {
            nodes[i] = new Node(weights[i]);
            nodeQueue.add(nodes[i]);
        }

        //主循环，当结点队列只剩一个结点时结束
        while (nodeQueue.size() > 1) {//从结点队列选择权值最小的两个结点
            Node left = nodeQueue.poll();
            Node right = nodeQueue.poll();//创建新结点作为两结点的父节点
            Node parent = new Node(left.weight + right.weight, left, right);
            nodeQueue.add(parent);
        }
        root = nodeQueue.poll();
    }

    //按照前序遍历输出
    private void outputNode(Node head) {
        if (head == null) {
            return;
        }

        System.out.println(head.weight);
        outputNode(head.lChild);
        outputNode(head.rChild);
    }

    //按照前序遍历输出
    public void output(HuffmanTree tree) {
        if (tree == null) {
            return;
        }
        if (tree.root == null) {
            return;
        }
        outputNode(tree.root);
    }

    public static class Node implements Comparable<Node> {
        int weight;
        Node lChild;
        Node rChild;

        public Node(int weight) {
            this.weight = weight;
        }

        public Node(int weight, Node lChild, Node rChild) {
            this.weight = weight;
            this.lChild = lChild;
            this.rChild = rChild;
        }

        @Override
        public int compareTo(Node o) {
            return new Integer(this.weight).compareTo(new Integer(o.weight));
        }
    }
}
