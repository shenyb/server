package com.need.service.bops.wms.impl;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.need.dao.bops.wms.ESynEdiReceiveMessageDAO;
import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;
import com.need.kafka.services.comsume.NeedConsumer;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

@Component  
public class ErpCommonServiceImpl {
    @Autowired
    private ESynEdiReceiveMessageDAO ediReceiveMessageDao;
	/**
	 * 接受wms到erp的数据插入到e_syn_edi_receive_message
	 * @param limitNum
	 * @param loop
	 * @param topic
	 * @return
	 * @author zhangmengbin
	 */
	public Map<String,Set<String>> wmsToErpResult(final boolean loop,final String topic){
				Thread thread = new Thread(new Runnable(){
					long threadStartTime = System.currentTimeMillis();
					long initVal = 0;
					long interval = 10000;//每10s显示一次更新信息
					@Override
					public void run() {
						do {
							try {
								long threadRunTime = System.currentTimeMillis();
								if(initVal == (threadRunTime - threadStartTime)/interval){
									continue;
								}
								initVal = (threadRunTime - threadStartTime)/interval;
								KafkaStream<String, String> stream= NeedConsumer.getInstance().consume("2",topic);
								ConsumerIterator<String, String> temp = stream.iterator();
								if (temp.hasNext()) {
									for (MessageAndMetadata<String, String> aStream : stream) {
										System.out.println(aStream.message());
						                if(!aStream.message().endsWith("]")){
						                	System.out.println(aStream.message());
						                	ESynEdiReceiveMessage eSynEdiReceiveMessage =JSONObject.parseObject(aStream.message(), ESynEdiReceiveMessage.class);  
						                	   //在config.properties根据业务类型拿到topic
						                	  // PropertiesFileLoader propertiesFileLoader = PropertiesFileLoader.getInstance();
						                	   //String topic= propertiesFileLoader.getProerties("config/conf.properties", "erp"+ediReceiveMessage.getType());
						                	eSynEdiReceiveMessage.setQueue(topic);
						                	eSynEdiReceiveMessage.setReceiveDate(new Date());
						                	ediReceiveMessageDao.insert(eSynEdiReceiveMessage);
						            	   }
										}
									}
								Thread.sleep(2000);//每2秒执行一次更新
							} catch (Exception e) {
								System.out.println("从kafka获取消息失败:"+Thread.currentThread().getName());
								e.printStackTrace();
							}
						} while (true);
					}});
				thread.start();
		return Collections.emptyMap();
	}
}
