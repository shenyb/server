package com.need.common.core.dao.jdbc.cheap;

import com.need.common.domain.po.api.cheap.CheapBasePO;
import com.need.common.domain.po.api.cheap.CheapOpenPO;

import java.util.List;

public interface CheapOpenDAO {
    int deleteByPrimaryKey(Integer cheapOpenId);

    int insert(CheapOpenPO record);

    int insertSelective(CheapOpenPO record);

    CheapOpenPO selectByPrimaryKey(Integer cheapOpenId);

    int updateByPrimaryKeySelective(CheapOpenPO record);

    int updateByPrimaryKey(CheapOpenPO record);
    /**
     * 
     * @author peiboli 2015年10月23日 下午6:47:11
     * @Method: queryCheapPage 
     * @Description: TODO用户获取已关闭的团便宜列表
     * @return 
     * @throws
     */
	List<CheapBasePO> queryCloseCheapByMobile(String mobile);

	/**
	 * 
	 * @author peiboli 2015年10月24日 下午4:51:33
	 * @Method: queryAvailableCheapByUserId 
	 * @Description: TODO我的进行中团便宜列表
	 * @param mobile
	 * @return 
	 * @throws
	 */
	List<CheapBasePO> queryAvailableCheapByMobile(String mobile);
}