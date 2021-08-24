package com.mydesign.example.factory.factory_method;

import com.mydesign.example.factory.simple_factory.v1.IRuleConfigParser;
import com.mydesign.example.factory.simple_factory.v1.JsonConfigParser;

/**
 * @author hemaoling
 */
public class JsonRuleConfigParserFactory implements RuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new JsonConfigParser();
    }
}
