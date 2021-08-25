package com.mydesign.example.demo3;

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
