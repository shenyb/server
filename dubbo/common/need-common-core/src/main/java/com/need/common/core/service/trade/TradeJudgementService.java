package com.need.common.core.service.trade;

import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.GoodsJudgementListResultVO;

import java.util.List;

public interface TradeJudgementService {

	
	
	public List<GoodsJudgementListResultVO> getGoodsJudgementOpsList(String goodsId);
	
	
	public int countGoodsJudgement(String goodsId);


	public Message getCommentBypage(String goodsId, Integer pageNum, Integer pageSize);
}
