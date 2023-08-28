package com.mydesign.example.template_method;

/**
 * 模板方法模式
 *
 * @author hemaoling
 */
public abstract class AbstractParentClass {

    /**
     * 这个模板方法由父类定义并实现，固定步骤的执行某些方法，构成算法骨架
     * 然后将扩展点方法交给子类实现，由子类控制实现想要的逻辑，
     * 就达到了不改变父类骨架的方式改变某些行为
     */
    public final void templateMethod() {
        /*
        step1();
        step2();
        step3();
        ...
        */
        method1();


        method2();
    }

    /**
     * 根据需要将一些方法声明成抽象类，强制子类实现这些方法
     * 并非是必须的，不需要就不用
     */
    protected abstract void method1();

    protected abstract void method2();
    protected abstract void method3();

    protected abstract void method(ICallBack callBack);


}
