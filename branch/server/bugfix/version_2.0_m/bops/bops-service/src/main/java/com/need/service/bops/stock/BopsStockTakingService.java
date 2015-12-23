package com.need.service.bops.stock;

import org.springframework.stereotype.Service;

import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;

/**
 * 
 * @author liuhongyang 2015年11月30日 下午2:22:19
 * @ClassName BopsStockTakingService
 * @Description 盘点wms到erp
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: liuhongyang 2015年11月30日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public interface BopsStockTakingService {
	public void getStockTakingToSave(ESynEdiReceiveMessage eSynEdiReceiveMessage);
}
