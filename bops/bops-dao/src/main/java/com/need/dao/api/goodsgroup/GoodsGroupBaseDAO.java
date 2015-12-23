package com.need.dao.api.goodsgroup;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.api.goodsgroup.GoodsGroupBase;
import com.need.domain.vo.goodsgroup.GoodsGroupVO;

public interface GoodsGroupBaseDAO {
    int deleteByPrimaryKey(String groupBatch);

    int insert(GoodsGroupBase record);

    int insertSelective(GoodsGroupBase record);

    GoodsGroupBase selectByPrimaryKey(String groupBatch);

    int updateByPrimaryKeySelective(GoodsGroupBase record);

    int updateByPrimaryKey(GoodsGroupBase record);

	List<GoodsGroupVO> queryAll(GoodsGroupVO goodsGroupVO);

	int getCount(GoodsGroupVO goodsGroupVO);

	void updateStatus(@Param("groupBatch")String groupBatch, @Param("groupStatus")String groupStatus, @Param("auditId")Integer auditId);

	GoodsGroupBase getByBatch(String groupBatch);
}