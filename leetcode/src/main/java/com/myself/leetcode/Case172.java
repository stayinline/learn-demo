package com.myself.leetcode;

import java.math.BigInteger;

public class Case172 {

    //暴力法
    public int trailingZeroes2(int n) {
        long ret = 1;
        while (n != 0) {
            ret = ret * (long) n;
            n--;
        }
        int count = 0;
        while (ret != 0) {
            if (ret % 10 == 0) {
                count++;
            }
            ret /= 10;
        }
        return count;
    }

    public int trailingZeroes1(int n) {

        BigInteger nFactorial = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            nFactorial = nFactorial.multiply(BigInteger.valueOf(i));
        }

        int zeroCount = 0;

        while (nFactorial.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
            nFactorial = nFactorial.divide(BigInteger.TEN);
            zeroCount++;
        }

        return zeroCount;
    }

    public int trailingZeroes(int n) {
        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int powerOf5 = 5;
            while (i % powerOf5 == 0) {
                zeroCount += 1;
                powerOf5 *= 5;
            }
        }
        return zeroCount;
    }


}
