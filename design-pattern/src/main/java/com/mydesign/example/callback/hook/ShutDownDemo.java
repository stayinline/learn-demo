package com.mydesign.example.callback.hook;

/**
 * 异步回调
 *
 * @author hemaoling
 */
public class ShutDownDemo {

    private static class ShutDownHook extends Thread {
        @Override
        public void run() {
            System.out.println("Alert, server was shutdowned!!!!!!");
        }
    }


    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutDownHook());
    }
}
