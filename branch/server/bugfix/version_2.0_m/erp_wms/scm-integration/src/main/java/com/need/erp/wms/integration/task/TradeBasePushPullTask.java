package com.need.erp.wms.integration.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.need.dao.bops.wms.ESynEdiReceiveMessageDAO;
import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;
import com.need.service.bops.purchase.BopsPurchaseService;
import com.need.service.bops.stock.BopsStockTakingService;
import com.need.service.bops.trade.BopsTradePushPullService;
import com.need.service.bops.wms.WmsToErpService;

/**
 * 
 * <p>
 * </p>
 * 
 * @author shenyb 2015年11月12日 下午3:16:15
 * @ClassName TradeBasePushPullTask
 * @Description
 * @version V1.3
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: shenyb 2015年11月12日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
public class TradeBasePushPullTask {
	private static Logger logger = Logger.getLogger(TradeBasePushPullTask.class);
	@Autowired
	private BopsTradePushPullService bopsTradePushPullService;
	@Autowired
	ESynEdiReceiveMessageDAO eSynEdiReceiveMessageDAO;
	@Autowired
	BopsPurchaseService bopsPurchaseService;
	@Autowired
	WmsToErpService wmsToErpService;
	@Autowired
	BopsStockTakingService bopsStockTakingService;
	/**
	 * 定时把海关通过的订单推送到wms
	 * @author zhangmengbin
	 */
	public void sendOrderMessage(){
		try {
			logger.info("erp-wms-integration系统------定时把海关通过的订单推送到wms----正在执行");
			bopsTradePushPullService.createDeliverTradeTest();
		} catch (Exception e) {
			logger.error("erp-wms-integration系统------定时把海关通过的订单推送-----出现异常"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	/**
	 * 处理从kafka接受的信息
	 * @author zhangmengbin
	 */
	public void doReceiveMessage(){
		try {
			logger.info("erp-wms-integration系统------定时处理从kafka接受的信息----正在执行");
			List<ESynEdiReceiveMessage> list = eSynEdiReceiveMessageDAO.queryListByStatus(1L);
			ESynEdiReceiveMessage eSynEdiReceiveMessage = null;
			if(list!=null && list.size()>0){
				for (int j = 0; j < list.size(); j++) {
					eSynEdiReceiveMessage = list.get(j);
					if("101".equals(eSynEdiReceiveMessage.getType())){
						bopsPurchaseService.saveMessageToSave(eSynEdiReceiveMessage);
					}else if("201".equals(eSynEdiReceiveMessage.getType())){
						 wmsToErpService.createWmsOrderToErp(eSynEdiReceiveMessage);
					}else if("100".equals(eSynEdiReceiveMessage.getType())){
						//拒收订单
						wmsToErpService.createRejectionOrder(eSynEdiReceiveMessage);
					}else if("212".equals(eSynEdiReceiveMessage.getType())){
						bopsStockTakingService.saveStockTaking(eSynEdiReceiveMessage);
					}
				}
			}
		}catch (Exception e) {
				logger.error("erp-wms-integration系统------定时处理从kafka接受的信息---出现异常"+e.getMessage());
				e.printStackTrace();
			}
		}
}
