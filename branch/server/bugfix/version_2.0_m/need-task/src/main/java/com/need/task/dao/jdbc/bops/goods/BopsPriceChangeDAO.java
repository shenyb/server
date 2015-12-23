package com.need.task.dao.jdbc.bops.goods;

import com.need.task.dao.jdbc.bops.goods.po.BopsPriceChangeGoodsPO;
import com.need.task.dao.jdbc.bops.goods.po.BopsPriceChangePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BopsPriceChangeDAO {
    int deleteByPrimaryKey(Integer pricechangeId);

    int insert(BopsPriceChangePO record);


    BopsPriceChangePO selectById(Integer pricechangeId);

    int updateById(@Param("pricechangeId")Integer pricechangeId,@Param("excuted")String excuted);
    
    /**
     * @author Rylan 2015年11月18日 下午10:12:36
     * @Method: selectChangeStartTimeRecord 
     * @Description: 查询商品定时开始时间
     * @return 
     * @throws
     */
    List<BopsPriceChangeGoodsPO>  selectChangeStartTimeRecord();
    
    /**
     * @author Rylan 2015年11月18日 下午10:12:41
     * @Method: selectChangeEndTimeRecord 
     * @Description: 查询商品定时结束时间
     * @return 
     * @throws
     */
    List<BopsPriceChangeGoodsPO>  selectChangeEndTimeRecord();
    
    /**
     * @author Rylan 2015年11月23日 下午8:51:05
     * @Method: updateStartTimeById 
     * @Description: 更新状态和开始时间
     * @param pricechangeId
     * @param excuted
     * @return 
     * @throws
     */
    int updateStartTimeById(@Param("pricechangeId")Integer pricechangeId,@Param("excuted")String excuted);
    
    /**
     * @author Rylan 2015年11月23日 下午8:51:22
     * @Method: updateEndTimeById 
     * @Description: 更新状态和结束时间
     * @param pricechangeId
     * @param excuted
     * @return 
     * @throws
     */
    int updateEndTimeById(@Param("pricechangeId")Integer pricechangeId,@Param("excuted")String excuted);
}