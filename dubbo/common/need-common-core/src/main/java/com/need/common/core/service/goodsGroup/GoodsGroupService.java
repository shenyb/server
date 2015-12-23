package com.need.common.core.service.goodsGroup;

import com.need.common.domain.pub.Message;

import java.util.List;


public interface GoodsGroupService {
	
	public Message getGroupPrice(String groupBatch,int goodsNumber,List<String> goodsIds);
	
}
