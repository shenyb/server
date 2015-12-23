package com.need.share.dao.jdbc.api.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.need.share.dao.jdbc.api.goods.po.GoodsMainPO;
import com.need.share.dao.jdbc.api.user.po.UserBasePO;
import com.need.share.web.controller.kol.vo.KolInfoVo;
import com.need.share.web.controller.kol.vo.TradeGoodVO;



/**
 * <p>
 * </p>
 * 
 * @author Rylan 2015年7月25日 上午11:51:28
 * @ClassName UserBaseDAO
 * @Description UserBase数据访问层 ,此处注意一定不能使用select * .mysql 的sql语句要用小写
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public interface UserBaseDAO {

	/**
	 * @author Rylan 2015年8月25日 上午12:49:23
	 * @Method: selectUserBaseByUserId 
	 * @Description: TODO
	 * @param userId
	 * @return 
	 * @throws
	 */
	UserBasePO getUserBaseByUserId(@Param("userId")String userId);


	/**
	 * @author Rylan 2015年8月25日 上午1:13:21
	 * @Method: getKolInfoBykolId 
	 * @Description: TODO
	 * @param kolId
	 * @return 
	 * @throws
	 */
	KolInfoVo getKolInfoBykolId(@Param("kolId")String kolId);


	/**
	 * @author Rylan 2015年8月25日 上午11:22:49
	 * @Method: querycategoryNameList 
	 * @Description: TODO
	 * @param userId
	 * @return 
	 * @throws
	 */
	List<String> querycategoryNameList(String userId);
	
	/**
	 * @author Rylan 2015年8月25日 下午12:15:40
	 * @Method: selectNeedGoods 
	 * @Description: TODO
	 * @param userId
	 * @return 
	 * @throws
	 */
	Page<GoodsMainPO> queryNeedGoods(String userId);
	
	/**
	 * @author Rylan 2015年8月25日 下午1:20:25
	 * @Method: selectTradeGoods 
	 * @Description: TODO
	 * @param userId
	 * @return 
	 * @throws
	 */
	Page<TradeGoodVO> queryTradeGoods(String userId);


	/**
	 * @author shenyb 2015年7月25日 上午3:25:09 @Method:
	 *         selectByMobileAndLoginPwd @Description: 根据手机号和登录密码查询 @param
	 *         user @throws
	 * mapper
	 */
	List<UserBasePO> selectByMobile(@Param("mobile")String mobile);

}