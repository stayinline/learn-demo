package com.mydesign.example.factory.abstract_factory.demo1;

import com.mydesign.example.factory.simple_factory.RuleConfig;

/**
 * @author hemaoling
 */
public interface ISystemRuleConfigParser {
    RuleConfig pase(String configTxt);
}
