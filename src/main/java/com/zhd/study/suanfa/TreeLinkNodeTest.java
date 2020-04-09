package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-09 14:38
 */
public class TreeLinkNodeTest {

    public static void main(String[] args) {


    }


    public static TreeLinkNode nextNode(TreeLinkNode treeLinkNode) {
        if (treeLinkNode.right != null) {
            TreeLinkNode target = treeLinkNode.right;
            while (target.left != null) {
                target = target.left;
            }
            return target;
        } else {
            TreeLinkNode target = treeLinkNode;
            while (target.next != null) {
                TreeLinkNode parent = target.next;
                if (parent.left == target) {
                    return parent;
                }
                target = parent;
            }
        }
        return null;
    }
}
