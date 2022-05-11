package com.mydesign.example.chain_of_responsibility.demo1;

/**
 * @author hemaoling
 */
public class ChainOfResponsibilityDemo1 {

    public static void main(String[] args) {
        AbstractLogger logger = getLoggerChain();
        logger.logMsg(AbstractLogger.INFO, "一条info级别的日志");
        logger.logMsg(AbstractLogger.ERROR, "一条error级别的日志");
        logger.logMsg(AbstractLogger.DEBUG, "一条debug级别的日志");

        /*分析：
         * （1）info的级别为1，只会被打印一次，也就是info的责任链，
         * 只有一个节点，nextLogger为null
         *
         * 这是一条info级别的日志：一条info级别的日志
         *
         * （2）error的级别为2，会被打印两次，也就是error的责任链有两个节点
         *
         * 这是一条info级别的日志：一条error级别的日志
         * 这是一条error级别的日志：一条error级别的日志
         *
         *
         * （3）debug的级别为3，会被打印3次，也就是debug的责任链有3个节点
         * 这是一条info级别的日志：一条debug级别的日志
         * 这是一条error级别的日志：一条debug级别的日志
         * 这是一条debug级别的日志：一条debug级别的日志
         */
    }

    private static AbstractLogger getLoggerChain() {
        AbstractLogger infoLogger = new InfoLogger(1);
        AbstractLogger errorLogger = new ErrorLogger(2);
        AbstractLogger debugLogger = new DebugLogger(3);
        infoLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(debugLogger);
        return infoLogger;
    }
}
