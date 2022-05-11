package com.mydesign.example.chain_of_responsibility.demo1;

public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void writeMsg(String msg) {
        System.out.println("这是一条error级别的日志：" + msg);
    }
}
