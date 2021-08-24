package com.mydesign.example.factory.simple_factory.v1;

import com.mydesign.example.factory.simple_factory.InvalidConfigExecption;
import com.mydesign.example.factory.simple_factory.RuleConfig;

/**
 * 1.0版本：
 * 根据配置文件的后缀，选择不同的解析器
 *
 * @author hemaoling
 */
public class RuleConfigResource {
    public RuleConfig load(String configFilePath) throws InvalidConfigExecption {
        String configFileExtension = getFileExtension(configFilePath);

        /*
         *很显然：create这里的逻辑很不优雅，应该剥离出来
         * 方法1：抽出一个方法
         * 方法2：抽出一个单独的类，这个类只为了完成创建功能
         */
        IRuleConfigParser parser = null;
        if ("json".equals(configFileExtension)) {
            parser = new JsonConfigParser();
        } else if ("xml".equals(configFileExtension)) {
            parser = new XmlConfigParser();
        } else if ("yaml".equals(configFileExtension)) {
            parser = new YamlConfigParser();
        } else {
            throw new InvalidConfigExecption("config file path is invalid!!!");
        }

        String configTxt = "{\"name\":123}";
        return parser.pase(configTxt);
    }

    private String getFileExtension(String filePath) {
        return "json";
    }
}
