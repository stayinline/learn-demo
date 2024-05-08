package com.myself.bailongma;

public class Test1 {

    public static void main(String[] args) {
        int val = getAnInt("1234");
        System.out.println(val);
    }

    private static int getAnInt(String s) {
        int sum = 0;
        char[] charArray = s.toCharArray();
        int a = 1;
        for (int i = charArray.length - 1; i >= 0; i--) {
            sum += a * (charArray[i] - '0');
            a *= 10;
        }
        return sum;
    }



}
