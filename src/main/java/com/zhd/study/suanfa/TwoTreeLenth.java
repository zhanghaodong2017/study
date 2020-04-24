package com.zhd.study.suanfa;


import java.util.HashMap;

/**
 * [34,19,45,22,29,31,35,36,4,17,2,24,28,14,20,13,30,12,25,9,1,8,37,42,3,23,11,7,6,10,5,21,26,38,44,27,32,15,18,41,40,39,43,16,33,#]
 */

public class TwoTreeLenth {

    private HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
    private HashMap<TreeNode, Integer> cengjiMap = new HashMap<>();
    private TreeNode max;
    private TreeNode min;

    public static void main(String[] args) {
        TreeNode left = new TreeNode(6, new TreeNode(1), new TreeNode(10));
        TreeNode right = new TreeNode(5, new TreeNode(20), new TreeNode(4));
        TreeNode top = new TreeNode(7, left, right);
        int dis = new TwoTreeLenth().getDis(top);
        System.out.println(dis);

    }

    public int getDis(TreeNode root) {
        // write code here
        max = root;
        min = root;
        deepl(root, 1);

        int maxcj = cengjiMap.get(max), m = maxcj;
        int mincj = cengjiMap.get(min), n = mincj;

        TreeNode maxpNode = max;
        TreeNode minpNode = min;
        while (maxcj > mincj) {
            maxpNode = parentMap.get(maxpNode);
            maxcj--;
        }

        while (maxcj < mincj) {
            minpNode = parentMap.get(minpNode);
            mincj--;
        }
        if (maxpNode == min) {
            return m - n;
        }
        if (minpNode == max) {
            return n - m;
        }

        while (minpNode != maxpNode) {
            maxcj--;
            mincj--;
            maxpNode = parentMap.get(maxpNode);
            minpNode = parentMap.get(minpNode);
        }
        return m + n - maxcj - mincj;

    }

    public void deepl(TreeNode node, int cengji) {
        if (node == null) {
            return;
        }
        if (max.val < node.val) {
            max = node;
        }
        if (min.val > node.val) {
            min = node;
        }
        cengjiMap.put(node, cengji);
        cengji++;
        if (node.left != null) {
            parentMap.put(node.left, node);
            deepl(node.left, cengji);
        }
        if (node.right != null) {
            parentMap.put(node.right, node);
            deepl(node.right, cengji);
        }

    }


}