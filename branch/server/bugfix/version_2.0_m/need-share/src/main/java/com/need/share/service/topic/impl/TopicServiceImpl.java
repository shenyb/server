package com.need.share.service.topic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.need.share.dao.jdbc.api.goods.GoodsMainDAO;
import com.need.share.service.topic.TopicService;

import freemarker.template.SimpleHash;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private GoodsMainDAO goodsMainDAO;
	
	

	@Override
	public SimpleHash makeContent(String id, String... otherStr) {
		/** TODO Auto-generated method stub*/
		SimpleHash sh = new SimpleHash();
        sh.put("goodsInfo", goodsMainDAO.selectByGoodId(id));        
		return sh;
	}
	
}
