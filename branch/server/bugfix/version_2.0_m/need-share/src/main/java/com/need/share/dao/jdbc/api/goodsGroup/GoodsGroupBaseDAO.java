package com.need.share.dao.jdbc.api.goodsGroup;

import org.apache.ibatis.annotations.Param;

import com.need.share.dao.jdbc.api.goodsGroup.po.GoodsGroupBase;



public interface GoodsGroupBaseDAO {
    int deleteByPrimaryKey(String groupBatch);

    int insert(GoodsGroupBase record);

    int insertSelective(GoodsGroupBase record);

    GoodsGroupBase selectByPrimaryKey(String groupBatch);

    int updateByPrimaryKeySelective(GoodsGroupBase record);

    int updateByPrimaryKey(GoodsGroupBase record);

	void updateStatus(@Param("groupBatch")String groupBatch, @Param("groupStatus")String groupStatus, @Param("auditId")Integer auditId);

	GoodsGroupBase getByBatch(String groupBatch);
}