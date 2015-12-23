package com.need.dao.api.distribution;

import com.need.domain.po.api.distribution.DistributionGoodsPO;

public interface ApiDistributionGoodsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(com.need.domain.po.bops.distribution.DistributionGoodsPO record);

    int insertSelective(DistributionGoodsPO record);

    DistributionGoodsPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DistributionGoodsPO record);

    int updateByPrimaryKey(DistributionGoodsPO record);

    /**
     * 
     * @author peiboli 2015年12月1日 下午6:37:59
     * @Method: frozen 
     * @Description: TODO冻结
     * @param id 
     * @throws
     */
	void frozen(String id);
}