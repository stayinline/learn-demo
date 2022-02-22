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
    static List<RegObserver> regObservers = new ArrayList<>();


    @Resource
    private UserService userService;


    /**
     * 需要先调用这个方法初始化所有的观察者
     * 后续有需要新增的观察者，也通过这个方法添加进来
     *
     * @param observers
     */
    public void setObservers(List<RegObserver> observers) {
        regObservers.addAll(observers);
    }


    /**
     * 然后在业务中被使用的地方遍历通知所有观察者
     *
     * @param telephone
     * @param password
     * @return
     */
    public Long register(String telephone, String password) {
        // 1、初始化和注册观察者
        // 正常情况下，这里不能每次调用都初始化一次，应该是全局只初始化一次
        List<RegObserver> observers = new ArrayList<>();
        observers.add(new RegNotificationObserver());
        observers.add(new RegPromotionObserver());
        setObservers(observers);

        // 2、执行观察者所关注的那个动作
        Long userId = userService.register(telephone, password);

        if (null != userId) {
            // 3、动作执行成功后通知观察者
            for (RegObserver regObserver : regObservers) {
                regObserver.handleRegSuccess(userId);
            }
        }

        return userId;
    }
}
