package com.need.dao.api.cheap;

import java.util.List;

import com.need.domain.po.api.cheap.CheapBasePO;
import com.need.domain.vo.cheap.CheapStatVO;

public interface CheapBaseDAO {
    int deleteByPrimaryKey(String cheapNo);

    int insert(CheapBasePO bopsCheapBase);

    int insertSelective(CheapBasePO record);

    CheapBasePO selectByPrimaryKey(String cheapNo);

    int updateByPrimaryKeySelective(CheapBasePO record);

    int updateByPrimaryKey(CheapBasePO record);

    /**
     * 
     * @author peiboli 2015年11月7日 下午6:43:56
     * @Method: updateFrozen 
     * @Description: TODO冻结cheap
     * @param cheapNo 
     * @throws
     */
	void updateFrozen(String cheapNo);

	/**
	 * 
	 * @author peiboli 2015年11月9日 上午11:04:16
	 * @Method: queryCheapStatPage 
	 * @Description: TODO团便宜统计数据，没有参加人数和支付人数
	 * @param param
	 * @return 
	 * @throws
	 */
	List<CheapStatVO> queryCheapStatPage(CheapStatVO param);

	/**
	 * 
	 * @author peiboli 2015年11月9日 上午11:06:27
	 * @Method: cheapStatCount 
	 * @Description: TODO团便宜数量
	 * @param param
	 * @return 
	 * @throws
	 */
	Integer cheapStatCount(CheapStatVO param);

	/**
	 * 
	 * @author peiboli 2015年11月9日 下午2:50:27
	 * @Method: queryJoinAndPay 
	 * @Description: TODO获得参加人数和支付人数
	 * @param param
	 * @return 
	 * @throws
	 */
	List<CheapStatVO> queryJoinAndPay(CheapStatVO param);
}