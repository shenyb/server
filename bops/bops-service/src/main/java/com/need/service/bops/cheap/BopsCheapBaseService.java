package com.need.service.bops.cheap;

import java.util.List;

import com.need.domain.po.bops.cheap.BopsCheapBase;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.domain.vo.cheap.BopsCheapBaseVO;
import com.need.domain.vo.cheap.CheapPageVO;
import com.need.domain.vo.cheap.CheapStatVO;

public interface BopsCheapBaseService {

	/**
	 * 
	 * @author peiboli 2015年10月26日 下午7:55:08
	 * @Method: getCheapList 
	 * @Description: TODO获得团便宜列表
	 * @return 
	 * @throws
	 */
	List<BopsCheapBaseVO> getCheapList(CheapPageVO page);

	int addCheap(BopsCheapBase bopsCheapBase);

	BopsCheapBaseVO getCheapByid(String cheapNo);

	/**
	 * 
	 * @author peiboli 2015年10月28日 下午2:40:46
	 * @Method: auditSuccess 
	 * @Description: TODO审核
	 * @param cheapNo
	 * @return 
	 * @throws
	 */
	Message audit(String cheapNo,String auditStatus,String goodsId,Integer bopsUserId);

	/**
	 * 
	 * @author peiboli 2015年10月28日 下午6:06:04
	 * @Method: update 
	 * @Description: TODO编辑
	 * @param bopsCheapBase
	 * @return 
	 * @throws
	 */
	Boolean update(BopsCheapBase bopsCheapBase);

	Message frozen(String cheapNo, Integer userId);

	List<CheapStatVO> getCheapStatList(CheapStatVO param);



}
