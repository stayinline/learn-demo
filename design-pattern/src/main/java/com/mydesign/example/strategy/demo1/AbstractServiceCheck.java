package com.mydesign.example.strategy.demo1;

public abstract class AbstractServiceCheck {

    public boolean checkReq(CommonReq req) {
        System.out.println("默认的校验处理机制");
        return false;
    }
}
