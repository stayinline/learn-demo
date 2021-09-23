package com.mydesign.example.interfases.demo2.v2;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleUpdater {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private long initDelayInSeconds;
    private HotUpdate hotUpdate;


    public ScheduleUpdater(long initDelayInSeconds, HotUpdate updater) {
        this.initDelayInSeconds = initDelayInSeconds;
        this.hotUpdate = updater;
    }


    public void run() {
        executor.schedule(() -> hotUpdate.updateConfig(), initDelayInSeconds, TimeUnit.MILLISECONDS);
    }
}
