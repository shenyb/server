package com.need.common.core.dao.jdbc.distribution;

import com.need.common.domain.po.api.distribution.UserDistributionGoodsPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.distribution.DistributionGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDistributionGoodsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(UserDistributionGoodsPO record);

    int insertSelective(UserDistributionGoodsPO record);

    UserDistributionGoodsPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserDistributionGoodsPO record);

    int updateByPrimaryKey(UserDistributionGoodsPO record);

	UserDistributionGoodsPO getByUserIdAndGoodsId(@Param("userId") String userId, @Param("goodsId")String goodsId);

	Message queryGoodsByUserId(String userId);

	List<DistributionGoodsVO> getGoodsByUserId(String userId);

	
}