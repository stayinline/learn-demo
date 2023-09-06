package com.java8.test.fork.demo2;

import com.java8.test.fork.User;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        List<Integer> ids = Arrays.asList(1, 2, 3, 2, 2, 3, 4, 4, 2);
        RemoteUesrApi api = new RemoteUesrApi();
        List<User> users = api.queryUserDetail(ids);
        System.out.println(users);
        System.out.println("----------------");

        Function<List<Integer>, List<User>> function = api::queryUserDetail;

        ForkListService service = new ForkListService(ids, 0, ids.size(), function);
        List result = service.compute();
        System.out.println(result);
    }
}
