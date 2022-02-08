package com.mydesign.example.strategy.demo3;


import com.mydesign.example.strategy.demo2.BaseService;

/**
 * @author hemaoling
 */
public class ServiceStrategy {
    /*
     * 基于工厂模式实现的策略
     */
    public static BaseService getService(String serviceInfo) {
        return ServiceSimpleFeactory.get(serviceInfo);
    }

}
