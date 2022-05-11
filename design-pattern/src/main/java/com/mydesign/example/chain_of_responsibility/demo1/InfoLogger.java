package com.mydesign.example.chain_of_responsibility.demo1;

public class InfoLogger extends AbstractLogger {

    public InfoLogger(int level) {
        this.level = level;
    }

    @Override
    protected void writeMsg(String msg) {
        System.out.println("这是一条info级别的日志：" + msg);
    }
}
