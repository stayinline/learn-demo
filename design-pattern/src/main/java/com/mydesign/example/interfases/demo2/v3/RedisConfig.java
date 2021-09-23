package com.mydesign.example.interfases.demo2.v3;

import com.mydesign.example.interfases.demo2.ConfigSource;
import com.mydesign.example.interfases.demo2.v2.HotUpdate;


/**
 * 通过实现不同的接口获得不同的行为，
 * 通过控制要不要实现相应的接口来拥有不用的行为，
 * 而不是将不同的行为糅合在同一个或者不同的方法中
 */
public class RedisConfig implements HotUpdate, Viewer {
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

    @Override
    public void showConfig(String url, String jsonStr) {
        // 将json格式的配置信息，发送到URL指定的接口
    }


    // 省略其他get/set方法
}
