package com.mydesign.example.di;

public class DemoApp {

    public static void main(String[] args) {
        MessageSender messageSender = new SmsSender();
        Notification notification = new Notification(messageSender);
        notification.senfMsg("hello", "13112341234");
    }
}
