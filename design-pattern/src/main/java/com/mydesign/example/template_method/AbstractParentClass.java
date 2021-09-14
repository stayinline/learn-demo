package com.mydesign.example.template_method;

/**
 * @author hemaoling
 */
public abstract class AbstractParentClass {

    /**
     * 这个模板方法由父类定义并实现，固定步骤的执行某些方法
     * 然后将扩展点方法交给子类实现
     */
    public final void templateMethod() {
        /*
        step1();
        step2();
        step3();
        ...
        */
        method1();


        method12();
    }

    /**
     * 根据需要将一些方法声明成抽象类，强制子类实现这些方法
     * 并非是必须的，不需要就不用
     */
    protected abstract void method1();

    protected abstract void method12();

}
