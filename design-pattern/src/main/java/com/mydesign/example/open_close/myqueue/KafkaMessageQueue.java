package com.mydesign.example.open_close.myqueue;

public class KafkaMessageQueue implements MessageQueue {
    @Override
    public boolean send(String msg) {
        return false;
    }
}
