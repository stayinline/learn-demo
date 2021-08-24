package com.mydesign.example.factory.abstract_factory.demo1;

import com.mydesign.example.factory.simple_factory.v1.IRuleConfigParser;
import com.mydesign.example.factory.simple_factory.v1.YamlConfigParser;

/**
 * @author hemaoling
 */
public class YamlRuleConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new YamlConfigParser();
    }

    @Override
    public ISystemRuleConfigParser createSystemParser() {
        return null;
    }
}
