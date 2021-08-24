package com.mydesign.example.factory.simple_factory.v2;

import com.mydesign.example.factory.simple_factory.InvalidConfigExecption;
import com.mydesign.example.factory.simple_factory.v1.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂模式
 *
 * @author hemaoling
 */
public class RuleConfigParserFactory {

    /**
     * 简单工厂模式的实现方法1：
     * 缺陷：
     * （1）if条件分支很多
     * （2）如果添加新的解析实现类，那么这个createParser方法将被改动很多；用实现方法2规避这个问题
     *
     * @param configFormat
     * @return
     * @throws InvalidConfigExecption
     */
    public static IRuleConfigParser createParser(String configFormat) throws InvalidConfigExecption {
        IRuleConfigParser parser;
        if ("json".equals(configFormat)) {
            parser = new JsonConfigParser();
        } else if ("xml".equals(configFormat)) {
            parser = new XmlConfigParser();
        } else if ("yaml".equals(configFormat)) {
            parser = new YamlConfigParser();
        } else {
            throw new InvalidConfigExecption("config file path is invalid!!!");
        }
        return parser;
    }


    /**
     * 简单工厂模式的实现方法2：
     *
     * @param configFormat
     * @return
     * @throws InvalidConfigExecption
     */


    private static final Map<String, IRuleConfigParser> PARSER_MAP = new HashMap<>();

    static {
        PARSER_MAP.put("json", new JsonConfigParser());
        PARSER_MAP.put("xml", new XmlConfigParser());
        PARSER_MAP.put("yaml", new YamlConfigParser());
    }

    public static IRuleConfigParser createParser2(String configFormat) {
        if (configFormat == null || PARSER_MAP.isEmpty()) {
            return null;
        }
        return PARSER_MAP.get(configFormat);
    }


}
