package com.need.common.core.dao.jdbc.need;

import com.github.pagehelper.Page;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.need.NeedGoodsPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NeedGoodsDAO {
	/**
	 * 
	 * @author LXD 2015年8月21日 下午2:49:33
	 * @Method: deleteById
	 * @Description: TODO 删除
	 * @param id
	 */
	int deleteById(Integer id);

	/**
	 * 
	 * @author LXD 2015年8月21日 下午2:49:47
	 * @Method: insert
	 * @Description: TODO 插入
	 * @param record
	 */
	int insert(NeedGoodsPO record);

	/**
	 * 
	 * @author LXD 2015年8月21日 下午2:49:59
	 * @Method: getById
	 * @Description: TODO 根据ID查看
	 * @param id
	 */
	NeedGoodsPO getById(Integer id);

	/***
	 * 
	 * @author LXD 2015年8月21日 下午2:50:15
	 * @Method: update
	 * @Description: TODO 更新记录
	 * @param record
	 */

	int update(NeedGoodsPO record);

	/**
	 * 
	 * @author LXD 2015年8月21日 下午2:50:33
	 * @Method: getNeedGoodsCount
	 * @Description: TODO 该商品need count
	 * @param goosId
	 */
	int getNeedGoodsCount(String goosId);

	/**
	 * 
	 * @author LXD 2015年8月21日 下午2:50:51
	 * @Method: queryByParams
	 * @Description: TODO
	 * @param userId
	 * @param goodsId
	 * @param needStatus
	 */
	NeedGoodsPO queryByParams(@Param("userId") String userId, @Param("goodsId") String goodsId,
			@Param("needStatus") String needStatus);

	/***
	 * 
	 * @author LXD 2015年8月21日 下午2:51:27
	 * @Method: getIsNeed
	 * @Description: TODO
	 * @param userId
	 * @param goodsId
	 */
	NeedGoodsPO getIsNeed(@Param("userId") String userId, @Param("goodsId") String goodsId);

	

	/**
	 * 
	 * @author LXD 2015年8月21日 下午2:51:37
	 * @Method: selectNeedGoods
	 * @Description: TODO1.1
	 * @param userId
	 */
	List<GoodsMainPO> queryNeedList(@Param("userId")String userId);

	String countIsNeed(@Param("goodsId") String userId, @Param("goodsId") String goodsId);

	/**
	 * 
	 * @author xiehao 2015年9月11日 下午6:33:40
	 * @Method: countIsNeeded 
	 * @Description: TODO 该商品是否被该用户Need
	 * @param userId
	 * @param goodsId
	 */
	Boolean countIsNeeded(@Param("userId") String userId, @Param("goodsId") String goodsId);

	/**
	 * 
	 * @author peiboli 2015年9月20日 下午12:34:00
	 * @Method: selectNeedGoods 
	 * @Description: TODO获得need商品列表，不显示保税仓商品
	 * @param userId
	 * @param isGolbal
	 * @return 
	 * @throws
	 */
	Page<GoodsMainPO> selectNeedGoods(@Param("userId")String userId, @Param("isGolbal")String isGolbal);

}