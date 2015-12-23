package com.need.dao.bops.goodsgroup;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.goodsgroup.BopsGoodsGroup;
import com.need.domain.po.bops.goodsgroup.BopsGoodsGroupKey;
import com.need.domain.vo.goodsgroup.GoodsGroupParaVO;

public interface BopsGoodsGroupDAO {
    int deleteByPrimaryKey(BopsGoodsGroupKey key);

    int insert(BopsGoodsGroup record);

    int insertSelective(BopsGoodsGroup record);

    BopsGoodsGroup selectByPrimaryKey(BopsGoodsGroupKey key);

    int updateByPrimaryKeySelective(BopsGoodsGroup record);

    int updateByPrimaryKey(BopsGoodsGroup record);

	BopsGoodsGroup getByOpsIdAndGoodsId(@Param("groupBatch")String groupBatch, @Param("goodsId")String goodsId);

	int getCountBybatch(GoodsGroupParaVO goodsGroupVO);

	List<GoodsGroupParaVO> queryByBatch(GoodsGroupParaVO goodsGroupVO);

	int getCount(String groupBatch);
}