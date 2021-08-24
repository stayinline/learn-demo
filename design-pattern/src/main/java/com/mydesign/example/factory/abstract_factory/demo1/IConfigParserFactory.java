package com.mydesign.example.factory.abstract_factory.demo1;

import com.mydesign.example.factory.simple_factory.v1.IRuleConfigParser;

/**
 * 解析器创建工厂
 *
 * @author hemaoling
 */
public interface IConfigParserFactory {
    /**
     * 创建一个普通的解析器
     *
     * @return
     */
    IRuleConfigParser createParser();

    /**
     * 创建一个系统配置解析器
     *
     * @return
     */
    ISystemRuleConfigParser createSystemParser();

}
