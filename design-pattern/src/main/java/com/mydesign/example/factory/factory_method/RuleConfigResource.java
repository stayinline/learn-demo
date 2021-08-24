package com.mydesign.example.factory.factory_method;

import com.mydesign.example.factory.simple_factory.InvalidConfigExecption;
import com.mydesign.example.factory.simple_factory.RuleConfig;
import com.mydesign.example.factory.simple_factory.v1.*;

/**
 * 多态版本、工厂方法实现：
 * 缺陷：
 * 假设现在需要新加一个 ISystemRuleConfigParser 专门用来处理系统配置
 * 那么，此时就需要现有的四个对应的再新建一个实现类 JsonRuleConfigParserFactory、XmlRuleConfigParserFactory、YamlRuleConfigParserFactory，
 * 且全都实现ISystemRuleConfigParser
 * <p>
 * 每一个工厂实现类都要新建一个实现类然后 implements RuleConfigParserFactory ，非常繁琐
 * 解决办法：
 * 抽象工厂模式
 *
 * @author hemaoling
 */
public class RuleConfigResource {
    public RuleConfig load(String configFilePath) throws InvalidConfigExecption {
        String configFileExtension = getFileExtension(configFilePath);
        RuleConfigParserFactory parserFactory;
        /*
         * 这里采用多态的方式，新增就新加一个实现类即可。改动小，更符合开闭原则
         *
         * 但是：这里又引入了很多if，
         *              解决方式就是-------简单工厂模式的实现方法2
         * 也就是创建一个这些工厂类的工厂，然后管理起来
         */
        if ("json".equals(configFileExtension)) {
            parserFactory = new JsonRuleConfigParserFactory();
        } else if ("xml".equals(configFileExtension)) {
            parserFactory = new XmlRuleConfigParserFactory();
        } else if ("yaml".equals(configFileExtension)) {
            parserFactory = new YamlRuleConfigParserFactory();
        } else {
            throw new InvalidConfigExecption("config file path is invalid!!!");
        }
        IRuleConfigParser parser = parserFactory.createParser();

        String configTxt = "{\"name\":123}";
        return parser.pase(configTxt);
    }

    private String getFileExtension(String filePath) {
        return "json";
    }


}
