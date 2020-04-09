package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-09 14:33
 */
public class TreeLinkNode {

    public TreeLinkNode left = null;
    public TreeLinkNode right = null;
    public TreeLinkNode next = null; // 指向父结点的指针
    public int val;

    TreeLinkNode(int val) {
        this.val = val;
    }

    public TreeLinkNode(TreeLinkNode left, TreeLinkNode right, TreeLinkNode next, int val) {
        this.left = left;
        this.right = right;
        this.next = next;
        this.val = val;
    }
}
