package com.java8.test.lambda.demo2;

import java.util.ArrayList;
import java.util.List;

public class BusinessTest {

    private static List<Integer> black_list = new ArrayList<>();

    public static void alert(int id) {
        if (!black_list.contains(id)) {
            System.out.println("该用户不是黑名单用户");
        }
    }

}
