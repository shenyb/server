package com.need.common.core.dao.jdbc.distribution;

import com.need.common.domain.po.api.distribution.DistributionGoodsPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionGoodsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(DistributionGoodsPO record);

    int insertSelective(DistributionGoodsPO record);

    DistributionGoodsPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DistributionGoodsPO record);

    int updateByPrimaryKey(DistributionGoodsPO record);

    /**
     * @author peiboli 2015年12月1日 下午6:37:59 @Method: frozen @Description:
     * TODO冻结 @param id @throws
     */
    void frozen(String id);

    /**
     * @param goodsId
     * @return @throws
     * @author shenyb 2015年12月3日 下午4:01:42 @Method:
     * queryByGoodsIdAndDistributionStatus @Description:
     */

    DistributionGoodsPO getByGoodsId(@Param("goodsId") String goodsId);

    DistributionGoodsPO getByGoodsIdAndDistributionStatus(@Param("goodsId") String goodsId,
                                                          @Param("distributionStatus") String distributionStatus);
}