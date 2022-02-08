package com.mydesign.example.strategy.demo2;

import com.mydesign.example.strategy.demo1.AbstractServiceCheck;
import com.mydesign.example.strategy.demo1.CommonReq;

/**
 * @author hemaoling
 */
public class ServiceA implements BaseService {
    @Override
    public boolean checkReq(CommonReq req) {
        System.out.println("//check A req");
        return false;
    }
}
