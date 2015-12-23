package com.need.show.dao.jdbc.api.trade;

import com.github.pagehelper.Page;

public interface TradeJudgementDAO {

 
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