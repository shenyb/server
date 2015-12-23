package com.need.api.web.controller.need;


import com.need.api.constant.ControllerMappings;
import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.service.need.NeedService;
import com.need.common.domain.enums.needFeedStatusEnum;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.need.NeedFeedPO;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p></p>
 * @author LXD 2015年8月8日 下午3:09:08
 * @ClassName GetHomeOps
 * @Description  行家need商品
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.KOL_NEED_GOODS)
public class KolNeedGoodsController {
	private static final Logger logger = Logger.getLogger(KolNeedGoodsController.class);
	@Autowired
	private NeedService needService;
	
	@Autowired
	private UserBaseDAO userBaseDAO;
	
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	/**
	 * 
	 * @author LXD 2015年8月9日 上午10:29:56
	 * @Method: GetHotGoods 
	 * @Description: 行家need商品
 	 * @param userId
	 * @return 
	 * @throws
	 */
	@ParamsValidate(value={
			@ParamValidate(name="kolId",notNull=true,code=BizReturnCode.NEED_KOLID_NOT_EXIST),
			@ParamValidate(name="goodsId",notNull=true,code=BizReturnCode.NEED_GOODS_ID_NOT_EXIST),
			@ParamValidate(name="memo",notNull=true,code=BizReturnCode.NEED_FEED_MEMO_NOT_EXIST)
			
	})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.POST)
	public Message kolNeedGoods(@RequestParam(required = true) String kolId,
			@RequestParam(required = true) String goodsId, @RequestParam(required = true) String memo) {
		logger.info("kolNeedGoods.....");
		if(memo.length()>140){
			return Message.error(BizReturnCode.NEED_FEED_MEMO_LENGTH);
		}
		Message success = Message.success();
		NeedFeedPO needFeedPO = new NeedFeedPO();
		needFeedPO.setAuthorId(kolId);
		needFeedPO.setGoodsId(goodsId);
		needFeedPO.setMemo(memo);
	    needFeedPO.setFeedStatus(needFeedStatusEnum.VALID.code);
		UserBasePO user = userBaseDAO.getUserInfo(kolId);
		GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(goodsId);
		if (user != null && goods != null) {
			needFeedPO.setFeedId(BizCodeUtil.generateFeedId(user.getUserId(), goods.getGoodsName()));
			needService.kolPublishFeed(needFeedPO);
		} else {
			//该用户或商品不存在
			return  Message.error(BizReturnCode.NEED_GOODS_KOL_OR_GOODS_NOT_EXIST);
		}
		logger.info(String.format("kol need goods :%s", needFeedPO.toString()));
		return success;

	}

}
