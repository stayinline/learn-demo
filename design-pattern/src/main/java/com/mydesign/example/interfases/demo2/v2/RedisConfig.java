package com.mydesign.example.interfases.demo2.v2;

import com.mydesign.example.interfases.demo2.ConfigSource;

public class RedisConfig implements HotUpdate {
    private String address;
    private int timeout;
    private int maxTotal;
    private ConfigSource configSource;

    RedisConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    /**
     * 从配置中心拉取最新的配置，并且解析，然后更改相应的字段
     */
    @Override
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
