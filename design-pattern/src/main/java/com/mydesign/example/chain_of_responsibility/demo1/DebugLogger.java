package com.mydesign.example.chain_of_responsibility.demo1;

public class DebugLogger extends AbstractLogger {

    public DebugLogger(int level) {
        this.level = level;
    }

    @Override
    protected void writeMsg(String msg) {
        System.out.println("这是一条debug级别的日志：" + msg);
    }
}
