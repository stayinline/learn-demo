package com.mydesign.example.chain_of_responsibility.demo1;

public abstract class AbstractLogger {

    public static int INFO = 1;
    public static int ERROR = 2;
    public static int DEBUG = 3;

    protected int level;

    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMsg(int level, String msg) {
        if (level >= this.level) {
            writeMsg(msg);
        }
        if (nextLogger != null) {
            nextLogger.logMsg(level, msg);
        }
    }

    protected abstract void writeMsg(String msg);
}
