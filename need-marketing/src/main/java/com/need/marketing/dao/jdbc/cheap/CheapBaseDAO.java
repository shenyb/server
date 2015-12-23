package com.need.marketing.dao.jdbc.cheap;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.marketing.dao.jdbc.cheap.po.CheapBasePO;
import com.need.marketing.web.controller.cheap.vo.CheapVO;


public interface CheapBaseDAO {
    
    CheapBasePO selectById(String cheapNo);
    
    int deleteByPrimaryKey(String cheapNo);

    int insert(CheapBasePO record);

    int insertSelective(CheapBasePO record);


    int updateByPrimaryKeySelective(CheapBasePO record);

    int updateByPrimaryKey(CheapBasePO record);

    /**
     * 
     * @author peiboli 2015年10月23日 下午6:47:11
     * @Method: queryCheapPage 
     * @Description: TODO获取团便宜列表
     * @return 
     * @throws
     */
	List<CheapBasePO> queryCheapPage();
	
	 /**
     * 
     * @author peiboli 2015年10月23日 下午6:47:11
     * @Method: queryCheapPage 
     * @Description: TODO用户获取已关闭的团便宜列表
     * @return 
     * @throws
     */
	List<CheapBasePO> queryCheapByUserId(String mobile);

	/**
	 * 
	 * @author peiboli 2015年10月24日 下午4:51:33
	 * @Method: queryAvailableCheapByUserId 
	 * @Description: TODO我的进行中团便宜列表
	 * @param mobile
	 * @return 
	 * @throws
	 */
	List<CheapBasePO> queryAvailableCheapByUserId(String mobile);

	/**
	 * 
	 * @author peiboli 2015年10月26日 上午10:28:39
	 * @Method: getCheapByMobile 
	 * @Description: TODo 根据手机号和cheapNo来确定团便宜列表
	 * @return 
	 * @throws
	 */
	List<CheapVO> getCheapByMobile(@Param("cheapNo") String cheapNo,@Param("mobile")String mobile);
}