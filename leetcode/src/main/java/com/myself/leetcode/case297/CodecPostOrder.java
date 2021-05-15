package com.myself.leetcode.case297;

import com.myself.leetcode.TreeNode;

import java.util.LinkedList;

public class CodecPostOrder {

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
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
        sb.append(root.val).append(SPLIT);
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
        //后序遍历的最后一个元素一定是根节点
        String rootVal = list.removeLast();
        //如果是null也要处理
        if (rootVal.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(rootVal));
        //先找右孩子，因为list的出队方式是从右边出的
        root.right = deserializeHelper(list);
        //再找左孩子
        root.left = deserializeHelper(list);
        return root;
    }
}