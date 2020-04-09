package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description 二叉树的构建：
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和
 * 中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * @date: 2020-04-08 21:55
 */
public class TreeNodeTest {

    public static void main(String[] args) {
//        TreeNode top = new TreeNode(5, new TreeNode(3, null, new TreeNode(4)), new TreeNode(6));

        int[] pre = {1, 2, 4, 3, 5, 6};
        int[] in = {4, 2, 1, 5, 3, 6};

        TreeNode treeNode = reConstructBinaryTree(pre, in);
        printFist(treeNode);
    }

    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length == 0 || in.length == 0) {
            return null;
        }
        if (pre.length == 1 || in.length == 1) {
            return new TreeNode(pre[0]);
        }

        int top = pre[0];
        TreeNode treeNode = new TreeNode(top);
        int index = index(in, top);
        int[] leftIn = copyOfRange(in, 0, index);
        int[] leftPre = copyOfRange(pre, 1, leftIn.length + 1);
        int[] rightIn = copyOfRange(in, index + 1, in.length);
        int[] rightPre = copyOfRange(pre, leftPre.length + 1, pre.length);

        treeNode.left = reConstructBinaryTree(leftPre, leftIn);
        treeNode.right = reConstructBinaryTree(rightPre, rightIn);
        return treeNode;
    }

    public static int[] copyOfRange(int[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0) {
            throw new IllegalArgumentException(from + " > " + to);
        }
        int[] copy = new int[newLength];
        System.arraycopy(original, from, copy, 0,
                Math.min(original.length - from, newLength));
        return copy;
    }

    private static int index(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }


    public static void printFist(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.err.println(treeNode.val);
        printFist(treeNode.left);
        printFist(treeNode.right);
    }

    public static void printMiddle(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        printMiddle(treeNode.left);
        System.err.println(treeNode.val);
        printMiddle(treeNode.right);
    }

    public static void printLast(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        printLast(treeNode.left);
        printLast(treeNode.right);
        System.out.println(treeNode.val);
    }


}
