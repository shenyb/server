package com.need.kafka.services.comsume;

import com.need.kafka.services.KafkaConstantEnum;
import com.need.kafka.services.utils.KafkaPropertiesManager;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 消费者类
 * <p/>
 * Created by LightHuangfu on 2015/11/21.
 */
public class NeedConsumer {

    private static final NeedConsumer instance = new NeedConsumer();

    private static final Logger log = LoggerFactory.getLogger(NeedConsumer.class);
    private static final boolean DEBUG = log.isDebugEnabled();

    private NeedConsumer() {
    }

    public static NeedConsumer getInstance() {
        return instance;
    }

    /**
     * @param groupId 分组ID
     *                当你将多个Consumer分在同一个组的时候，会按照实例启动的先后顺序读取订阅的同一个Topic，
     *                也就是说，服务器A1读取到了message,A2...之后的服务器就不会再读到当前这条message了。
     *                当你将多个Consumer分在不同组的时候，会按照组别进行同一个Topic消息的获取（例如2台服务器分在2个组，
     *                订阅同一个Topic的话，A和B服务器都可以收到最新Message）
     * @param topic   主题
     * @return 返回MsgId和json的Map对象
     */
    public KafkaStream<String, String> consume(String groupId, String topic) {
        List<String> topicList = new ArrayList<>(1);
        topicList.add(topic);
        Map<String, List<KafkaStream<String, String>>> consumerMap = consume(groupId, topicList);
        KafkaStream<String, String> stream = consumerMap.get(topic).get(0);
//        if (DEBUG) {
//            for (MessageAndMetadata<String, String> aStream : stream) {
//                //System.out.println(aStream.message());
//                log.debug("Get message " + aStream.message());
//            }
//        }
        return stream;
    }

    /**
     * @param groupId   消费者分组的ID
     * @param topicList 订阅的topic列表
     * @return 批量订阅消息，和返回消息
     */
    public Map<String, List<KafkaStream<String, String>>> consume(String groupId, List<String> topicList) {
        Properties props = KafkaPropertiesManager.getConsumerProps();
        props.put(KafkaConstantEnum.GROUP_ID.getValue(), groupId);
        ConsumerConfig config = new ConsumerConfig(props);
        ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        for (int i = 0; i < topicList.size(); i++) {
            topicCountMap.put(topicList.get(i), i + 1);
        }
        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        return consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
    }

    public static void main(String[] args) {
//        List<String> topicList = new ArrayList<>(2);
//        topicList.add("light");
//        topicList.add("lightHuangfu");
//        Map<String, List<KafkaStream<String, String>>> consumerMap = NeedConsumer.getInstance().consume("16", topicList);
//        System.out.println("map size is " + consumerMap.size());
//        for (Map.Entry<String, List<KafkaStream<String, String>>> entry : consumerMap.entrySet()) {
//            System.out.println("key is " + entry.getKey());
//            System.out.println("consumer map is " + consumerMap.get(entry.getKey()).toString());
//            for (KafkaStream<String, String> kafkaStream : entry.getValue()) {
//                for (MessageAndMetadata<String, String> aStream : kafkaStream) {
//                    System.out.println(aStream.message());
//                }
//            }
//        }
        NeedConsumer.getInstance().consume("18", "light");
    }

}
