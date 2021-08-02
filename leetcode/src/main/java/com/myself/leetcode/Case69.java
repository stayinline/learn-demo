package com.myself.leetcode;

public class Case69 {

    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        double x0 = x, c = x;
        while (true) {
            double xi = 0.5 * (x0 + c / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }

        return (int) x0;

    }

    public static int mySqrt2(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        mySqrt(2);
        mySqrt2(2);
        System.out.println(mySqrt2(2));
        System.out.println(mySqrt(2));

    }
}
