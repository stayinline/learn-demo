package com.myself.leetcode.case297;


import com.myself.leetcode.TreeNode;
import com.myself.leetcode.TreeUtils;

import java.util.LinkedList;
import java.util.Queue;

public class CodecLevelOrder {

    String SPLIT = ",";
    String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                sb.append(node.val).append(SPLIT);
            } else {
                sb.append(NULL).append(SPLIT);
                continue;
            }
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] list = data.split(SPLIT);
        //层序遍历的第一个一定是根节点
        String rootVal = list[0];
        TreeNode root = new TreeNode(Integer.parseInt(rootVal));
        // 队列 q 记录父节点，将 root 加入队列
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        for (int i = 1; i < list.length; ) {
            // 队列中存的都是父节点
            TreeNode parent = q.poll();
            if (null == parent) {
                continue;
            }
            // 父节点对应的左侧子节点的值
            String left = list[i++];
            if (!left.equals(NULL)) {
                parent.left = new TreeNode(Integer.parseInt(left));
                q.offer(parent.left);
            } else {
                parent.left = null;
            }
            // 父节点对应的右侧子节点的值
            String right = list[i++];
            if (!right.equals(NULL)) {
                parent.right = new TreeNode(Integer.parseInt(right));
                q.offer(parent.right);
            } else {
                parent.right = null;
            }
        }
        return root;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode right2 = new TreeNode(4);
        root.left = left;
        root.right = right;
        left.left = null;
        left.right = right2;


        String serialize = new CodecLevelOrder().serialize(root);
        TreeNode root3 = new CodecLevelOrder().deserialize(serialize);
        TreeUtils.preOrder(root3);

    }
}