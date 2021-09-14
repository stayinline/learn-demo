package com.mydesign.example.open_close.myqueue;

public class RocketMessageQueue implements MessageQueue {
    @Override
    public boolean send(String msg) {
        return false;
    }
}
