package com.need.dao.bops.dic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.dic.BopsDic;


public interface BopsDicDAO {
    int deleteByPrimaryKey(Integer codeId);

    int insert(BopsDic record);

    int insertSelective(BopsDic record);

    BopsDic selectByPrimaryKey(Integer codeId);

    int updateByPrimaryKeySelective(BopsDic record);

    int updateByPrimaryKey(BopsDic record);
    
    List<BopsDic> getDicByCodeType(@Param("codeType")String codeType);
}