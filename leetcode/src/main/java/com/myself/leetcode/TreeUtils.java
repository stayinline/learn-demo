package com.myself.leetcode;

/**
 * @author hemaoling
 */
public class TreeUtils {
    public static void preOrder(TreeNode root) {
        if (null == root) {
            System.out.print("null");
        }

        System.out.println(root + "->");
        preOrder(root.left);
        preOrder(root.right);
    }
}
