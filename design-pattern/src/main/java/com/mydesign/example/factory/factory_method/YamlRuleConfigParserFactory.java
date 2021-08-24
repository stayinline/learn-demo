package com.mydesign.example.factory.factory_method;

import com.mydesign.example.factory.simple_factory.v1.IRuleConfigParser;
import com.mydesign.example.factory.simple_factory.v1.YamlConfigParser;

/**
 * @author hemaoling
 */
public class YamlRuleConfigParserFactory implements RuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new YamlConfigParser();
    }
}
