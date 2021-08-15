package com.mydesign.example.ocp.demo1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hemaoling
 */
@Slf4j
public class Notification {

    public void notifyMsg(NotiNotificationEmergencyLevelEnum levelEnum, String msg) {
        System.out.println(levelEnum.getDesc() + "---" + msg);
    }
}

