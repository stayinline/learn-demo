package com.mydesign.example.factory.abstract_factory.demo1;

import com.mydesign.example.factory.simple_factory.v1.IRuleConfigParser;
import com.mydesign.example.factory.simple_factory.v1.JsonConfigParser;

/**
 * @author hemaoling
 */
public class JsonRuleConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new JsonConfigParser();
    }

    @Override
    public ISystemRuleConfigParser createSystemParser() {
        return null;
    }
}
