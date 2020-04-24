package com.zhd.study.suanfa;

import java.util.Scanner;

/**
 * @author: zhanghaodong
 * @description 对称二叉树
 * @date: 2020-04-09 15:20
 */
public class DuiChenTree {

    public static void main(String[] args) {
        TreeNode top = new TreeNode(5, new TreeNode(6), new TreeNode(6));
        System.out.println(isSymmetrical(top));

        Scanner sc =  new Scanner(System.in);
        String str = sc.nextLine();
        int k = Integer.valueOf(str);
    }

    public static boolean isSymmetrical(TreeNode node) {
        if (node == null) {
            return true;
        }
        return isSymmetrical(node.left, node.right);
    }

    public static boolean isSymmetrical(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        if (n1.val != n2.val) {
            return false;
        }
        return isSymmetrical(n1.left, n2.right) && isSymmetrical(n2.left, n1.right);

    }
}
