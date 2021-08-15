package com.mydesign.example.ocp.demo1.handler;

import com.mydesign.example.ocp.demo1.ApiStatInfo;
import com.mydesign.example.ocp.demo1.NotiNotificationEmergencyLevelEnum;
import com.mydesign.example.ocp.demo1.Notification;
import com.mydesign.example.ocp.demo1.AlertRule;

/**
 * @author hemaoling
 */
public class ErrorAlertHandler extends AlertHandler {

    public ErrorAlertHandler(AlertRule alertRule, Notification notification) {
        super(alertRule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {

        if (apiStatInfo.getErrorCount() > alertRule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {
            String msg = "tps 超过阈值了！";
            notification.notifyMsg(NotiNotificationEmergencyLevelEnum.URGENCY, msg);
        }

    }
}
