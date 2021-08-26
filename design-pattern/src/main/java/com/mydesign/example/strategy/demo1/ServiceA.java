package com.mydesign.example.strategy.demo1;

/**
 * @author hemaoling
 */
public class ServiceA extends AbstractServiceCheck {
    @Override
    public boolean checkReq(CommonReq req) {
        System.out.println("//check A req");
        return false;
    }
}
