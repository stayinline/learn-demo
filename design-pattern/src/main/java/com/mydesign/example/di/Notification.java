package com.mydesign.example.di;

public class Notification {

    private MessageSender messageSender;

    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void senfMsg(String msg, String cellphone) {
        this.messageSender.send(cellphone, msg);
    }
}
