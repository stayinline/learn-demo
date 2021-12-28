package com.mydesign.example.observer.demo2.observer;

import javax.annotation.Resource;

public class RegNotifucationObserver implements RegObserver {

    @Resource
    protected NotificationService notificationService;


    @Override
    public void handleRegSuccess(Long uid) {
        String message = "welcome to ......";
        notificationService.sendInboxMessage(uid, message);
    }
}
