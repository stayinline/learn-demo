package com.mydesign.example.strategy.demo3;

import com.mydesign.example.strategy.demo2.BaseService;
import com.mydesign.example.strategy.demo2.ServiceA;
import com.mydesign.example.strategy.demo2.ServiceB;

import java.util.HashMap;
import java.util.Map;

public class ServiceSimpleFeactory {


    /**
     * 简单的创建实现类
     *
     * @param info
     * @return
     */
    public static BaseService ge1(String info) {
        if (null == info || "".equals(info)) {
            System.out.println("param is error");
            return null;
        }
        switch (info) {
            case "A":
                return new ServiceA();
            case "B":
                return new ServiceB();
            default:
                System.out.println("service get error");
                return null;
        }
    }


    /****************************简单工厂实现创建业务类******************************/
    private static Map<String, BaseService> map = new HashMap<>();

    static {
        map.put("A", new ServiceA());
        map.put("B", new ServiceB());
    }

    public static BaseService get(String serviceInfo) {
        return map.get(serviceInfo);
    }

    /****************************简单工厂实现创建业务类******************************/

}
