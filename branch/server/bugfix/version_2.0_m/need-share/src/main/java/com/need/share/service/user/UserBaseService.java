package com.need.share.service.user;

import java.util.List;

import com.need.share.dao.jdbc.api.user.po.UserBasePO;
import com.need.share.web.controller.kol.vo.GoodsMainVO;
import com.need.share.web.controller.kol.vo.TradeGoodVO;
import com.need.share.web.controller.kol.vo.UserInfoVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p></p>
 * @author Rylan 2015年8月25日 上午1:02:45
 * @ClassName UserBaseService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月25日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserBaseService {
	
	/**
	 * @author Rylan 2015年8月25日 上午11:03:40
	 * @Method: getUserInfo 
	 * @Description: TODO
	 * @param userId
	 * @return 
	 * @throws
	 */
	UserInfoVO   getUserInfo(String userId);
	
	/**
	 * @author Rylan 2015年8月25日 上午11:37:52
	 * @Method: getNeedGoodsList 
	 * @Description: TODO
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return 
	 * @throws
	 */
	List<GoodsMainVO> getNeedGoodsList(String userId, Integer pageNum, Integer pageSize);
	
	
	/**
	 * @author Rylan 2015年8月25日 下午1:18:39
	 * @Method: getTradeGoodsList 
	 * @Description: TODO
	 * @param userId
	 * @return 
	 * @throws
	 */
	public List<TradeGoodVO> getTradeGoodsList(String userId,Integer pageNum, Integer pageSize);


	/**
	 * @author shenyb 2015年7月25日 上午3:25:09 @Method:
	 *         selectByMobileAndLoginPwd @Description: 根据手机号和登录密码查询 @param
	 *         user @throws
	 * mapper
	 */
	public List<UserBasePO> selectByMobile(@Param("mobile")String mobile);
	
}
