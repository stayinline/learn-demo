package com.mydesign.example.interfases.demo2.v2;

import com.mydesign.example.interfases.demo2.ConfigSource;


/**
 * 现在实现了配置的热更新
 * <p>
 * 思考：假设 RedisConfig 和 MySQLConfig 都需要实现可视化配置，那么如何处理
 */
public class DemoApp {

    public static void main(String[] args) {
        ConfigSource configSource = new ConfigSource();
        RedisConfig redisConfig = new RedisConfig(configSource);

        // 实现了Redis的配置定期热更新，如果 MySQLConfig 也需要热更新，实现HotUpdate接口即可
        ScheduleUpdater scheduleUpdater = new ScheduleUpdater(300, redisConfig);
        scheduleUpdater.run();
    }
}
