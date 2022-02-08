package com.mydesign.example.strategy;

import com.mydesign.example.strategy.demo1.CommonReq;

/**
 * 不好的编码：
 * 冗长的if-else判断或者Switch-case
 */
public class BadCase {
    void yourMethod(CommonReq req) {
        String serviceInfo = getServiceInfo();
        boolean checkRet = checkReq(req, serviceInfo);
        if (!checkRet) {
            System.out.println("校验未通过");
            return;
        }
        // deal you business

    }

    private boolean checkReq(CommonReq req, String serviceInfo) {
        boolean checkRet = false;
        switch (serviceInfo) {
            case "A":
                checkRet = checkAReq(req);
                break;
            case "B":
                checkRet = checkBReq(req);
                break;
            case "C":
                checkRet = checkCReq(req);
                break;
            default:
                break;
        }
        return checkRet;
    }


    private boolean checkCReq(CommonReq req) {
        return false;
    }

    private boolean checkAReq(CommonReq req) {
        return false;
    }

    private boolean checkBReq(CommonReq req) {
        return false;
    }

    private String getServiceInfo() {
        return "A";
    }

    public static void main(String[] args) {
        new BadCase().yourMethod(new CommonReq());
    }

}
