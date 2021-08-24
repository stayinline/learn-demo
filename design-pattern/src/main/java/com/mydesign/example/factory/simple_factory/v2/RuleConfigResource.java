package com.mydesign.example.factory.simple_factory.v2;

import com.mydesign.example.factory.simple_factory.InvalidConfigExecption;
import com.mydesign.example.factory.simple_factory.RuleConfig;
import com.mydesign.example.factory.simple_factory.v1.*;

/**
 * 2.0 版本：
 * 简单工厂模式
 *
 * @author hemaoling
 */
public class RuleConfigResource {
    public RuleConfig load(String configFilePath) throws InvalidConfigExecption {
        String configFileExtension = getFileExtension(configFilePath);
        IRuleConfigParser parser = RuleConfigParserFactory.createParser(configFileExtension);
        String configTxt = "{\"name\":123}";
        return parser.pase(configTxt);
    }

    private String getFileExtension(String filePath) {
        return "json";
    }
}
