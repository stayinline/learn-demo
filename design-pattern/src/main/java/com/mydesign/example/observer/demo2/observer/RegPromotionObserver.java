package com.mydesign.example.observer.demo2.observer;

import com.mydesign.example.observer.demo2.bad.PromotionService;

import javax.annotation.Resource;

/**
 * @author hemaoling
 */
public class RegPromotionObserver implements RegObserver {

    @Resource
    private PromotionService promotionService;


    @Override
    public void handleRegSuccess(Long uid) {
        promotionService.issueNewUserExperienceCash(uid);
    }
}
