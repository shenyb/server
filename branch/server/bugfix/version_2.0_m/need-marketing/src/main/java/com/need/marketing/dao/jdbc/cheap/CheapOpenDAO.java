package com.need.marketing.dao.jdbc.cheap;

import org.apache.ibatis.annotations.Param;

import com.need.marketing.dao.jdbc.cheap.po.CheapOpenPO;
import com.need.marketing.web.controller.cheap.vo.CheapVO;
import com.need.marketing.web.controller.cheap.vo.ShareCheapVO;

public interface CheapOpenDAO {
    int deleteByPrimaryKey(Integer cheapOpenId);

    void insert(CheapOpenPO record);

    int insertSelective(CheapOpenPO record);

    CheapOpenPO selectByPrimaryKey(Integer cheapOpenId);

    int updateByPrimaryKeySelective(CheapOpenPO record);

    int updateByPrimaryKey(CheapOpenPO record);

	CheapOpenPO getByParames(@Param("userId")String userId, @Param("cheapNo")String cheapNo);

	ShareCheapVO  getShareInfo(String cheapOpenId);

	CheapVO getByOpenId(Integer cheapOpenId);

	void updateStatus(Integer cheapOpenId);
    
	
}