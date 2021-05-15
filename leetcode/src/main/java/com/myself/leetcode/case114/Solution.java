package com.myself.leetcode.case114;


import com.myself.leetcode.TreeNode;

public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        //后序遍历位置

        TreeNode oldLeft = root.left;
        TreeNode oldRight = root.right;

        //现左置为null，原左当现右
        root.left = null;
        root.right = oldLeft;

        //原左最右节点，原右挂其上
        TreeNode tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        tmp.right = oldRight;
    }
}
 