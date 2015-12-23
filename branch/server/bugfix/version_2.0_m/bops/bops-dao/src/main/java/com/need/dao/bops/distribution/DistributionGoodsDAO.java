package com.need.dao.bops.distribution;

import java.util.List;

import com.need.domain.po.bops.distribution.DistributionGoodsPO;
import com.need.domain.vo.distribution.DistributionGoodsStatisticsVO;
import com.need.domain.vo.distribution.DistributionListVO;
import com.need.domain.vo.distribution.DistributionPageVO;

public interface DistributionGoodsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(DistributionGoodsPO record);

    int insertSelective(DistributionGoodsPO record);

    DistributionGoodsPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DistributionGoodsPO record);

    int updateByPrimaryKey(DistributionGoodsPO record);

    /**
     * 
     * @author peiboli 2015年12月1日 上午11:02:00
     * @Method: queryByPage 
     * @Description: TODO分页获取分销商品列表
     * @param params
     * @return 
     * @throws
     */
	List<DistributionListVO> queryByPage(DistributionPageVO params);

	/**
	 * 
	 * @author peiboli 2015年12月1日 上午11:01:38
	 * @Method: count 
	 * @Description: TODO差分销商品总数
	 * @param params
	 * @return 
	 * @throws
	 */
	Integer count(DistributionPageVO params);

	/**
	 * 
	 * @author peiboli 2015年12月1日 下午4:21:46
	 * @Method: updateAudit 
	 * @Description: TODO审核
	 * @param id
	 * @return 
	 * @throws
	 */
	int updateAudit(String id);

	DistributionGoodsPO getDistributionByGoodsId(String goodsId);

	/**
	 * 
	 * @author peiboli 2015年12月1日 下午6:32:34
	 * @Method: updateFrozen 
	 * @Description: TODO冻结
	 * @param id
	 * @return 
	 * @throws
	 */
	int updateFrozen(String id);

	/**
	 * 
	 * @author peiboli 2015年12月1日 下午7:09:44
	 * @Method: updateEdit 
	 * @Description: TODO编辑
	 * @param params
	 * @return 
	 * @throws
	 */
	int updateEdit(DistributionGoodsPO params);

	/**
	 * 
	 * @author peiboli 2015年12月1日 下午7:58:31
	 * @Method: getDistributionById 
	 * @Description: TODO根据id获取详情，用于编辑页
	 * @param id
	 * @return 
	 * @throws
	 */
	DistributionListVO getDistributionById(String id);


}