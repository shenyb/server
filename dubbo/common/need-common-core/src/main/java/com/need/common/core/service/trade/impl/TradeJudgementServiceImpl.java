package com.need.common.core.service.trade.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.common.core.dao.jdbc.trade.TradeJudgementDAO;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.service.trade.TradeJudgementService;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.GoodsJudgementListResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeJudgementServiceImpl implements TradeJudgementService {

	@Autowired
	private TradeJudgementDAO tradeJudgementDAO;
	@Autowired
	private UserBaseDAO userBaseDAO;
	
	@Override
	public List<GoodsJudgementListResultVO> getGoodsJudgementOpsList(String goodsId) {
		// TODO Auto-generated method stub
		
		
		List<GoodsJudgementListResultVO> resultVOList = tradeJudgementDAO.queryJudgementByGoodsId(goodsId);
		for(GoodsJudgementListResultVO resultVO : resultVOList){
			UserBasePO user= userBaseDAO.selectByPrimaryKey(resultVO.getUserId());
			resultVO.setProfilePicKey(user!=null?user.getProfilePicKey():"");
			resultVO.setUserName(user!=null?user.getNickName():"");
			resultVO.setJudgementTime(resultVO.getDateJudgementTime().getTime());//传给客户端的时间格式是毫秒数
			resultVO.setDateJudgementTime(null);
		}
		
		return resultVOList;
	}

	/**
	 * @author xiehao 2015年8月19日 下午3:28:35
	 * @Method: countGoodsJudgement 
	 * @Description: TODO 统计某个商品的评论数
	 * @param goodsId
	 * @return 
	 * @see com.need.api.service.trade.TradeJudgementService#countGoodsJudgement(java.lang.String)
	 */
	@Override
	public int countGoodsJudgement(String goodsId) {
		// TODO Auto-generated method stub
		return tradeJudgementDAO.getJudgementCount(goodsId);
	}

	@Override
	public Message getCommentBypage(String goodsId, Integer pageNum, Integer pageSize) {
		Message message =Message.success();
		PageHelper.startPage(pageNum, pageSize);
		Page<GoodsJudgementListResultVO> page = (Page<GoodsJudgementListResultVO>) tradeJudgementDAO.getcommentsBygoodsId(goodsId);
		message.addData("commentList", page.getResult());
		message.addData("total", page.getTotal());
		return message;
	}

}
