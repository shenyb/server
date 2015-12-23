package com.need.api.web.controller.ops;


import com.need.api.constant.ControllerMappings;
import com.need.common.core.service.ops.OpsPositionService;
import com.need.common.domain.pub.Message;
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
 * @Description TODO  获取人气
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月8日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.SELECTED_GOODS)
public class GetSelectedGoodsController {
	private static final Logger logger = Logger.getLogger(GetSelectedGoodsController.class);

	@Autowired
	private OpsPositionService opsPositionService;
	/**
	 * @return 
	 * 
	 * @author LXD 2015年8月9日 上午10:29:56
	 * @Method: GetHotGoods 
	 * @Description: TODO 获取精选商品
 	 * @param userId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message GetSelectedGoodsGetHotGoods(@RequestParam(required = false)String userId,
			@RequestParam(required = true)Integer pageNum,@RequestParam(required = true) Integer pageSize){
		 logger.info("GetSelectedGoods.....");
		 Message success= opsPositionService.querySelectedGoods(userId,pageNum,pageSize);
		 return success;
		 
	}
	
}
