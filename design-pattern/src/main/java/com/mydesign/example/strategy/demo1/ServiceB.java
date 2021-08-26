package com.mydesign.example.strategy.demo1;

public class ServiceB extends AbstractServiceCheck  {
    @Override
    public boolean checkReq(CommonReq req) {
        // check b req
        return false;
    }
}
