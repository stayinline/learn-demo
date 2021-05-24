package com.mydesign.example.ocp.demo1;

import com.mydesign.example.ocp.demo1.handler.AlertHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hemaoling
 * 告警类
 */
public class Alert {

    private List<AlertHandler> handlers = new ArrayList<>();

    public void addAlertHandler(AlertHandler alertHandler) {
        handlers.add(alertHandler);
    }

    public void check(ApiStatInfo apiStatInfo) {
        handlers.forEach(x -> x.check(apiStatInfo));
    }
}
