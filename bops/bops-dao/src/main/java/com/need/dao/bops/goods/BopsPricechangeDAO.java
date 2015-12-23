package com.need.dao.bops.goods;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.goods.BopsPricechange;
import com.need.domain.po.bops.goods.BopsPricechangeGoods;
import com.need.domain.vo.goods.GoodsPriceChangeParamVO;
import com.need.domain.vo.goods.GoodsPriceChangeResultVO;

public interface BopsPricechangeDAO {
	int deleteByPrimaryKey(Integer pricechangeId);

	int insert(BopsPricechange record);

	int insertByPriceType(BopsPricechange record);

	BopsPricechange selectByPrimaryKey(Integer pricechangeId);

	int updatePricechangeStatus(@Param("audit") String audit, @Param("pricechangeId") Integer pricechangeId,
			@Param("auditorId") Integer auditorId);

	int updateExcuted(@Param("excuted") String excuted, @Param("pricechangeId") Integer pricechangeId);

	/**
	 * @author Rylan 2015年11月23日 下午8:51:05
	 * @Method: updateStartTimeById
	 * @Description: 更新状态和开始时间
	 * @param pricechangeId
	 * @param excuted
	 */
	int updateStartTimeById(@Param("pricechangeId") Integer pricechangeId, @Param("excuted") String excuted);

	/**
	 * @author Rylan 2015年11月23日 下午8:51:22
	 * @Method: updateEndTimeById
	 * @Description: 更新状态和结束时间
	 * @param pricechangeId
	 * @param excuted
	 */
	int updateEndTimeById(@Param("pricechangeId") Integer pricechangeId, @Param("excuted") String excuted);

	int updateStartTime(Integer pricechangeId);

	int updateEndTime(Integer pricechangeId);

	/**
	 * @author Rylan 2015年11月18日 下午10:12:36
	 * @Method: selectChangeStartTimeRecord
	 * @Description: 查询商品定时开始时间
	 */
	List<BopsPricechangeGoods> selectChangeStartTimeRecord();

	/**
	 * @author Rylan 2015年11月18日 下午10:12:41
	 * @Method: selectChangeEndTimeRecord
	 * @Description: 查询商品定时结束时间
	 */
	List<BopsPricechangeGoods> selectChangeEndTimeRecord();

	List<GoodsPriceChangeResultVO> queryPriceChange(GoodsPriceChangeParamVO goodsPriceChangeParamVO);

	GoodsPriceChangeResultVO getPriceChangeById(int pricechangeId);

	int countPriceChange(GoodsPriceChangeParamVO goodsPriceChangeParamVO);

	int updateByPrimaryKeySelective(BopsPricechange record);

	int updateByPrimaryKey(BopsPricechange record);
}