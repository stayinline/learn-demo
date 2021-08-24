package com.mydesign.example.factory.abstract_factory.demo1;

import com.mydesign.example.factory.simple_factory.v1.IRuleConfigParser;
import com.mydesign.example.factory.simple_factory.v1.XmlConfigParser;

/**
 * @author hemaoling
 */
public class XmlRuleConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new XmlConfigParser();
    }

    @Override
    public ISystemRuleConfigParser createSystemParser() {
        return null;
    }
}
