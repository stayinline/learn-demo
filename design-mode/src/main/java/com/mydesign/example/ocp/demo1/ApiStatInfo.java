package com.mydesign.example.ocp.demo1;

import lombok.Data;

@Data
public class ApiStatInfo {

    private String api;
    private Long requestCount;
    private Long errorCount;

    /**
     * 持续时间（单位：秒）
     */
    private Long durationOfSeconds;

    private Long timeoutCount;
}
