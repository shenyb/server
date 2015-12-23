package com.need.common.core.dao.jdbc.trade;

import com.need.common.domain.po.api.trade.TradeCartPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeCartDAO {

	int deleteByCartIds(@Param("cartIds") String cartIds);

	int deleteByCartId(@Param("cartId") String cartId);

	int insert(TradeCartPO record);

	int insertSelective(TradeCartPO record);

	TradeCartPO selectByPrimaryKey(@Param("cardId") String cardId);

	int updateByPrimaryKeySelective(TradeCartPO record);

	int updateByPrimaryKey(TradeCartPO record);

	TradeCartPO getByUserIdAndGoodsId(@Param("userId") String userId, @Param("goodsId") String goodsId);

	List<TradeCartPO> selectByPage(@Param("userId") String userId);

	int modifyGoodsCount(@Param("modify") int modify, @Param("cardId") String cartId);

	int updateGoodsCount(int goodsCount, String cartId);

	List<TradeCartPO> queryByUserId(String userId);

	int getGoodsCountByUserId(String userId);
	int batchDeleteByCartIds(List<String> cartIdsList);

}