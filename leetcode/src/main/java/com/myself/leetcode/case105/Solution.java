package com.myself.leetcode.case105;


import com.myself.leetcode.TreeNode;

public class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
                                     int[] inorder, int inStart, int inEnd) {

        if (inStart > inEnd || preStart > preEnd) {
            return null;
        }

        //第一步，前序遍历的第一个一定是根节点
        int rootVal = preorder[preStart];

        //第二步，在中序遍历数组中找到根节点位置，然后将中序数组一分为二
        int idx = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                idx = i;
                break;
            }
        }

        TreeNode root = new TreeNode(rootVal);

        //第三步：计算左数节点个数，来确定前序遍历数组的划分界限
        int leftCount = idx - inStart;

        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftCount,
                inorder, inStart, idx - 1);

        root.right = buildTreeHelper(preorder, preStart + leftCount + 1, preEnd,
                inorder, idx + 1, inEnd);

        return root;

    }

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (i + 6 == j + 8) {
                    System.out.println(i + ":" + j);
                    continue;
                }
            }
        }
    }



}