package com.mydesign.example.ocp.demo1.handler;

import com.mydesign.example.ocp.demo1.AlertRule;
import com.mydesign.example.ocp.demo1.ApiStatInfo;
import com.mydesign.example.ocp.demo1.Notification;

/**
 * @author hemaoling
 */
public abstract class AlertHandler {
    protected AlertRule alertRule;
    protected Notification notification;

    public AlertHandler(AlertRule alertRule, Notification notification) {
        this.alertRule = alertRule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
