package com.mydesign.example.ocp.demo1;

import com.mydesign.example.ocp.demo1.handler.ErrorAlertHandler;
import com.mydesign.example.ocp.demo1.handler.TpsAlertHandler;


/**
 * @author hemaoling
 */
public class AlertApplicationContext {
    private AlertRule alertRule;
    private Notification notification;
    private Alert alert;


    private AlertApplicationContext init() {
        alertRule = new AlertRule();
        notification = new Notification();
        alert = new Alert();
        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
        return this;
    }

    public Alert getAlert() {
        return alert;
    }

    private static final AlertApplicationContext instance = new AlertApplicationContext();

    private AlertApplicationContext() {
    }

    public static AlertApplicationContext getInstance() {
        return instance.init();
    }
}
