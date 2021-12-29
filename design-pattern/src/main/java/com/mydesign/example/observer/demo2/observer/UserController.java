package com.mydesign.example.observer.demo2.observer;


import com.mydesign.example.observer.demo2.bad.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 场景：
 * 金融业务，
 * 新用户注册之后，给用户发行一些体验金，
 * 给用户发邮件，
 * 后续还可能会有更多方式，如红包现金、积分等等。
 * <p>
 * 使用观察者模式重构
 *
 * @author hemaoling
 */

public class UserController {
    /**
     * 这里采用观察者模式，将一堆需要被通知的对象放在一起，然后逐个执行这个接口的通知方法，
     * 实现了解耦
     */
    List<RegObserver> regObservers = new ArrayList<>();


    @Resource
    private UserService userService;


    /**
     * @param telephone
     * @param password
     * @return
     */
    public Long register(String telephone, String password) {
        Long userId = userService.register(telephone, password);

        for (RegObserver regObserver : regObservers) {
            regObserver.handleRegSuccess(userId);
        }

        return userId;
    }
}
