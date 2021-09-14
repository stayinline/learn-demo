package com.mydesign.example.open_close;

import com.mydesign.example.demo1.Notification;
import com.mydesign.example.open_close.myfromatter.MeaasgeFromatter;
import com.mydesign.example.open_close.myqueue.MessageQueue;

/**
 * @author hemaoling
 */
public class TestDemo {
    private MessageQueue queue;


    public TestDemo(MessageQueue queue) {
        this.queue = queue;
    }

    public void sendNotification(Notification notification, MeaasgeFromatter<Notification> meaasgeFromatter) {
        String formatStr = meaasgeFromatter.format(notification);

        queue.send(formatStr);

    }

    public static void main(String[] args) {

    }
}
