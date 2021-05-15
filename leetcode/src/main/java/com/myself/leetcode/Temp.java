package com.myself.leetcode;//package com.example.mytest.leetcode;
//
//public class Temp {
//    public static void main(String[] args) {
//        boolean flag = true;
//        TreeNode treeNode = new TreeNode(1);
//        treeNode.setVal(null);
//        TreeNode i = new TreeNode(2);
//        //这里会报空指针
//        treeNode.setVal(flag ? treeNode.getVal() :-1);
//        //这里通过查看字节码得出：三目运算符会调用静态的Double.valueOf()方法
//        treeNode.setVal(flag ? Double.valueOf(treeNode.getVal()) : Double.valueOf(-1));
//    }
//}
