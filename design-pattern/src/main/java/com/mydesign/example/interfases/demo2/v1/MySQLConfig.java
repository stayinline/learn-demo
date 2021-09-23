package com.mydesign.example.interfases.demo2.v1;

import com.mydesign.example.interfases.demo2.ConfigSource;

/**
 * 可以看到，这种方式的更新配置，是加载的时候只初始化一次单独的
 * 或者是需要调用者来反复轮询的
 * 我们期望：
 * MySQLConfig 和 RedisConfig 独立开来，
 * Redis可以热更新，而 MySQLConfig 一般不需要热更新
 */
public class MySQLConfig {
    private String address;
    private int timeout;
    private int maxTotal;
    private ConfigSource configSource;

    public MySQLConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    /**
     * 从配置中心拉取最新的配置，并且解析，然后更改相应的字段
     */
    public void updateConfig() {
        String latestConfig = configSource.pullConfig();
        address = parseConfig(latestConfig);
        // 省略其他解析逻辑
    }

    private String parseConfig(String config) {
        return "127.0.0.1";
    }

    // 省略其他get/set方法
}
