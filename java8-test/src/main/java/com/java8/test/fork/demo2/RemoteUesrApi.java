package com.java8.test.fork.demo2;

import com.java8.test.fork.User;

import java.util.List;
import java.util.stream.Collectors;

public class RemoteUesrApi {

    public List<User> queryUserDetail(List<Integer> userIds) {
        return userIds.stream().map(x -> new User(x, x.toString() + "---")).collect(Collectors.toList());
    }

    public User queryUserDetail(Integer x) {
        return new User(x, x.toString() + "---");
    }
}
