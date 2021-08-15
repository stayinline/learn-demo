package com.mydesign.example.ocp.demo1;

/**
 * @author hemaoling
 */

public enum NotiNotificationEmergencyLevelEnum {

    URGENCY(1,"紧急"),
    NOMAL(2,"一般");

    private Integer code;
    private String desc;

    NotiNotificationEmergencyLevelEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
