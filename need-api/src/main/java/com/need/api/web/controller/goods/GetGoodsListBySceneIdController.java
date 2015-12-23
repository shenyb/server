package com.need.api.web.controller.goods;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.goods.GoodsSceneDAO;
import com.need.common.core.service.goods.GoodsMainService;
import com.need.common.domain.po.api.goods.GoodsScenePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.goods.GoodsPageResultVO;
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
 * <p></p>
 * @author xiehao 2015年8月19日 下午3:24:33
 * @ClassName GetGoodsListBySceneIdController
 * @Description TODO 获得某个场景下的商品List
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月19日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.GOODS_GETGOODS_LIST_BYSCENEID)
public class GetGoodsListBySceneIdController {

	private static final Logger logger = Logger.getLogger(GetGoodsListBySceneIdController.class);
	
	@Autowired
	private GoodsMainService goodsMainService;
	
	@Autowired
	private GoodsSceneDAO goodsSceneDAO;
	
	@ParamsValidate(value={
			@ParamValidate(name="sceneId",notNull=true,code=BizReturnCode.SCENE_NOT_EXIST,regex="^[1-9]\\d*$")})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getGoodsListBySceneId (
			@RequestParam(required = true)Integer sceneId,
			String userId,
			@RequestParam(required = false)Integer pageNum,	//分页数据不是必须的
			@RequestParam(required = false)Integer pageSize){
		logger.info("in goods/scene/list sceneId: " + sceneId);
		
		GoodsScenePO goodsScenePO = goodsSceneDAO.selectByPrimaryKey(sceneId);
		if(goodsScenePO == null){
			return Message.error(BizReturnCode.NOT_SCENE_ID);
		}
		
		Message message = Message.success();
		if(pageNum == null)	//为分页数据添加默认值
			pageNum = 1;
		if(pageSize == null)
			pageSize = 10;
		GoodsPageResultVO goodsPageResultVO = goodsMainService.getGoodsListBySceneId(sceneId, userId, pageNum, pageSize);
		message.addData("goodsList", goodsPageResultVO.getGoodsList());
		message.addData("totalCount", goodsPageResultVO.getTotalCount());
		
		logger.info("out goods/scene/list sceneId: " + sceneId);
//		logger.info(String.format("out goods/scene/list sceneId: %s", sceneId));
		
		return message;
	}
	
}
