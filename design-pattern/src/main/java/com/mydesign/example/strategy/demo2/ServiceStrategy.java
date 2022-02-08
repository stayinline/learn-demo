package com.mydesign.example.strategy.demo2;


import java.util.HashMap;
import java.util.Map;

/**
 * @author hemaoling
 */
public class ServiceStrategy {
    /*
     * 基于接口实现的策略
     */
    private static Map<String, BaseService> map = new HashMap<>();

    static {
        map.put("A", new ServiceA());
        map.put("B", new ServiceB());
    }

    public static BaseService getService(String serviceInfo) {
        return map.get(serviceInfo);
    }

}
