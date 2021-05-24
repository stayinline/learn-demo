package com.mydesign.example.ocp.demo1;

import lombok.Data;

/**
 * @author hemaoling
 */
@Data
public class TpsAlertRule extends AlertRule {
    private String api = "/test/my/api";
    private Long maxTps = 1L;
}