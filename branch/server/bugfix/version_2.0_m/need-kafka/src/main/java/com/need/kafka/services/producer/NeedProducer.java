package com.need.kafka.services.producer;

import com.need.kafka.services.utils.KafkaPropertiesManager;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生产者类，默认都使用同步方法发送
 * <p/>
 * Created by LightHuangfu on 2015/11/21.
 */
public class NeedProducer {

    private static final NeedProducer instance = new NeedProducer();

    public static NeedProducer getInstance() {
        return instance;
    }


    private NeedProducer() {
    }

    /**
     * @param topic 需要发送消息的Topic
     * @param msgId 发送的消息ID，一般是Integer
     * @param json  发送的内容
     */
    public boolean sendSync(String topic, String msgId, String json) {
        Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(KafkaPropertiesManager.getProducerProps()));
        try {
            producer.send(new KeyedMessage<String, String>(topic, msgId, json));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            producer.close();
        }

    }

    /**
     * @param keyedMessageMap 批量发送消息到多个Topic，数据结构为<Topic, <msgId, json>>
     * @return 是否抛出了异常，一般被认为是发送失败
     */
    public boolean sendBatchSync(Map<String, Map<String, String>> keyedMessageMap) {
        List<KeyedMessage<String, String>> keyedMessages = new ArrayList<>(keyedMessageMap.size());
        for (Map.Entry<String, Map<String, String>> entry : keyedMessageMap.entrySet()) {
            for (Map.Entry<String, String> messageEntry : entry.getValue().entrySet()) {
                KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>(entry.getKey(), messageEntry.getKey(), messageEntry.getValue());
                keyedMessages.add(keyedMessage);
            }
        }
        Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(KafkaPropertiesManager.getProducerProps()));
        try {
            producer.send(keyedMessages);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            producer.close();
        }
    }

    public static void main(String args[]) {
        Map<String, Map<String, String>> keyedMessageMap = new HashMap<>(2);
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put(String.valueOf(System.currentTimeMillis() + 1000), "hello light111");
        Map<String, String> msgMap1 = new HashMap<>();
        msgMap1.put(String.valueOf(System.currentTimeMillis()), "hello lightHuangfu222");
        keyedMessageMap.put("light", msgMap);
        keyedMessageMap.put("lightHuangfu", msgMap1);
        NeedProducer.getInstance().sendBatchSync(keyedMessageMap);
//        NeedProducer.getInstance().sendSync("light", String.valueOf(System.currentTimeMillis()), "Hello 4321111");
    }
}
