package com.need.marketing.dao.jdbc.cheap;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.marketing.dao.jdbc.cheap.po.CheapOpenUserPO;
import com.need.marketing.dao.jdbc.cheap.po.CheapOpenUserPOKey;
import com.need.marketing.web.controller.cheap.vo.CheapUserVO;
import com.need.marketing.web.controller.cheap.vo.JoinUserVO;
import com.need.marketing.web.controller.cheap.vo.ShareCheapVO;

public interface CheapOpenUserDAO {
    int deleteByPrimaryKey(CheapOpenUserPOKey key);

    int insert(CheapOpenUserPO record);

    int insertSelective(CheapOpenUserPO record);

    CheapOpenUserPO selectByPrimaryKey(CheapOpenUserPOKey key);

    int updateByPrimaryKeySelective(CheapOpenUserPO record);

    int updateByPrimaryKey(CheapOpenUserPO record);

    List<CheapUserVO> getByParames(@Param("mobile")String mobile, @Param("cheapNo")String cheapNo);

	List<ShareCheapVO> getShareList(int cheapOpenId);

	List<CheapOpenUserPO> getListByOpenId(Integer cheapOpenId);

	List<JoinUserVO> getJoinUser(Integer cheapOpenId);
    
	List<CheapOpenUserPO> getBycheapNoAndMobile(CheapOpenUserPOKey key);

	List<CheapOpenUserPO> queryByParams(@Param("mobile")String mobile, @Param("cheapNo")String cheapNo, @Param("traded")String traded);
}