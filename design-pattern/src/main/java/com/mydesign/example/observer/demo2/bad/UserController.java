package com.mydesign.example.observer.demo2.bad;


import javax.annotation.Resource;

/**
 * 场景：
 * 金融业务，
 * 新用户注册之后，给用户发行一些体验金
 * <p>
 * 这里实现一个最简单的版本，然后使用观察者模式重构
 *
 * @author hemaoling
 */

public class UserController {


    @Resource
    private UserService userService;

    @Resource
    private PromotionService promotionService;

    /**
     * @param telephone
     * @param password
     * @return
     */
    public Long register(String telephone, String password) {
        Long userId = userService.register(telephone, password);
        promotionService.issueNewUserExperienceCash(userId);
        return userId;
    }
}
