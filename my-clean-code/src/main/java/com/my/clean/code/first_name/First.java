package com.my.clean.code.first_name;

import java.util.ArrayList;

public class First {


    public int execute(String args) {

        return args.length();
    }

    public String testSame(String args) {

        return args;
    }


    public UserResp testParam(UserReq userReq) {

        return new UserResp(userReq.getName(), userReq.getUid(), new ArrayList<>());
    }
}
