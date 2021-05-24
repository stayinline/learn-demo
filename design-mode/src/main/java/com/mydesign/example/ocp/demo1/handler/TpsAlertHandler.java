package com.mydesign.example.ocp.demo1.handler;

import com.mydesign.example.ocp.demo1.AlertRule;
import com.mydesign.example.ocp.demo1.ApiStatInfo;
import com.mydesign.example.ocp.demo1.NotiNotificationEmergencyLevelEnum;
import com.mydesign.example.ocp.demo1.Notification;

/**
 * @author hemaoling
 */
public class TpsAlertHandler extends AlertHandler {

    public TpsAlertHandler(AlertRule alertRule, Notification notification) {
        super(alertRule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {

        long tps = apiStatInfo.getRequestCount() / apiStatInfo.getDurationOfSeconds();

        if (tps > alertRule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {
            String msg = "tps 超过阈值了！";
            notification.notifyMsg(NotiNotificationEmergencyLevelEnum.URGENCY, msg);
        }

    }
}
