package com.need.kafka.services.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件管理
 * <p/>
 * Created by LightHuangfu on 2015/11/21.
 */
public class KafkaPropertiesManager {

    private static final String PATH = "/properties/producer.properties";

    private static final String CPATH = "/properties/consumer.properties";


    public static Properties getProducerProps() {
        return getIn(PATH);
    }

    public static Properties getConsumerProps() {
        return getIn(CPATH);
    }

    private static Properties getIn(String path) {
        InputStream in = KafkaPropertiesManager.class.getResourceAsStream(
                path);
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void main(String args[]) {
        Properties properties = KafkaPropertiesManager.getProducerProps();
        System.out.println(properties);
    }
}
