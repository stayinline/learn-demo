package com.mydesign.example.demo3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hemaoling
 */
public class ServiceStrategy {

    private static Map<String, BaseService> map = new HashMap<>();

    static {
        map.put("A", new ServiceA());
        map.put("B", new ServiceB());
    }

    public static BaseService getService(String serviceInfo) {
        return map.get(serviceInfo);
    }
}
