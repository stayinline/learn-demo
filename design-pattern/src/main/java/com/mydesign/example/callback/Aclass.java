package com.mydesign.example.callback;


/**
 * 回调：
 * A调用B，B反过来又调用A
 *
 * @author hemaoling
 */
public class Aclass implements ICallBack {
    @Override
    public void callbackMethod() {
        System.out.println("Aclass 实现的回调接口");
    }

    public static void main(String[] args) {
        Bclass bclass = new Bclass();
        bclass.process(new Aclass());
        /*
         * 执行结果：
         * 执行 Bclass 的业务逻辑中。。。
         * Aclass 实现的回调接口
         * 执行 Bclass 的业务逻辑结束。。。
         */
    }


}
