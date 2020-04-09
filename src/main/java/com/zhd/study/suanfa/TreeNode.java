package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-08 21:54
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int x) {
        this.val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
