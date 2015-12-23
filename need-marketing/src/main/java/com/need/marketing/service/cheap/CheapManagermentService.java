package com.need.marketing.service.cheap;

import java.util.List;

import org.springframework.ui.Model;

import com.need.marketing.dao.jdbc.cheap.po.CheapBasePO;
import com.need.marketing.dao.jdbc.cheap.po.CheapOpenUserPOKey;
import com.need.marketing.pub.Message;

import com.need.marketing.web.controller.cheap.vo.CheapVO;
import com.need.marketing.web.controller.cheap.vo.JoinUserVO;

public interface CheapManagermentService {
    /***
     * 
     * @author LXD 2015年10月27日 下午3:40:25
     * @Method: getCheapById  
     * @Description: TODO cheap 详情
     * @param cheapId
     * @param userId
     * @return 
     * @throws
     */
	CheapVO updateCheap(String cheapNo, String mobile,String userId,String cheapStatus);
   /***
    * 
    * @author LXD 2015年10月28日 上午10:26:57
    * @Method: openCheap 
    * @Description: TODO 开团
    * @param cheapNo
    * @param userId
    * @param model 
    * @throws
    */
	CheapVO openCheap(String cheapNo, String userId);
	CheapBasePO  getBaseCheapInfo(String cheapNo);
	
	
	CheapVO sharaCheap(String cheapNo,Integer cheapOpenId);
	
	/**
	 * @author Rylan 2015年8月26日 下午8:54:13
	 * @Method: sendGeneralValidateCode 
	 * @Description: TODO 发送验证码短信
	 * @param mobile
	 * @return 
	 * @throws
	 */
	Message sendGeneralValidateCode(String mobile,String cheapOpenId);
	/**
	 * 根据开团信息ID获取团的基本信息
	 * @author LXD 2015年10月30日 下午12:24:50
	 * @Method: getByCheapOpenId 
	 * @Description: TODO
	 * @param cheapOpenId
	 * @return 
	 * @throws
	 */
	CheapVO updateCheapOpenId(Integer cheapOpenId);
	/**
	 * 
	 * @author LXD 2015年10月30日 下午12:38:16
	 * @Method: getOpenUserInfo 
	 * @Description: TODO 开团人信息
	 * @param cheapOpenId
	 * @return 
	 * @throws
	 */
	JoinUserVO getOpenUserInfo(Integer cheapOpenId);
	List<JoinUserVO> getJoinUserInfo(Integer cheapOpenId);
	Message joinOpenCheap(CheapOpenUserPOKey key);
	void sendCheapSuccess(String mobile,String content,String goodsName);

   

}
