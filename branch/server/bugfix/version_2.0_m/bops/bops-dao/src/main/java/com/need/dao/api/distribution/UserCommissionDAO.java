package com.need.dao.api.distribution;

import java.util.List;

import com.need.domain.po.api.distribution.UserCommissionPO;
import com.need.domain.vo.distribution.DistributionGoodsStatisticsVO;
import com.need.domain.vo.distribution.DistributionPageVO;
import com.need.domain.vo.distribution.DistributionUserStatisticsVO;

public interface UserCommissionDAO {
    int deleteByPrimaryKey(Long id);

    int insert(UserCommissionPO record);

    int insertSelective(UserCommissionPO record);

    UserCommissionPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCommissionPO record);

    int updateByPrimaryKey(UserCommissionPO record);
	/**
	 * 
	 * @author peiboli 2015年12月2日 下午8:53:54
	 * @Method: queryDistributionGoodsStatisticsList 
	 * @Description: TODO获得分销商品统计列表
	 * @return 
	 * @throws
	 */
	List<DistributionGoodsStatisticsVO> queryDistributionGoodsStatisticsList(DistributionPageVO params);

	/**
	 * 
	 * @author peiboli 2015年12月2日 下午9:14:40
	 * @Method: count 
	 * @Description: TODO获得分销商品统计列表总数
	 * @param params
	 * @return 
	 * @throws
	 */
	Integer countGoods(DistributionPageVO params);

	/**
	 * 
	 * @author peiboli 2015年12月3日 上午10:57:13
	 * @Method: countUser 
	 * @Description: TODO用户佣金统计数量
	 * @param params
	 * @return 
	 * @throws
	 */
	Integer countUser(DistributionPageVO params);
	/**
	 * 
	 * @author peiboli 2015年12月3日 上午10:58:05
	 * @Method: queryDistributionUserStatisticsList 
	 * @Description: TODO用户佣金统计
	 * @param params
	 * @return 
	 * @throws
	 */
	List<DistributionUserStatisticsVO> queryDistributionUserStatisticsList(DistributionPageVO params);
    
}