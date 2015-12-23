package com.need.dao.bops.goods;

import java.util.List;

import com.need.domain.po.api.goods.GoodsItemsPO;
import com.need.domain.po.bops.goods.BopsGoodsItemsPO;
import com.need.domain.vo.goods.BopsGoodsItemsVO;

public interface BopsGoodsItemsDAO {
    int deleteByPrimaryKey(Integer goodsItemsId);

    int insert(BopsGoodsItemsPO record);

    int insertSelective(BopsGoodsItemsVO record);

    BopsGoodsItemsPO selectByPrimaryKey(Integer goodsItemsId);

    int updateByPrimaryKeySelective(BopsGoodsItemsPO record);

    int updateByPrimaryKey(BopsGoodsItemsPO record);
    
    int insertGoodsItem(BopsGoodsItemsVO record);
    
    List<BopsGoodsItemsVO> selectItemList(String goodsGroupId);
    
    int insertReGoodsItem(BopsGoodsItemsVO record);
    
    int deleteByGroupId(String goodsGroupId);
    
    List<BopsGoodsItemsPO> selectItemListPrice(String goodsId);
}