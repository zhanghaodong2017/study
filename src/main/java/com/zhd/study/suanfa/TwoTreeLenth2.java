package com.zhd.study.suanfa;

public class TwoTreeLenth2 {
    private StringBuffer maxCodec;
    private StringBuffer minCodec;
    private int max;
    private int min;

    public static void main(String[] args) {
        TreeNode left = new TreeNode(6, new TreeNode(1), new TreeNode(10));
        TreeNode right = new TreeNode(5, new TreeNode(20), new TreeNode(4));
        TreeNode top = new TreeNode(7, left, right);
        int dis = new TwoTreeLenth2().getDis(top);
        System.out.println(dis);

    }

    public int getDis(TreeNode root) {
        // write code here
        max = root.val;
        min = root.val;

        deepl(root, '0', new StringBuffer());
        int minCount = maxCodec.length() > minCodec.length() ? minCodec.length() : maxCodec.length();
        int index = 0;
        while (index < minCount) {
            if (maxCodec.charAt(index) != minCodec.charAt(index)) {
                break;
            }
            index++;
        }
        return maxCodec.substring(index).length() + minCodec.substring(index).length();

    }

    public void deepl(TreeNode node, char code, StringBuffer codexc) {
        if (node == null) {
            return;
        }
        codexc.append(code);
        if (node.val > max) {
            max = node.val;
            maxCodec = codexc;
        }
        if (node.val < min) {
            min = node.val;
            minCodec = codexc;
        }
        if (node.left != null) {
            deepl(node.left, '0', new StringBuffer(codexc));
        }
        if (node.right != null) {
            deepl(node.right, '1', new StringBuffer(codexc));
        }
    }


}