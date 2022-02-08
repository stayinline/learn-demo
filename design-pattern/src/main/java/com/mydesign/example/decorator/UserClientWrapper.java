package com.mydesign.example.decorator;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hemaoling
 */
public class UserClientWrapper {

    @Resource
    private UserClient userClient;


    /**
     * 一个简单的装饰器实现
     *
     * @return
     */
    public Map<String, User> getUserInfo() {
        Map<String, User> map = new HashMap<>();
        List<User> userInfos = userClient.getUserInfo();

        //自定义字段
        userInfos.forEach(x -> map.put("xxx", x));
        return map;
    }


    /*
     * 复杂的装饰器，思想是通过组合的方式，给对象添加一些功能
     */
}
