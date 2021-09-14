package com.mydesign.example.callback;

/**
 * @author hemaoling
 */
public class Bclass {

    /**
     *
     * @param callback 接受一个接口
     */
    public void process(ICallBack callback) {
        System.out.println("执行 Bclass 的业务逻辑中。。。");

        callback.callbackMethod();

        System.out.println("执行 Bclass 的业务逻辑结束。。。");

    }
}
