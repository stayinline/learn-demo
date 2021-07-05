package com.myself.nio_timer_demo.server;


/**
 * @author hemaoling
 */
public class TimerServer {

    public static void main(String[] args) {
        int port = 8080;
        MultiplexerTimerServer multiplexerTimerServer = new MultiplexerTimerServer(port);
        new Thread(multiplexerTimerServer, "Timer_Server").start();
    }


}