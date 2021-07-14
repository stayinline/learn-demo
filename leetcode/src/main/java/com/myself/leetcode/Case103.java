package com.myself.leetcode;

import java.util.*;

public class Case103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (null == root) {
            return res;
        }
        boolean goLeft = true;
        //初始值必须是true，因为要从右向左开始

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Deque<Integer> curLevelNums = new LinkedList<>();
            // for (int i = 0; i < queue.size(); i++) { ：这种写法是错误的。循环里面会一直往队列中添加元素
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (goLeft) {
                    // 向左边走就把当前值加到最后
                    curLevelNums.offerLast(curNode.val);
                } else {
                    curLevelNums.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            res.add(new ArrayList<>(curLevelNums));
            // 这里也得注意，不是直接赋值 false，而是取反，因为每次遍历完之后需要反向
            goLeft = !goLeft;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        List<List<Integer>> list = new Case103().zigzagLevelOrder(root);

        System.out.println(list);
    }
}
