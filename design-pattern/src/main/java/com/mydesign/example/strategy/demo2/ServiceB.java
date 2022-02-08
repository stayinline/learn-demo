package com.mydesign.example.strategy.demo2;

import com.mydesign.example.strategy.demo1.CommonReq;

public class ServiceB implements BaseService {
    @Override
    public boolean checkReq(CommonReq req) {
        // check b req
        return false;
    }
}
