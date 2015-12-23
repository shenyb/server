package com.need.wap.web.controller.trade.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.pub.Message;
import com.need.wap.constant.Constants;
import com.need.wap.constant.ControllerMappings;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年10月24日 下午1:49:50
 * @ClassName GetTransportExpenseLimitController
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年10月24日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.GET_TRANSPORT_EXPENSE_LIMIT)
public class GetTransportExpenseLimitController {

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message getTransportExpenseLimit(@RequestParam(required = true) String warehouseType) {
		Message message = Message.success();
		if(WarehouseTypeEnum.OVERSEA_WAREHOUSE.code.equals(warehouseType)){
			message.addData("amount", Constants.TRANSPORT_EXPENSE_OF_OVERSEA_WAREHOUSE_LIMIT);
		}else if(WarehouseTypeEnum.SELF_WAREHOUSE.code.equals(warehouseType)){
			message.addData("amount", Constants.TRANSPORT_EXPENSE_OF_SELF_WAREHOUSE_LIMIT);
		}else if(WarehouseTypeEnum.TAX_WAREHOUSE.code.equals(warehouseType)){
			message.addData("amount", Constants.TRANSPORT_EXPENSE_OF_TAX_WAREHOUSE_LIMIT);
		}
		return message;
	}

}
