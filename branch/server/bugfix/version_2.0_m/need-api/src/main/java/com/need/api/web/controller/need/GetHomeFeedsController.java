package com.need.api.web.controller.need;

import com.need.api.constant.ControllerMappings;
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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * <p></p>
 * @author LXD 2015年8月12日 下午5:01:20
 * @ClassName GetHomeFeeds
 * @Description TODO 首页feed流
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月12日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.HOME_FEED_LIST)
public class GetHomeFeedsController {
	private static final Logger logger = Logger.getLogger(GetHomeFeedsController.class);
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
	@Autowired
	private NeedFeedService needFeedService;
	/**
	 * 
	 * @author LXD 2015年8月18日 下午6:18:28
	 * @Method: getHomeFeeds 
	 * @Description: TODO 首页feed流信息
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getHomeFeeds(@RequestParam(required = true)String userId,@RequestParam(required = false)Integer pageNum,@RequestParam(required = false) Integer pageSize){
		logger.info(String.format("userId&&&pageNum&&&&pageSize: %s", userId+"&&&"+pageNum+"&&&&"+pageSize));
		Message message =Message.success();	
	    FeedsParamsVO params=new FeedsParamsVO();
	    if(pageNum == null){
	    	pageNum = 1;
	    }
	    if(pageSize == null){
	    	pageSize = 10;
	    }
	    params.setCurrentPage(pageNum);
	    params.setPageSize(pageSize);
	    params.setUserId(userId);
	    int totalCount= needFeedDAO.getCountParam(userId);
	    params.setTotalCount(totalCount);
	    List<NeedFeedPO>  feedLsit=	needFeedDAO.queryBypageParams(params);
	    //如果用户关注的行家有feed，则显示  
	    if(feedLsit!=null&&feedLsit.size()>0){
	     List<FeedsVO> resultList= this.convertResult(feedLsit, userId);
			message.addData("feedList", resultList); 
			message.addData("totalCount", totalCount);
			 return message;
	    }else{
		  	  //如果用户没有关注行家或是所关注的行家发表的新鲜事为0，则查所有feed流按时间倒序
	    	FeedsParamsVO param=new FeedsParamsVO();
		    param.setCurrentPage(pageNum);
		    param.setPageSize(pageSize);
		    param.setUserId(userId);
	    	
	    	 int count=  needFeedDAO.getCount();
	    	 param.setTotalCount(count);
	    	List<NeedFeedPO> allFeeds= needFeedDAO.queryByPage(param);
		    	    if(allFeeds!=null&&allFeeds.size()>0){
		    	     List<FeedsVO> resultList= this.convertResult(allFeeds, userId);
		    	     message.addData("feedList", resultList); 
		 			 message.addData("totalCount", count);    
		    	    }
		   return message;		         
		}
		
	   
	}
	

	
	private FeedKolVO cconvertKol(UserBasePO user) {
		FeedKolVO feedKol=new FeedKolVO();
		feedKol.setKolId(user.getUserId());
		feedKol.setNickName(user.getNickName());
		feedKol.setProfilePicKey(user.getProfilePicKey());
		return feedKol;
	}

	private FeedGoodsVO convertGoods(GoodsMainPO goods) {
		FeedGoodsVO feedGoods =new FeedGoodsVO();
		feedGoods.setGoodsId(goods.getGoodsId());
		feedGoods.setGoodsName(goods.getGoodsName());
		feedGoods.setTopPicKey(goods.getScenePicKey());
		return feedGoods;
	}
	
	
   private 	List<FeedsVO> convertResult(List<NeedFeedPO> feedLsit,String userId){
	   List<FeedsVO> resultList=new ArrayList<FeedsVO>();
	   for(NeedFeedPO needFeed:feedLsit){
			FeedsVO feedsVO =new FeedsVO();
			feedsVO.setFeedId(needFeed.getFeedId());
			feedsVO.setContent(needFeed.getMemo());
			NeedGoodsPO needGoodsPO= needGoodsDAO.queryByParams(userId,needFeed.getGoodsId(),UserNeedStatus.NEED.code);
			if(needGoodsPO!=null){
				feedsVO.setIsNeed(NeedStatusEnum.NEED.code);
			}else{
				feedsVO.setIsNeed(NeedStatusEnum.CANCEL.code);
			}
			//根据needFeed.getGoodsId()获取商品信息
			GoodsMainPO goods=  goodsMainDAO.selectByPrimaryKey(needFeed.getGoodsId());
			 if(goods!=null){
			 FeedGoodsVO feedGoods=convertGoods(goods);
			 feedsVO.setGoods(feedGoods);
			 }
			//根据needFeed.getAuthorId()获取发布者信息
			UserBasePO user= userBaseDAO.selectByPrimaryKey(needFeed.getAuthorId());
			if(user!=null){
			FeedKolVO feedkol=cconvertKol(user);
			feedsVO.setKol(feedkol);
			}
			int needCount= needGoodsDAO.getNeedGoodsCount(needFeed.getGoodsId());
			int commentCount=needFeedCommentDAO.getcountByfeedId(needFeed.getFeedId());
			feedsVO.setCommentCount(commentCount);
			FeedNeedVO feedNeedVO =new FeedNeedVO();
			feedNeedVO.setNeedCount(needCount);
			feedsVO.setNeed(feedNeedVO);
			resultList.add(feedsVO);
		}
	   return resultList;
	   
	   
   }
   /**
    * 
    * @author LXD 2015年9月9日 下午12:29:40
    * @Method: getHomeFeeds_V1 
    * @Description: 1、NEED1.1 首页Feed流增加功能：商品添加字段：商品名称，isGlobal;2、增加游客登陆功能
    * @param userId
    * @param pageNum
    * @param pageSize
    * @return 
    * @throws
    */
    @ResponseBody
	@RequestMapping(params = "apiVersion=1.1", method = RequestMethod.GET)
	public Message getHomeFeeds_v1_1(@RequestParam(required = false)String userId,@RequestParam(required = false)Integer pageNum,@RequestParam(required = false) Integer pageSize){
		logger.info(String.format("userId&&&pageNum&&&&pageSize: %s", userId+"&&&"+pageNum+"&&&&"+pageSize));
		return needFeedService.getHomeFeeds(userId,pageNum,pageSize);
        
		
	   
	}
}

