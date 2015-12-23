package com.need.common.core.service.need.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.need.NeedFeedCommentDAO;
import com.need.common.core.dao.jdbc.need.NeedFeedDAO;
import com.need.common.core.dao.jdbc.need.NeedGoodsDAO;
import com.need.common.core.dao.jdbc.need.NeedKolFansDAO;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.service.follow.FollowCacheService;
import com.need.common.core.service.follow.FollowService;
import com.need.common.core.service.need.NeedService;
import com.need.common.domain.enums.FocusStatusEnum;
import com.need.common.domain.enums.UserNeedStatus;
import com.need.common.domain.enums.needFeedStatusEnum;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.need.NeedFeedCommentPO;
import com.need.common.domain.po.api.need.NeedFeedPO;
import com.need.common.domain.po.api.need.NeedGoodsPO;
import com.need.common.domain.po.api.need.NeedKolFansPO;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.need.FeedCommentVO;
import com.need.common.domain.vo.user.goodsMainVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NeedServiceImpl implements NeedService {

	@Autowired
	private NeedGoodsDAO needGoodsDAO;

	@Autowired
	private NeedKolFansDAO needKolFansDAO;
	@Autowired
	private NeedFeedCommentDAO needFeedCommentDAO;

	@Autowired
	private NeedFeedDAO needFeedDAO;
	@Autowired
	private UserBaseDAO userBaseDAO;
	@Autowired
	FollowCacheService followCacheService;
	@Autowired
	FollowService followService;
	
    /**
     * 
     * @author LXD 2015年8月19日 下午4:02:29
     * @Method: addNeedGoods 
     * @Description: TODO 添加need
     * @param needGoodsPO
     * @return 
     * @see com.need.api.service.need.NeedService#addNeedGoods(com.need.api.dao.jdbc.need.po.NeedGoodsPO)
     */
	@Override
	@Transactional
	public int addNeedGoods(NeedGoodsPO needGoodsPO) {
		needGoodsDAO.insert(needGoodsPO);
		return needGoodsDAO.getNeedGoodsCount(needGoodsPO.getGoodsId());
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午4:03:03
     * @Method: getNeedGoodsPO 
     * @Description: TODO 校验need
     * @param userId
     * @param goodsId
     * @param needStatus
     * @return 
     * @see com.need.api.service.need.NeedService#getNeedGoodsPO(java.lang.String, java.lang.String, java.lang.String)
     */
	@Override
	public NeedGoodsPO getNeedGoodsPO(String userId, String goodsId, String needStatus) {
		return needGoodsDAO.queryByParams(userId, goodsId, needStatus);
	}
   /**
    * 
    * @author LXD 2015年8月19日 下午4:03:24
    * @Method: updateNeedGoods 
    * @Description: TODO
    * @param needGoodsPO
    * @return 
    * @see com.need.api.service.need.NeedService#updateNeedGoods(com.need.api.dao.jdbc.need.po.NeedGoodsPO)
    */
	@Override
	@Transactional
	public int updateNeedGoods(NeedGoodsPO needGoodsPO) {
		needGoodsDAO.update(needGoodsPO);
		return needGoodsDAO.getNeedGoodsCount(needGoodsPO.getGoodsId());
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午4:01:41
     * @Method: getNeedKolFansPO 
     * @Description: TODO 
     * @param userId
     * @param kolId
     * @return 
     * @see com.need.api.service.need.NeedService#getNeedKolFansPO(java.lang.String, java.lang.String)
     */
//	@Override
//	public NeedKolFansPO getNeedKolFansPO(String userId, String kolId) { 
//		NeedKolFansPO needKolFansPO =needKolFansDAO.getByKolId(userId,kolId);
//		return needKolFansPO;
//		
//	}
    /**
     * 
     * @author LXD 2015年8月19日 下午4:01:05
     * @Method: addFocusKol  
     * @Description: TODO 添加关注
     * @param needKolFansPO 
     * @see com.need.api.service.need.NeedService#addFocusKol(com.need.api.dao.jdbc.need.po.NeedKolFansPO)
     */
	@Override
	@Transactional
	public void addFocusKol(NeedKolFansPO needKolFansPO) {
		needKolFansDAO.insert(needKolFansPO);

	}
    /**
     * 
     * @author LXD 2015年8月19日 下午4:00:44
     * @Method: getConcernCount 
     * @Description: TODO 获取关注数
     * @param userId
     * @return 
     * @see com.need.api.service.need.NeedService#getConcernCount(java.lang.String)
     */
	@Override
	public int getConcernCount(String kolId) {
		return needKolFansDAO.countKolFans(kolId);
	}
     /**
      * 
      * @author LXD 2015年8月19日 下午4:00:24
      * @Method: deleteFocusKol 
      * @Description: TODO 删除关注
      * @param needKolFansPO
      * @return 
      * @see com.need.api.service.need.NeedService#cancelFocusKol(com.need.api.dao.jdbc.need.po.NeedKolFansPO)
      */
	@Override
	@Transactional
	public int cancelFocusKol(NeedKolFansPO needKolFansPO) {
		needKolFansPO.setFocusStatus(FocusStatusEnum.CANCEL.code);
		needKolFansDAO.update(needKolFansPO);
		return needKolFansDAO.getConcernCount(needKolFansPO.getUserId());
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午3:49:43
     * @Method: addfeedComment 
     * @Description: TODO 添加feed评论
     * @param needFeedComment 
     * @see com.need.api.service.need.NeedService#addfeedComment(com.need.api.dao.jdbc.need.po.NeedFeedCommentPO)
     */
	@Override
	@Transactional
	public void addfeedComment(NeedFeedCommentPO needFeedComment) {
		needFeedCommentDAO.insert(needFeedComment);

	}
    /**
     * 
     * @author LXD 2015年8月19日 下午3:53:40
     * @Method: kolPublishFeed 
     * @Description: TODO 行家发布feed
     * @param needFeedPO 
     * @see com.need.api.service.need.NeedService#kolPublishFeed(com.need.api.dao.jdbc.need.po.NeedFeedPO)
     */
	@Override
	@Transactional
	public void kolPublishFeed(NeedFeedPO needFeedPO) {
		needFeedDAO.insert(needFeedPO);
		//同时在needGOODS表中插入一条数据
		NeedGoodsPO	needGoodsPO= needGoodsDAO.getIsNeed(needFeedPO.getAuthorId(), needFeedPO.getGoodsId());
		if(needGoodsPO==null){
			needGoodsPO =new NeedGoodsPO();
			needGoodsPO.setUserId(needFeedPO.getAuthorId());
			needGoodsPO.setGoodsId(needFeedPO.getGoodsId());
			needGoodsPO.setNeedStatus(UserNeedStatus.NEED.code);
			needGoodsDAO.insert(needGoodsPO);	
		}if(needGoodsPO!=null&&UserNeedStatus.CANCEL.code.equals(needGoodsPO.getNeedStatus())){
			needGoodsPO.setNeedStatus(UserNeedStatus.NEED.code);
			needGoodsDAO.update(needGoodsPO);
			
		}
		
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午4:00:04
     * @Method: changeNeedStatus 
     * @Description: TODO 修改Need的状态
     * @param needGoodsPO 
     * @see com.need.api.service.need.NeedService#changeNeedStatus(com.need.api.dao.jdbc.need.po.NeedGoodsPO)
     */
	@Override
	@Transactional
	public void changeNeedStatus(NeedGoodsPO needGoodsPO) {
		needGoodsDAO.update(needGoodsPO);

	}

	/**
	 * 
	 * @author LXD 2015年8月20日 下午10:44:00
	 * @Method: getByParams 
	 * @Description: TODO 根据用户ID和行家ID查处关系对象
	 * @param userId
	 * @param kolId
	 * @return 
	 * @see com.need.api.service.need.NeedService#getByParams(java.lang.String, java.lang.String)
	 */
	@Override
	public NeedKolFansPO getByParams(String userId, String kolId,String status) {
		return needKolFansDAO.getByParams(userId,kolId,status);
	}
	@Override
	public void updatNeedKolFansPO(NeedKolFansPO needKolFansPO) {
		needKolFansDAO.update(needKolFansPO);
		
	}

	
	@Override
	public Message getNeedGoodsList(String userId, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub


		PageHelper.startPage(pageNum, pageSize);
		Page<GoodsMainPO> page = (Page<GoodsMainPO>) needGoodsDAO.queryNeedList(userId);
		List<GoodsMainPO> goodsMainPO = page.getResult();
		Message success = Message.success();
		List<goodsMainVO> list = new ArrayList<goodsMainVO>();
		for (int i = 0; i < goodsMainPO.size(); i++) {
			if (goodsMainPO.get(i) != null) {
				goodsMainVO goods = new goodsMainVO();
				BeanUtils.copyProperties(goodsMainPO.get(i), goods);
				goods.setNeedPicKey(goodsMainPO.get(i).getTopPicKeys().split(",")[0]);
				list.add(goods);
			}
		}
		success.addData("goodsList", list);
        success.addData("total", page.getTotal());
		return success;
	}
	/***
	 * 
	 * @author LXD 2015年8月25日 下午5:06:26
	 * @Method: addfeedComment 
	 * @Description: TODO    feed添加评论
	 * @param feedId
	 * @param userId
	 * @param content
	 * @return 
	 * @see com.need.api.service.need.NeedService#addfeedComment(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Message addfeedComment(String feedId, String userId, String content) {
		Message message = Message.success();
		NeedFeedCommentPO needFeedComment = new NeedFeedCommentPO();
		UserBasePO user = userBaseDAO.getUserInfo(userId);
		//生成Id
		String commentId = BizCodeUtil.generateCommentId(feedId, user.getMobile());
		needFeedComment.setCommentId(commentId);
		needFeedComment.setAuthorId(userId);
		needFeedComment.setFeedId(feedId);
		needFeedComment.setComment(content);
		needFeedComment.setFeedStatus(needFeedStatusEnum.VALID.code);
		needFeedCommentDAO.insert(needFeedComment);
		message.addData("commentId", commentId);
		return message;
	}
	/**
	 * 
	 * @author LXD 2015年8月25日 下午5:14:35
	 * @Method: cancelFocusKol 
	 * @Description: TODO 取消关注
	 * @param userId
	 * @param kolId
	 * @return 
	 * @see com.need.api.service.need.NeedService#cancelFocusKol(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Message cancelFocusKol(String userId, String kolId) {
		Message message = Message.success();
		// 检验是否关注过
		NeedKolFansPO needKolFansPO =needKolFansDAO.getByParams(userId, kolId,FocusStatusEnum.FOCUS.code);
		if (needKolFansPO == null) {
			return Message.error(BizReturnCode.NEED_USER_NO_FOCUSE_KOL);	
		}
		needKolFansPO.setFocusStatus(FocusStatusEnum.CANCEL.code);
		needKolFansDAO.update(needKolFansPO);
		int concernCount= needKolFansDAO.countKolFans(kolId);
		message.addData("concernCount", concernCount);
		return message;
	}
	@Override
	public Message queryFeedComments(String feedId,int pageNum,int pageSize) {
		Message message = Message.success();
		PageHelper.startPage(pageNum, pageSize);
		Page<NeedFeedCommentPO> page = (Page<NeedFeedCommentPO>) needFeedCommentDAO.getcommentsByfeedId(feedId);
		List<FeedCommentVO> list = new ArrayList<FeedCommentVO>();
		for (NeedFeedCommentPO needFeedCommentPO : page.getResult()) {
			FeedCommentVO commentVO = new FeedCommentVO();

			commentVO.setCommentTime(needFeedCommentPO.getCreateTime().getTime());
			commentVO.setComment(needFeedCommentPO.getComment());
			UserBasePO user = userBaseDAO.getUserInfo(needFeedCommentPO.getAuthorId());
			if (user != null) {
				commentVO.setUserName(user.getNickName());
				commentVO.setUserProfilePicKey(user.getProfilePicKey());
			}
			list.add(commentVO);

		}
		message.addData("commentList", list);
		message.addData("totalCount", page.getTotal());
		return message;
	}
	@Override
	public boolean getNeedKolFansPO(String userId, String kolId, String status) {
		
		NeedKolFansPO needKolFansPO= needKolFansDAO.getByParams(userId, kolId, status);
		if(needKolFansPO!=null){
			
			return true;
		}else{
			
			return false;
		}
	}
	@Override
	public NeedKolFansPO getNeedKolFans(String userId, String kolId) {
		NeedKolFansPO needKolFansPO =needKolFansDAO.getByKolId(userId,kolId);
		return needKolFansPO;
	}
	@Override
	public void saveNeedKolFans(String userId, String kolId) {
		// TODO Auto-generated method stub
          needKolFansDAO.updateByParam(userId,kolId,FocusStatusEnum.FOCUS.code);
	}
	/**
	 * 
	 * @author peiboli 2015年9月20日 下午12:30:35
	 * @Method: getNeedGoodsList 
	 * @Description: TODO 1.0版本的获得need的商品列表,保税仓不显示
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @param isGolbal
	 * @return 
	 * @see com.need.api.service.need.NeedService#getNeedGoodsList(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public Message getNeedGoodsList(String userId, Integer pageNum, Integer pageSize, Boolean isGolbal) {

		PageHelper.startPage(pageNum, pageSize);
		Page<GoodsMainPO> page = (Page<GoodsMainPO>) needGoodsDAO.selectNeedGoods(userId,isGolbal.toString());
		List<GoodsMainPO> goodsMainPO = page.getResult();
		Message success = Message.success();
		List<goodsMainVO> list = new ArrayList<goodsMainVO>();
		for (int i = 0; i < goodsMainPO.size(); i++) {
			if (goodsMainPO.get(i) != null) {
				goodsMainVO goods = new goodsMainVO();
				BeanUtils.copyProperties(goodsMainPO.get(i), goods);
				goods.setNeedPicKey(goodsMainPO.get(i).getTopPicKeys().split(",")[0]);
				list.add(goods);
			}
		}
		success.addData("goodsList", list);
        success.addData("total", page.getTotal());
		return success;
	}
	@Override
	public Message addFocus(String userId, String[] kolId) {
		for(String kolid :kolId){
			if(!followCacheService.isFollow(userId,kolid)){
				followService.addFollow(userId.trim(), kolid.trim());
			}
		}
		return Message.success();
	}

}
