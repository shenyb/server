package com.need.dao.bops.goods;

import java.util.List;

import com.need.domain.po.bops.goods.BopsGoodsDetail;
import com.need.domain.vo.goods.GroupGoodsDetailVo;

public interface BopsGoodsDetailDAO {
    int deleteByPrimaryKey(String goodsId);

    int insert(BopsGoodsDetail record);

    int insertSelective(BopsGoodsDetail bopsGoodsDetail);
    
    int insertNewGoods(BopsGoodsDetail bopsGoodsDetail);

    BopsGoodsDetail selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(BopsGoodsDetail record);

    int updateByPrimaryKey(BopsGoodsDetail record);
    
    int updateWeight(BopsGoodsDetail record);
    
    /**
     * 根据组合的商品的id 查询
     * @param groupGoodsId
     * @return
     */
   List<GroupGoodsDetailVo> queryListByGroupGoodsId(String groupGoodsId);
}