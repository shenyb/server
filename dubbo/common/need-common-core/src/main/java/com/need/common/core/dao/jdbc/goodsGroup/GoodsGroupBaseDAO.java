package com.need.common.core.dao.jdbc.goodsGroup;


import com.need.common.domain.po.api.goodsGroup.GoodsGroupBase;
import org.apache.ibatis.annotations.Param;



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