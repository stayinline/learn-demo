package com.my.clean.code.first_name;

public class First {


    public int execute(String args) {

        return args.length();
    }

    public String testSame(String args) {

        return args;
    }



    public UserResp testParam(UserReq userReq) {

        return new UserResp();
    }
}
