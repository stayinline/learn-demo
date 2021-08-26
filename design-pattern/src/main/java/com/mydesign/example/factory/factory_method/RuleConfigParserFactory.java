package com.mydesign.example.factory.factory_method;

import com.mydesign.example.factory.simple_factory.v1.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂方法模式
 * <p>
 * 这是父工厂，或者叫工厂方法的公共接口，对创建对象这一方法抽象，这个方法也就值工厂方法模式的方法的体现
 * <p>
 * 子实现类实现这个接口，并且实现自己的创建对象流程
 * <p>
 * 然后用户根据当前自己的需要，选择不同的子实现类即可
 *
 * @author hemaoling
 */
public interface RuleConfigParserFactory {

    /**
     * 创建一个RuleConfigParser对象
     * <p>
     * 我们称这个方法叫做工厂方法，因为这个方法负责创建一个对象
     *
     * @return IRuleConfigParser 的实现类对象
     */
    IRuleConfigParser createParser();
}
