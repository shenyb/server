package com.need.dao.bops.cheap;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.api.cheap.CheapBasePO;
import com.need.domain.po.bops.cheap.BopsCheapBase;
import com.need.domain.pub.Page;
import com.need.domain.vo.cheap.BopsCheapBaseVO;
import com.need.domain.vo.cheap.CheapPageVO;

public interface BopsCheapBaseDAO {
    int deleteByPrimaryKey(String cheapNo);

    int insert(BopsCheapBase bopsCheapBase);

    int insertSelective(BopsCheapBase record);

    /**
     * 
     * @author peiboli 2015年10月28日 上午11:16:16
     * @Method: selectByPrimaryKey 
     * @Description: TODO获得cheapinfo根据id
     * @param cheapNo
     * @return 
     * @throws
     */
    BopsCheapBaseVO selectByPrimaryKey(String cheapNo);
   
    
    /**
     * 
     * @author peiboli 2015年11月6日 下午5:23:35
     * @Method: selectByCheapNo 
     * @Description: TODO根据cheapNo获得cheapinfo
     * @param cheapNo
     * @return 
     * @throws
     */
    CheapBasePO selectByCheapNo(String cheapNo);

    int updateByPrimaryKeySelective(BopsCheapBase record);

    int updateByPrimaryKey(BopsCheapBase record);

    /**
     * 
     * @author peiboli 2015年10月26日 下午7:54:25
     * @Method: queryCheapList 
     * @Description: TODO获得团便宜列表
     * @return 
     * @throws
     */
	List<BopsCheapBaseVO> queryCheapList(CheapPageVO Cheappage);

	/**
	 * 
	 * @author peiboli 2015年10月26日 下午9:50:13
	 * @Method: count 
	 * @Description: TODO获得列表数量
	 * @param page
	 * @return 
	 * @throws
	 */
	Integer count(CheapPageVO page);

	int updateAudit(@Param("cheapNo")String cheapNo, @Param("auditStatus")String auditStatus);

	/**
	 * 
	 * @author peiboli 2015年10月28日 下午6:09:58
	 * @Method: update 
	 * @Description: TODO编辑
	 * @param bopsCheapBase
	 * @return 
	 * @throws
	 */
	int update(BopsCheapBase bopsCheapBase);

	/**
	 * 
	 * @author peiboli 2015年10月28日 下午9:39:17
	 * @Method: getValidCheapList 
	 * @Description: TODO根据goodsId查看是该商品是否有生效的团便宜
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	List<BopsCheapBase> getValidCheapList(String goodsId);

	/**
	 * 
	 * @author peiboli 2015年11月7日 下午6:26:51
	 * @Method: updateFrozen 
	 * @Description: TODO根绝cheapNo，冻结团便宜
	 * @param cheapNo
	 * @param userId
	 * @return 
	 * @throws
	 */
	int updateFrozen(@Param("cheapNo") String cheapNo, @Param("userId") Integer userId);
}