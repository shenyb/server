package com.need.share.dao.jdbc.api.trade;

import java.util.List;

import com.github.pagehelper.Page;
import com.need.share.web.controller.goods.vo.GoodsJudgementListVO;

public interface TradeJudgementDAO {

    /**
     * @author Rylan 2015年8月23日 下午9:07:27
     * @Method: queryJudgementByGoodsId 
     * @Description: TODO
     * @param goodsId
     * @return 
     * @throws
     */
    public Page<GoodsJudgementListVO> queryJudgementByGoodsId(String goodsId);
    
    /**
     * @author Rylan 2015年8月23日 下午9:07:32
     * @Method: getJudgementCount 
     * @Description: TODO
     * @param goodsId
     * @return 
     * @throws
     */
    public int getJudgementCount(String goodsId);
}