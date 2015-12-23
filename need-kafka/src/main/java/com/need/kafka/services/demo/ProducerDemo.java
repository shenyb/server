package com.need.kafka.services.demo;

import com.need.kafka.services.utils.KafkaPropertiesManager;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个是demo 主要用来测试生产者的单条信息发送和批量信息发送
 * <p/>
 * Created by LightHuangfu on 2015/11/27.
 */
public class ProducerDemo {


    public static void main(String args[]) throws InterruptedException {
        ProducerConfig producerConfig = new ProducerConfig(KafkaPropertiesManager.getProducerProps());
        Producer<String, String> producer = new Producer<String, String>(producerConfig);
        // 单个发送
        for (int i = 0; i <= 1000000; i++) {
            KeyedMessage<String, String> message =
                    new KeyedMessage<String, String>(KafkaProperties.TEST_TOPIC, i + "", "Message" + i);
            producer.send(message);
            Thread.sleep(5000);
        }

        // 批量发送
        List<KeyedMessage<String, String>> messages = new ArrayList<KeyedMessage<String, String>>(100);
        for (int i = 0; i <= 10000; i++) {
            KeyedMessage<String, String> message =
                    new KeyedMessage<String, String>(KafkaProperties.TEST_TOPIC, i + "", "Message" + i);
            messages.add(message);
            if (i % 100 == 0) {
                producer.send(messages);
                messages.clear();
            }
        }
        producer.send(messages);
    }
}
