package com.mydesign.example.strategy.demo2;


import com.mydesign.example.strategy.demo1.CommonReq;


/**
 * 基于接口的策略模式
 */
public class Test {


    public void yourMethod(CommonReq req) {
        String serviceInfo = getServiceInfo();
        boolean checkRet = ServiceStrategy.getService(serviceInfo).checkReq(req);
        if (!checkRet) {
            System.out.println("校验未通过");
            return;
        }
        // deal you business

    }


    private String getServiceInfo() {
        return "A";
    }

    public static void main(String[] args) {
        new Test().yourMethod(new CommonReq());
    }

}
