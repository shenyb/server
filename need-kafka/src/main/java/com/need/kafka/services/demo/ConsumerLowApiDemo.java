package com.need.kafka.services.demo;

import kafka.api.FetchRequestBuilder;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.cluster.Broker;
import kafka.common.TopicAndPartition;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.Message;
import kafka.message.MessageAndOffset;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 低级别Api，定制方便，但难以理解
 * 低级别Api和一个指定的Broker保持连接，并在接收完消息后关闭连接，这个级别是无状态的，每次读取都带着offset.
 * <p/>
 * <p/>
 * Created by LightHuangfu on 2015/11/27.
 */
public class ConsumerLowApiDemo {


    public static void main(String args[]) {

    }

    public void consume() {
        int partition = 0;


        // 找到leader
        Broker leaderBroker = findLeader(KafkaProperties.BROKER_CONNECT, KafkaProperties.TEST_TOPIC, partition);


        // 从leader消费
        SimpleConsumer simpleConsumer =
                new SimpleConsumer(leaderBroker.host(), leaderBroker.port(), 20000, 10000, "mySimpleConsumer");
        long startOffet = 1;
        int fetchSize = 1000;


        while (true) {
            long offset = startOffet;
            // 添加fetch指定目标tipic，分区，起始offset及fetchSize(字节)，可以添加多个fetch
            kafka.api.FetchRequest req =
                    new FetchRequestBuilder().addFetch(KafkaProperties.TEST_TOPIC, 0, startOffet, fetchSize).build();


            // 拉取消息
            FetchResponse fetchResponse = simpleConsumer.fetch(req);


            ByteBufferMessageSet messageSet = fetchResponse.messageSet(KafkaProperties.TEST_TOPIC, partition);
            for (MessageAndOffset messageAndOffset : messageSet) {
                Message mess = messageAndOffset.message();
                ByteBuffer payload = mess.payload();
                byte[] bytes = new byte[payload.limit()];
                payload.get(bytes);
                String msg = new String(bytes);


                offset = messageAndOffset.offset();
                System.out.println("partition : " + 3 + ", offset : " + offset + "  mess : " + msg);
            }
            // 继续消费下一批
            startOffet = offset + 1;
        }
    }

    /**
     * @param brokerHosts broker地址，格式为：“host1:port1,host2:port2,host3:port3”
     * @param topic       topic
     * @param partition   分区
     * @return 找到制定分区的leader broker
     */
    public Broker findLeader(String brokerHosts, String topic, int partition) {
        Broker leader = findPartitionMetadata(brokerHosts, topic, partition).leader();
        System.out.println(String.format("Leader tor topic %s, partition %d is %s:%d", topic, partition, leader.host(),
                leader.port()));
        return leader;
    }

    /**
     * 找到指定分区的元数据
     *
     * @param brokerHosts broker地址，格式为：“host1:port1,host2:port2,host3:port3”
     * @param topic       topic
     * @param partition   分区
     * @return 元数据
     */
    private PartitionMetadata findPartitionMetadata(String brokerHosts, String topic, int partition) {
        PartitionMetadata returnMetaData = null;
        for (String brokerHost : brokerHosts.split(",")) {
            SimpleConsumer consumer = null;
            String[] splits = brokerHost.split(":");
            consumer = new SimpleConsumer(splits[0], Integer.valueOf(splits[1]), 100000, 64 * 1024, "leaderLookup");
            List<String> topics = Collections.singletonList(topic);
            TopicMetadataRequest request = new TopicMetadataRequest(topics);
            TopicMetadataResponse response = consumer.send(request);
            List<TopicMetadata> topicMetadatas = response.topicsMetadata();
            for (TopicMetadata topicMetadata : topicMetadatas) {
                for (PartitionMetadata PartitionMetadata : topicMetadata.partitionsMetadata()) {
                    if (PartitionMetadata.partitionId() == partition) {
                        returnMetaData = PartitionMetadata;
                    }
                }
            }
            consumer.close();
        }
        return returnMetaData;
    }

    /**
     * 根据时间戳找到某个客户端消费的offset
     *
     * @param consumer  SimpleConsumer
     * @param topic     topic
     * @param partition 分区
     * @param clientID  客户端的ID
     * @param whichTime 时间戳
     * @return offset
     */
    public long getLastOffset(SimpleConsumer consumer, String topic, int partition, String clientID, long whichTime) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo =
                new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));
        OffsetRequest request = new OffsetRequest(requestInfo, kafka.api.OffsetRequest.CurrentVersion(), clientID);
        OffsetResponse response = consumer.getOffsetsBefore(request);
        long[] offsets = response.offsets(topic, partition);
        return offsets[0];
    }
}
