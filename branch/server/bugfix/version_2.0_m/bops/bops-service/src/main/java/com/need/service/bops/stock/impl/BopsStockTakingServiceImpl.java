package com.need.service.bops.stock.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.stock.BopsStockTakingDAO;
import com.need.dao.bops.stock.BopsStockTakingItemDAO;
import com.need.dao.bops.wms.BopsReceiveSuccessDAO;
import com.need.dao.bops.wms.ESynEdiMessageDAO;
import com.need.dao.bops.wms.ESynEdiReceiveMessageDAO;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.stock.BopsStockTakingItemPO;
import com.need.domain.po.bops.stock.BopsStockTakingPO;
import com.need.domain.po.bops.wms.BopsReceiveSuccess;
import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;
import com.need.domain.vo.wms.InvCheckInventoryRecordVO;
import com.need.domain.vo.wms.InvCheckUploadVO;
import com.need.domain.vo.wms.StoreChangeVO;
import com.need.service.bops.goods.BopsGoodsStoreService;
import com.need.service.bops.stock.BopsStockTakingService;
import com.need.service.constant.Constants;

/**
 * 
 * @author liuhongyang 2015年11月30日 下午2:29:47
 * @ClassName BopsStockTakingServiceImpl
 * @Description 接收wms返回的盘点信息,保存到数据库,并更新库存
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: liuhongyang 2015年11月30日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class BopsStockTakingServiceImpl implements BopsStockTakingService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private BopsStockTakingDAO bopsStockTakingDAO;
	@Autowired
	private BopsStockTakingItemDAO bopsStockTakingItemDAO;
	@Autowired
	private BopsGoodsDAO BopsGoodsDAO;
	@Autowired
	BopsGoodsStoreService bopsGoodsStoreService;
	@Autowired
	ESynEdiMessageDAO eSynEdiMessageDAO;
	@Autowired
	private BopsReceiveSuccessDAO bopsReceiveSuccessDAO;
	@Autowired 
	ESynEdiReceiveMessageDAO eSynEdiReceiveMessageDAO;
	@Override
	@Transactional
	public void getStockTakingToSave(ESynEdiReceiveMessage eSynEdiReceiveMessage) {
		logger.info("getStockTakingToSave.........eSynEdiReceiveMessage"+eSynEdiReceiveMessage);
		BopsReceiveSuccess bopsReceiveSuccess=bopsReceiveSuccessDAO.selectByMsgId(eSynEdiReceiveMessage.getId().toString());
		try {
			if(null == bopsReceiveSuccess){
				BopsReceiveSuccess bopsReceive=new BopsReceiveSuccess();
				bopsReceive.setMsgId(eSynEdiReceiveMessage.getId().toString());
				bopsReceiveSuccessDAO.insert(bopsReceive);
				String body=eSynEdiReceiveMessage.getBody();
				InvCheckUploadVO invCheckUploadVO=(InvCheckUploadVO)JSONObject.parseObject(body, InvCheckUploadVO.class);
				BopsStockTakingPO bopsStockTakingPO=new BopsStockTakingPO();
				bopsStockTakingPO.setStockTakingNo(invCheckUploadVO.getUploadNo().toString());
				//状态默认为审核通过
				bopsStockTakingPO.setStockStatus("SUCCESS");
				bopsStockTakingPO.setCreateAt(invCheckUploadVO.getCreateAt());
				bopsStockTakingPO.setCreateBy(invCheckUploadVO.getCreateBy().toString());
				bopsStockTakingPO.setRemark(invCheckUploadVO.getComment());
				List<InvCheckInventoryRecordVO> itemList = new ArrayList<InvCheckInventoryRecordVO>();
				itemList=invCheckUploadVO.getInvCheckInventoryRecordVO();
				if(itemList.size()>0){
					bopsStockTakingPO.setWarehouseid(Integer.parseInt(itemList.get(0).getWarehouseId().toString()));
					//插入盘点主表
					bopsStockTakingDAO.insert(bopsStockTakingPO);
					for (InvCheckInventoryRecordVO invRecord : itemList) {
						BopsStockTakingItemPO bopsStockTakingItemPO=new BopsStockTakingItemPO();
						bopsStockTakingItemPO.setStockTakingId(bopsStockTakingPO.getId());
						bopsStockTakingItemPO.setGoodsCode(invRecord.getSkuId().toString());
						//根据商品code查询商品信息
						BopsGoods bopsGoods=BopsGoodsDAO.selectByGoodsCode(invRecord.getSkuId().toString());
						bopsStockTakingItemPO.setGoodsName(bopsGoods.getGoodsName());
						bopsStockTakingItemPO.setInventoryState(Integer.parseInt(invRecord.getInventoryState().toString()));
						bopsStockTakingItemPO.setQty(Integer.parseInt(invRecord.getQty().toString()));
						bopsStockTakingItemPO.setPlanQty(Integer.parseInt(invRecord.getPlanQty().toString()));
						bopsStockTakingItemPO.setRealQty(Integer.parseInt(invRecord.getRealQty().toString()));
						bopsStockTakingItemPO.setReason(invRecord.getReason());
						//插入盘点附表
						bopsStockTakingItemDAO.insert(bopsStockTakingItemPO);
						//修改库存
						StoreChangeVO storeChangeVO=new StoreChangeVO();
						storeChangeVO.setGoodsId(bopsGoods.getGoodsId());
						if(1== bopsStockTakingItemPO.getInventoryState()){
							//正品
							storeChangeVO.setNowStoreCount(bopsStockTakingItemPO.getQty());
						}else if(2 == bopsStockTakingItemPO.getInventoryState()){ 
							//残品
							storeChangeVO.setDefectiveStore(bopsStockTakingItemPO.getQty());
						}
						storeChangeVO.setStoreType("STOCKTAKING");
						storeChangeVO.setStoreNo(bopsStockTakingPO.getStockTakingNo());
						storeChangeVO.setWarehouseId(bopsStockTakingPO.getWarehouseid());
						storeChangeVO.setAuthorId(Constants.WMS_USER_ID);
						bopsGoodsStoreService.saveStoreChange(storeChangeVO);
					}
					
				}
				eSynEdiReceiveMessage.setStatus(2L);
				eSynEdiReceiveMessage.setHandleDate(new Date());
				eSynEdiReceiveMessageDAO.updateByPrimaryKey(eSynEdiReceiveMessage);
			}
		} catch (NumberFormatException e) {
			eSynEdiReceiveMessage.setStatus(9L);
			eSynEdiReceiveMessage.setHandleDate(new Date());
			eSynEdiReceiveMessageDAO.updateByPrimaryKey(eSynEdiReceiveMessage);
		}
		
	}
	

}
