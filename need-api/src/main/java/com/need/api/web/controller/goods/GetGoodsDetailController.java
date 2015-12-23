package com.need.api.web.controller.goods;

import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.service.goods.GoodsDetailService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.goods.GoodsDetailVO;
import com.need.common.domain.vo.goods.GoodsResultVO;
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
 * @author xiehao 2015年8月19日 下午3:34:24
 * @ClassName GetGoodsDetailController
 * @Description TODO 获得商品详情页的信息
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: xiehao 2015年8月19日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.GOODS_DETAIL)
public class GetGoodsDetailController {
	
	private static final Logger logger = Logger.getLogger(GetGoodsDetailController.class);
	
	@Autowired
	private GoodsDetailService goodsDetailService;
	
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	
	@ParamsValidate(value={
			@ParamValidate(name="goodsId",notNull=true,code=BizReturnCode.GOODS_NOT_EXIST,regex="^\\w\\S*$")})
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message goodsDetailById(
			@RequestParam(required = true) String goodsId){//商品ID是必须的
		logger.info("goodsDetailById in..goodsId :"+goodsId);
		GoodsResultVO GoodsResultVO = goodsMainDAO.getGoodsById(goodsId);
		if(GoodsResultVO == null){
			return Message.error(BizReturnCode.NOT_FIND_GOODS);
		}
		
		Message message = Message.success();
		GoodsDetailVO goodsDetailVO = goodsDetailService.getGoodsDetailById(goodsId);
		message.addData("goodsDesc", goodsDetailVO.getGoodsDesc());
		message.addData("detailPicKeys", goodsDetailVO.getPicList());
		
		logger.info("goodsDetailById out..goodsId :"+goodsId);
		
		return message;
	}

}
