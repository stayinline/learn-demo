package com.mydesign.example.strategy.demo1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hemaoling
 */
public class ServiceStrategy {


    /*
     * 基于接口实现的策略
     */
//    private static Map<String, BaseService> map = new HashMap<>();
//
//    static {
//        map.put("A", new ServiceA());
//        map.put("B", new ServiceB());
//    }
//
//    public static BaseService getService(String serviceInfo) {
//        return map.get(serviceInfo);
//    }


    /**
     * 基于抽象类实现的策略
     */
    private static Map<String, AbstractServiceCheck> map2 = new HashMap<>();

    static {
        map2.put("A", new ServiceA());
        map2.put("B", new ServiceB());
    }

    public static AbstractServiceCheck getService(String serviceInfo) {
        return map2.get(serviceInfo);
    }
}
