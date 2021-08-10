package com.myself.leetcode;

public class Case371 {


    public static int getSum(int a, int b) {
        int noCarrySum = a ^ b;//相加后的结果，不考虑进位
        int carry = (a & b) << 1;//相加后产生的进位
        while (carry != 0) {//有进位时就对无进位结果和进位相加，
            //相加的原理仍然不变，无进位就说明已经得到结果，就退出循环
            int tmp1 = noCarrySum;//临时变量
            int tmp2 = carry;//临时变量
            noCarrySum = tmp1 ^ tmp2;
            carry = (tmp1 & tmp2) << 1;
        }
        return noCarrySum;
    }


    public static void main(String[] args) {
        int a = 12, b = 3;
        System.out.println(getSum(a, b));
    }
}
