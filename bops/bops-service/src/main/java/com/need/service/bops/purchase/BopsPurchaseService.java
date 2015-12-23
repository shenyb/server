package com.need.service.bops.purchase;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.need.domain.po.bops.purchase.BopsPurchaseInfoPO;
import com.need.domain.po.bops.purchase.BopsPurchasePO;
import com.need.domain.po.bops.trade.BopsTradeLogistics;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;
import com.need.domain.pub.Message;
import com.need.domain.vo.purchase.BopsPurchaseVO;
import com.need.domain.vo.trade.BatchSendTradeParamVO;
import com.need.domain.vo.trade.CallcenterPageVO;
import com.need.domain.vo.trade.OrderExportParamVO;
import com.need.domain.vo.trade.TradeBaseParam;
import com.need.domain.vo.trade.TradeBaseVO;
import com.need.domain.vo.trade.TradeOrderParamsVO;
import com.need.service.common.exception.ServiceException;

/**
 * 
 * <p></p>
 * @author liuhongyang 2015年11月19日 下午4:09:01
 * @ClassName BopsPurchaseService
 * @Description 采购单管理
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: liuhongyang 2015年11月19日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public interface BopsPurchaseService {
	XSSFWorkbook exportPurchaseTemplate();
	
	List<BopsPurchaseVO> excelToList(InputStream in, Boolean is07Or10);
	
	boolean hasNullPurchaseList(BopsPurchaseVO bopsPurchaseVO);
	
	boolean isExistGoods(BopsPurchaseVO bopsPurchaseVO);
	
	BopsPurchaseVO getSuccessData(BopsPurchaseVO bopsPurchaseVO);
	
	void savePurchase(BopsPurchaseVO bopsPurchaseVO);
	
	List<BopsPurchaseVO> selectPurchaseList(BopsPurchaseVO bopsPurchaseVO);
	
	BopsPurchaseVO getPurchaseDetail(String id);
	
	List<BopsPurchaseVO> getGoodsDetail(Integer purchaseId);
	
	BopsPurchaseVO updatePurchase(BopsPurchaseVO bopsPurchaseVO);
	
	void getMessageToSave(ESynEdiReceiveMessage eSynEdiReceiveMessage);
}
