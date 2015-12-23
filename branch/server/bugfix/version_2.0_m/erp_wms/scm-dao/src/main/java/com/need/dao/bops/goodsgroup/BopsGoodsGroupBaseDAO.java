package com.need.dao.bops.goodsgroup;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.goodsgroup.BopsGoodsGroupBase;
import com.need.domain.vo.goodsgroup.GoodsGroupParaVO;
import com.need.domain.vo.goodsgroup.GoodsGroupResultVO;
import com.need.domain.vo.goodsgroup.GoodsGroupVO;

public interface BopsGoodsGroupBaseDAO {
    int deleteByPrimaryKey(String groupBatch);

    int insert(BopsGoodsGroupBase record);

    int insertSelective(BopsGoodsGroupBase record);

    BopsGoodsGroupBase selectByPrimaryKey(String groupBatch);

    int updateByPrimaryKeySelective(BopsGoodsGroupBase record);

    int updateByPrimaryKey(BopsGoodsGroupBase record);

	int getCount(GoodsGroupVO goodsGroupVO);

	List<GoodsGroupResultVO> queryAll(GoodsGroupVO goodsGroupVO);

	void updateStatus(@Param("groupBatch")String groupBatch, @Param("groupStatus")String groupStatus, @Param("auditId")Integer auditId);

	int getCountBybatch(GoodsGroupParaVO goodsGroupVO);

	List<GoodsGroupParaVO> queryByBatch(GoodsGroupParaVO goodsGroupVO);
}