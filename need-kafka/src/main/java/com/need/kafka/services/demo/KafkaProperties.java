package com.need.kafka.services.demo;

import com.need.kafka.services.utils.KafkaPropertiesManager;

/**
 * Created by LightHuangfu on 2015/11/27.
 */
public class KafkaProperties {

    public static final String TEST_TOPIC = "light";

    public static final String BROKER_CONNECT = KafkaPropertiesManager.getProducerProps().getProperty("metadata.broker.list");

}
