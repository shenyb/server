package com.need.common.core.dao.jdbc.cheap;

import com.need.common.domain.po.api.cheap.CheapBasePO;
import com.need.common.domain.pub.Page;
import com.need.common.domain.vo.cheap.CheapBaseBymobileVO;
import com.need.common.domain.vo.cheap.CheapShowStatus;
import com.need.common.domain.vo.cheap.CheapVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


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
	List<CheapBasePO> queryCloseCheapByMobile(String mobile);

	/**
	 * 
	 * @author peiboli 2015年10月24日 下午4:51:33
	 * @Method: queryAvailableCheapByUserId 
	 * @Description: TODO我的进行中团便宜列表
	 * @param mobile
	 * @return 
	 * @throws
	 */
	List<CheapBasePO> queryAvailableCheapByMobile(String mobile);

	/**
	 * 
	 * @author peiboli 2015年10月26日 上午10:28:39
	 * @Method: getCheapByMobile 
	 * @Description: TODo 根据手机号和cheapNo来确定团便宜列表
	 * @return 
	 * @throws
	 */
	List<CheapVO> getCheapByMobile(@Param("cheapNo") String cheapNo,@Param("mobile")String mobile);

	/**
	 * 
	 * @author peiboli 2015年11月9日 下午8:23:12
	 * @Method: queryCheapPageByMobile 
	 * @Description: TODO传mobile是得到的cheapList
	 * @return 
	 * @throws
	 */
	List<CheapBaseBymobileVO> queryCheapPageByMobile(Page page);

	/**
	 * 
	 * @author peiboli 2015年11月9日 下午8:32:58
	 * @Method: queryShowList 
	 * @Description: TODO根据手机号，获得该用户参加过的团的列表
	 * @param mobile
	 * @return 
	 * @throws
	 */
	List<CheapShowStatus> queryShowList(String mobile);

	/**
	 * 
	 * @author peiboli 2015年11月10日 上午10:39:41
	 * @Method: Cheapcount 团便宜首页列表总数
	 * @Description: TODO
	 * @return 
	 * @throws
	 */
	Integer Cheapcount();
}