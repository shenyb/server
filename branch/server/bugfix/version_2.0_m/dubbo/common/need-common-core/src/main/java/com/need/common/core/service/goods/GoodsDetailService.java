package com.need.common.core.service.goods;

import com.alibaba.fastjson.JSONObject;
import com.need.common.domain.vo.goods.GoodsDetailVO;

public interface GoodsDetailService {

	public GoodsDetailVO getGoodsDetailById(String goodsId);
	
	public JSONObject getGoodsParamsById(String goodsId);
}
