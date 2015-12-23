package com.need.common.core.service.need;

import com.need.common.domain.po.api.need.NeedFeedCommentPO;
import com.need.common.domain.po.api.need.NeedFeedPO;
import com.need.common.domain.po.api.need.NeedGoodsPO;
import com.need.common.domain.po.api.need.NeedKolFansPO;
import com.need.common.domain.pub.Message;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * <p></p>
 * @author LXD 2015年8月11日 上午11:47:08
 * @ClassName NeedService
 * @Description TODO 用户need商品
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月11日
 * @modify by reason:{方法名}:{原因}
 */
public interface NeedService {
   /**
    * 
    * @author LXD 2015年8月11日 下午2:21:23
    * @Method: addNeedGoods 
    * @Description: TODO 用户need商品
    * @param needGoodsPO
    * @return 
    * @throws
    */

	 @Transactional

	int addNeedGoods(NeedGoodsPO needGoodsPO);
    /**
     * 
     * @author LXD 2015年8月11日 下午2:21:42
     * @Method: getNeedGoodsPO 
     * @Description: TODO 根据用户ID和商品ID获得NeedGoodsPO
     * @param userId
     * @param goodsId
     * @return 
     * @throws
     */
     NeedGoodsPO getNeedGoodsPO(String userId, String goodsId,String needStatus);
	 /**
	  * 
	  * @author LXD 2015年8月11日 下午7:15:35
	  * @Method: updateNeedGoods 
	  * @Description: TODO 更新needGoodsPO
	  * @param needGoodsPO
	  * @return 
	  * @throws
	  */


	int updateNeedGoods(NeedGoodsPO needGoodsPO);
	 /**
	  * 
	  * @author LXD 2015年8月11日 下午7:15:56
	  * @Method: getNeedKolFansPO 
	  * @Description: TODO 根据userId 和行家ID查询
	  * @param userId
	  * @param kolId
	  * @return 
	  * @throws
	  */
	 boolean getNeedKolFansPO(String userId, String kolId,String status);
	 /**
	  * 
	  * @author LXD 2015年8月11日 下午7:16:24
	  * @Method: addFocusKol 
	  * @Description: TODO 更新needKolFansPO
	  * @param needKolFansPO 
	  * @throws
	  */
	 void addFocusKol(NeedKolFansPO needKolFansPO);
	 int getConcernCount(String userId);

     int cancelFocusKol(NeedKolFansPO needKolFansPO);

	void addfeedComment(NeedFeedCommentPO needFeedComment);
	void kolPublishFeed(NeedFeedPO needFeedPO);
	void changeNeedStatus(NeedGoodsPO needGoodsPO);
	NeedKolFansPO getByParams(String userId, String kolId,String status);
	void updatNeedKolFansPO(NeedKolFansPO needKolFansPO);

	
	Message getNeedGoodsList(String userId, Integer pageNum, Integer pageSize);
	
	/**
	 * 
	 * @author LXD 2015年8月25日 下午4:53:47
	 * @Method: addfeedComment 
	 * @Description: TODO feed 添加评论
	 * @param feedId
	 * @param userId
	 * @param content
	 * @return 
	 * @throws
	 */
	Message addfeedComment(String feedId, String userId, String content);
	/***
	 * 
	 * @author LXD 2015年8月25日 下午5:09:01
	 * @Method: cancelFocusKol 
	 * @Description: TODO 用户取消关注
	 * @param userId
	 * @param kolId
	 * @return 
	 * @throws
	 */
	Message cancelFocusKol(String userId, String kolId);
	/**
	 * 
	 * @author LXD 2015年8月25日 下午5:21:36
	 * @Method: queryFeedComments 
	 * @Description: TODO 获取一条feed的评论
	 * @param feedId
	 * @return 
	 * @throws
	 */
	Message queryFeedComments(String feedId,int pageNum,int pageSize);
	NeedKolFansPO getNeedKolFans(String userId, String trim);
	void  saveNeedKolFans(String userId, String trim);
	/**
	 * 
	 * @author peiboli 2015年9月20日 下午12:29:29
	 * @Method: getNeedGoodsList 
	 * @Description: TODO1.0版本的获得need的商品列表
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @param false1
	 * @return 
	 * @throws
	 */
	Message getNeedGoodsList(String userId, Integer pageNum, Integer pageSize, Boolean isGolbal);
	/**
	 * 
	 * @author peiboli 2015年12月18日 下午5:21:36
	 * @Method: addFocus 
	 * @Description: TODO批量关注用户
	 * @param userId
	 * @param kolId
	 * @return 
	 * @throws
	 */
	Message addFocus(String userId, String[] kolId);

}
