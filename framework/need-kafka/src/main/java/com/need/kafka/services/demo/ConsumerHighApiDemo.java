package com.need.kafka.services.demo;

import com.need.kafka.services.utils.KafkaPropertiesManager;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 低级别的API是高级别API实现的基础，也是为了一些对维持消费状态有特殊需求的场景，比如Hadoop consumer这样的离线consumer。
 * <p/>
 * Created by LightHuangfu on 2015/11/27.
 */
public class ConsumerHighApiDemo {

    /**
     * 该consumer所属的组ID
     */
    private String groupid;

    /**
     * 该consumer的ID
     */
    private String consumerid;

    /**
     * 每个topic开几个线程？
     */
    private int threadPerTopic;

    public ConsumerHighApiDemo(String groupid, String consumerid, int threadPerTopic) {
        super();
        this.groupid = groupid;
        this.consumerid = consumerid;
        this.threadPerTopic = threadPerTopic;
    }

    public void consume() {
        Properties properties = KafkaPropertiesManager.getConsumerProps();
        properties.put("group.id", groupid);
        ConsumerConfig config = new ConsumerConfig(properties);

        ConsumerConnector connector = Consumer.createJavaConsumerConnector(config);
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        // 设置每个topic开几个线程
        topicCountMap.put(KafkaProperties.TEST_TOPIC, threadPerTopic);

        // 获取stream
        Map<String, List<KafkaStream<byte[], byte[]>>> streams = connector.createMessageStreams(topicCountMap);
        // 为每个stream启动一个线程消费消息
        for (KafkaStream<byte[], byte[]> stream : streams.get(KafkaProperties.TEST_TOPIC)) {
            new MyStreamThread(stream).start();
        }
    }

    /**
     * 每个consumer的内部线程
     *
     */
    private class MyStreamThread extends Thread {
        private KafkaStream<byte[], byte[]> stream;

        public MyStreamThread(KafkaStream<byte[], byte[]> stream) {
            super();
            this.stream = stream;
        }

        @Override
        public void run() {
            ConsumerIterator<byte[], byte[]> streamIterator = stream.iterator();

            // 逐条处理消息
            while (streamIterator.hasNext()) {
                MessageAndMetadata<byte[], byte[]> message = streamIterator.next();
                String topic = message.topic();
                int partition = message.partition();
                long offset = message.offset();
                if (message.key() == null || message.message() == null) {
                    System.out.println("message key is null topic:" + topic + " partition:" + partition + " offset:" + offset);
                    continue;
                }
                String key = new String(message.key());
                String msg = new String(message.message());
                // 在这里处理消息,这里仅简单的输出
                // 如果消息消费失败，可以将已上信息打印到日志中，活着发送到报警短信和邮件中，以便后续处理
                System.out.println("consumerid:" + consumerid + ", thread : " + Thread.currentThread().getName()
                        + ", topic : " + topic + ", partition : " + partition + ", offset : " + offset + " , key : "
                        + key + " , mess : " + msg);
            }
        }
    }

    public static void main(String[] args) {
        String groupid = "myconsumergroup";
        ConsumerHighApiDemo consumer1 = new ConsumerHighApiDemo(groupid, "myconsumer1", 3);
        ConsumerHighApiDemo consumer2 = new ConsumerHighApiDemo(groupid, "myconsumer2", 3);

        consumer1.consume();
        consumer2.consume();
    }

}
