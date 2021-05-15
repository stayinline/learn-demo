package com.myself.leetcode.case297;


import com.myself.leetcode.TreeNode;

import java.util.LinkedList;

public class Codec {

    String SPLIT = ",";
    String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SPLIT);
            return;
        }
        sb.append(root.val).append(SPLIT);
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> list = new LinkedList<>();
        //切分之后存入数组
        for (String node : data.split(SPLIT)) {
            list.add(node);
        }
        TreeNode root = deserializeHelper(list);
        return root;

    }

    private TreeNode deserializeHelper(LinkedList<String> list) {
        if (list.isEmpty()) {
            return null;
        }
        //先序遍历的第一个元素一定是根节点
        String rootVal = list.removeFirst();
        //如果是null也要处理
        if (rootVal.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(rootVal));
        //递归找左孩子和右孩子
        root.left = deserializeHelper(list);
        root.right = deserializeHelper(list);
        return root;
    }
}