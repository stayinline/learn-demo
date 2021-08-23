package com.mydesign.example.demo1;


/**
 * @author hemaoling
 */
public class Notification {

    public void notifyMsg(NotiNotificationEmergencyLevelEnum levelEnum, String msg) {
        System.out.println(levelEnum.getDesc() + "---" + msg);
    }
}

