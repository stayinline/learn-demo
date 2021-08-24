package com.mydesign.example.factory.abstract_factory.demo1;

import com.mydesign.example.factory.simple_factory.v1.IRuleConfigParser;
import com.mydesign.example.factory.simple_factory.InvalidConfigExecption;
import com.mydesign.example.factory.simple_factory.RuleConfig;

/**
 * 抽象工厂模式
 *
 * @author hemaoling
 */
public class RuleConfigResource {
    public RuleConfig load(String configFilePath) throws InvalidConfigExecption {
        String configFileExtension = getFileExtension(configFilePath);
        IRuleConfigParser parser = null;
        ISystemRuleConfigParser systemParser = null;
        if ("json".equals(configFileExtension)) {
            if ("system".contains(configFileExtension)) {
                systemParser = new JsonRuleConfigParserFactory().createSystemParser();
            } else {
                parser = new JsonRuleConfigParserFactory().createParser();
            }
        } else if ("xml".equals(configFileExtension)) {
            if ("system".contains(configFileExtension)) {
                systemParser = new JsonRuleConfigParserFactory().createSystemParser();
            } else {
                parser = new JsonRuleConfigParserFactory().createParser();
            }
        } else if ("yaml".equals(configFileExtension)) {
            if ("system".contains(configFileExtension)) {
                systemParser = new JsonRuleConfigParserFactory().createSystemParser();
            } else {
                parser = new JsonRuleConfigParserFactory().createParser();
            }
        } else {
            throw new InvalidConfigExecption("config file path is invalid!!!");
        }

        String configTxt = "{\"name\":123}";
        String systemConfigTxt = "{\"name\":123}";
        RuleConfig config = parser.pase(configTxt);
        RuleConfig systemConfig = systemParser.pase(systemConfigTxt);
        return config;
    }

    private String getFileExtension(String filePath) {
        return "json";
    }
}
