package com.mydesign.example.factory.factory_method;

import com.mydesign.example.factory.simple_factory.v1.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂方法模式
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
