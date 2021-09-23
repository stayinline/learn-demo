package com.mydesign.example.interfases.demo1;

import java.util.List;

public class Statistics {

    private Long max;
    private Long min;
    private Long avg;
    private Long sum;


    /**
     * 错误示范！！！
     * 这种设计和封装就不够单一
     * 每个接口(函数)功能都应该是单一的
     */
    public Statistics count(List<Long> list) {
        Statistics statistics = new Statistics();
        // 计算max
        // 计算avg
        // 等等逻辑
        return statistics;
    }

    public Long getMax(List<Long> list) {
        return 1L;
    }
}
