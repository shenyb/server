package com.need.common.core.service.need.impl;

import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.need.NeedFeedCommentDAO;
import com.need.common.core.dao.jdbc.need.NeedFeedDAO;
import com.need.common.core.dao.jdbc.need.NeedGoodsDAO;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.service.need.NeedFeedService;
import com.need.common.domain.enums.NeedStatusEnum;
import com.need.common.domain.enums.UserNeedStatus;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.need.NeedFeedPO;
import com.need.common.domain.po.api.need.NeedGoodsPO;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.need.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NeedFeedServiceImpl implements NeedFeedService {
	@Autowired
	private NeedFeedDAO needFeedDAO;
	@Autowired
	private NeedGoodsDAO needGoodsDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	
	@Autowired
	private UserBaseDAO userBaseDAO;
	@Autowired
	private NeedFeedCommentDAO needFeedCommentDAO;
    /***
     * 
     * @author LXD 2015年9月9日 上午11:56:21
     * @Method: getHomeFeeds 
     * @Description: TODO 首页运营位信息
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return 
     * @see com.need.api.service.need.NeedFeedService#getHomeFeeds(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
	@Override
	public Message getHomeFeeds(String userId, Integer pageNum, Integer pageSize) {
		Message message=Message.success();
		/***
		 * 正常用户登录
		 */
		if(StringUtils.isNotBlank(userId)){
			FeedsParamsVO params=new FeedsParamsVO();
		    params.setCurrentPage(pageNum);
		    params.setPageSize(pageSize);
		    params.setUserId(userId);
		    int totalCount= needFeedDAO.getCountParam_V1_1(userId);
		    params.setTotalCount(totalCount);
		    List<NeedFeedPO>  feedLsit=	needFeedDAO.queryBypageParams_V1_1(params);
		    /***
		     * 如果用户关注的行家有feed，则显示  
		     */
		    if(feedLsit!=null&&feedLsit.size()>0){
		     List<FeedsVO> resultList= this.convertResult(feedLsit, userId);
				message.addData("feedList", resultList); 
				message.addData("totalCount", totalCount);
				 return message;
		    }else{
			  	  /***
			  	   * 如果用户没有关注行家或是所关注的行家发表的新鲜事为0，则查所有feed流按时间倒序
			  	   */
		    	FeedsParamsVO param=new FeedsParamsVO();
			    param.setCurrentPage(pageNum);
			    param.setPageSize(pageSize);
			    param.setUserId(userId);
		    	
		    	 int count=  needFeedDAO.getCount_V1_1();
		    	 param.setTotalCount(count);
		    	List<NeedFeedPO> allFeeds= needFeedDAO.queryByPage_V1_1(param);
			    	    if(allFeeds!=null&&allFeeds.size()>0){
			    	     List<FeedsVO> resultList= this.convertResult(allFeeds, userId);
			    	     message.addData("feedList", resultList); 
			 			 message.addData("totalCount", count);    
			    	    }
			   return message;		         
			}	
		}else{
			/**
			 * 游客登录，分页展示所有的feed
			 */
			FeedsParamsVO param=new FeedsParamsVO();
		    param.setCurrentPage(pageNum);
		    param.setPageSize(pageSize);	
	    	int count=  needFeedDAO.getCount_V1_1();
	    	param.setTotalCount(count);
	    	List<NeedFeedPO> allFeeds= needFeedDAO.queryByPage_V1_1(param);
		    	    if(allFeeds!=null&&allFeeds.size()>0){
		    	     List<FeedsVO> resultList= this.convertResult(allFeeds, userId);
		    	     message.addData("feedList", resultList); 
		 			 message.addData("totalCount", count);    
		    	    }
		   return message;		         
			
		}
		
	}
	/**
	 * 
	 * @author LXD 2015年9月16日 下午3:06:23
	 * @Method: convertResult 
	 * @Description: TODO 封装返回结果
	 * @param feedLsit
	 * @param userId
	 * @return 
	 * @throws
	 */
	private List<FeedsVO> convertResult(List<NeedFeedPO> feedLsit, String userId) {
		List<FeedsVO> resultList = new ArrayList<FeedsVO>();
		for (NeedFeedPO needFeed : feedLsit) {
			FeedsVO feedsVO = new FeedsVO();
			feedsVO.setFeedId(needFeed.getFeedId());
			feedsVO.setContent(needFeed.getMemo());
			if (StringUtils.isNotBlank(userId)) {
				NeedGoodsPO needGoodsPO = needGoodsDAO.queryByParams(userId, needFeed.getGoodsId(),
						UserNeedStatus.NEED.code);
				if (needGoodsPO != null) {
					feedsVO.setIsNeed(NeedStatusEnum.NEED.code);
				} else {
					feedsVO.setIsNeed(NeedStatusEnum.CANCEL.code);
				}
				/**
				 * 游客登陆isNeed直接返回FALSE
				 */
			} else {
				feedsVO.setIsNeed(NeedStatusEnum.CANCEL.code);
			}
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(needFeed.getGoodsId());
			if (goods != null) {
				FeedGoodsVO feedGoods = convertGoods(goods);
				feedsVO.setGoods(feedGoods);
			}
			// 根据needFeed.getAuthorId()获取发布者信息
			UserBasePO user = userBaseDAO.selectByPrimaryKey(needFeed.getAuthorId());
			if (user != null) {
				FeedKolVO feedkol = cconvertKol(user);
				feedsVO.setKol(feedkol);
			}
			int needCount = needGoodsDAO.getNeedGoodsCount(needFeed.getGoodsId());
			int commentCount = needFeedCommentDAO.getcountByfeedId(needFeed.getFeedId());
			feedsVO.setCommentCount(commentCount);
			FeedNeedVO feedNeedVO = new FeedNeedVO();
			feedNeedVO.setNeedCount(needCount);
			feedsVO.setNeed(feedNeedVO);
			resultList.add(feedsVO);
		}
		return resultList;

	}
	/**
	 * 
	 * @author LXD 2015年9月16日 下午3:08:10
	 * @Method: cconvertKol 
	 * @Description: TODO UserBasePO 转 FeedKolVO
	 * @param user
	 * @return 
	 * @throws
	 */
	private FeedKolVO cconvertKol(UserBasePO user) {
		FeedKolVO feedKol=new FeedKolVO();
		feedKol.setKolId(user.getUserId());
		feedKol.setNickName(user.getNickName());
		feedKol.setProfilePicKey(user.getProfilePicKey());
		return feedKol;
	}
   /***
    * 
    * @author LXD 2015年9月16日 下午3:08:26
    * @Method: convertGoods 
    * @Description: TODO GoodsMainPO 转 FeedGoodsVO
    * @param goods
    * @return 
    * @throws
    */
	private FeedGoodsVO convertGoods(GoodsMainPO goods) {
		FeedGoodsVO feedGoods =new FeedGoodsVO();
		feedGoods.setGoodsId(goods.getGoodsId());
		feedGoods.setGoodsName(goods.getGoodsName());
		feedGoods.setTopPicKey(goods.getScenePicKey());
		feedGoods.setIsGlobal(goods.getIsGlobal());
		return feedGoods;
	}
	
}
