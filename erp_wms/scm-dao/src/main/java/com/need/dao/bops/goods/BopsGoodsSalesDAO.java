package com.need.dao.bops.goods;

import java.util.List;

import com.need.domain.po.bops.goods.BopsGoodsSales;
import com.need.domain.vo.goods.BopsGoodsSalesVO;

public interface BopsGoodsSalesDAO {
    int deleteByPrimaryKey(String goodsId);

    int insert(BopsGoodsSales record);

    int insertSelective(BopsGoodsSales record);

    BopsGoodsSales selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(BopsGoodsSales record);

    int updateByPrimaryKey(BopsGoodsSales record);
    
    public List<BopsGoodsSales> queryGoodsSales(BopsGoodsSalesVO bopsGoodsSalesVO);
    
    int deleteAll();
}