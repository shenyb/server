package com.need.common.core.dao.jdbc.trade;

import com.need.common.domain.po.api.trade.TradeJudgementPO;
import com.need.common.domain.vo.trade.GoodsJudgementListResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeJudgementDAO {
    int deleteByPrimaryKey(String orderNo);

    int insert(TradeJudgementPO record);

    int insertSelective(TradeJudgementPO record);

    TradeJudgementPO selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(TradeJudgementPO record);

    int updateByPrimaryKey(TradeJudgementPO record);
    
    
    /**
     * 
     * @author xiehao 2015年8月12日 下午6:45:57
     * @Method: queryJudgementByGoodsId 
     * @Description: TODO 根据 goodsId 获得商品的评价列表
     * @param goodsId
     * @return 
     * @throws
     */
    public List<GoodsJudgementListResultVO> queryJudgementByGoodsId(String goodsId);
    
    /**
     * 
     * @author xiehao 2015年8月12日 下午6:12:38
     * @Method: getJudgementCount 
     * @Description: TODO 获得商品的被评价的次数
     * @param goodsId
     * @return 
     * @throws
     */
    public int getJudgementCount(String goodsId);

	TradeJudgementPO getbyParams(@Param("orderNo")String orderNo, @Param("userId")String userId);

	List<GoodsJudgementListResultVO> getcommentsBygoodsId(String goodsId);
}