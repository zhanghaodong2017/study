package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-24 19:43
 */
public class TreeNodeSum {


    static int result = 0;

    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(15));
        TreeNode right = new TreeNode(3);
        TreeNode top = new TreeNode(1, left, right);
        printFist(top, new StringBuffer());
        System.out.println(result);
    }

    public static void printFist(TreeNode treeNode, StringBuffer buffer) {
        if (treeNode == null) {
            return;
        }
        buffer.append(treeNode.val);
        String str = buffer.toString();
        if (treeNode.left == null && treeNode.right == null) {
            System.err.println(str);
            result += Integer.valueOf(str);
        } else {
            printFist(treeNode.left, new StringBuffer(str));
            printFist(treeNode.right, new StringBuffer(str));
        }
    }

}
