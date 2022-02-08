package com.mydesign.example.observer.demo2.observer;

import javax.annotation.Resource;

/**
 * @author hemaoling
 */
public class RegNotificationObserver implements RegObserver {

    @Resource
    protected NotificationService notificationService;


    @Override
    public void handleRegSuccess(Long uid) {
        String message = "welcome to ......";
        notificationService.sendInboxMessage(uid, message);
    }
}
