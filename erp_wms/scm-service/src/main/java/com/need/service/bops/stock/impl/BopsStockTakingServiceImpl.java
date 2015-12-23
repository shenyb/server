package com.need.service.bops.stock.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.purchase.BopsPurchaseDAO;
import com.need.dao.bops.stock.BopsStockTakingDAO;
import com.need.dao.bops.stock.BopsStockTakingItemDAO;
import com.need.dao.bops.store.BopsCreditMemoDAO;
import com.need.dao.bops.store.BopsCreditMemoItemDAO;
import com.need.dao.bops.store.BopsInventoryDAO;
import com.need.dao.bops.wms.BopsReceiveSuccessDAO;
import com.need.dao.bops.wms.ESynEdiMessageDAO;
import com.need.dao.bops.wms.ESynEdiReceiveMessageDAO;
import com.need.domain.common.enums.AuditStatusEnums;
import com.need.domain.common.enums.ReportTypeEnum;
import com.need.domain.common.enums.StoreTypeEnum;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.purchase.BopsPurchaseInfoPO;
import com.need.domain.po.bops.purchase.BopsPurchasePO;
import com.need.domain.po.bops.stock.BopsStockTakingItemPO;
import com.need.domain.po.bops.stock.BopsStockTakingPO;
import com.need.domain.po.bops.store.BopsCreditMemoItemPO;
import com.need.domain.po.bops.store.BopsCreditMemoPO;
import com.need.domain.po.bops.store.BopsInventoryPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.po.bops.wms.BopsReceiveSuccess;
import com.need.domain.po.bops.wms.ESynEdiMessage;
import com.need.domain.po.bops.wms.ESynEdiReceiveMessage;
import com.need.domain.pub.Message;
import com.need.domain.vo.wms.InvCheckInventoryRecordVO;
import com.need.domain.vo.wms.InvCheckUploadVO;
import com.need.domain.vo.wms.OutCheckInventoryRecordVO;
import com.need.domain.vo.wms.OutCheckUploadVO;
import com.need.domain.vo.wms.StoreChangeVO;
import com.need.kafka.services.producer.NeedProducer;
import com.need.service.bops.goods.BopsGoodsStoreService;
import com.need.service.bops.stock.BopsStockTakingService;
import com.need.service.constant.BizReturnCode;
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
	private BopsGoodsDAO bopsGoodsDAO;
	@Autowired
	BopsGoodsStoreService bopsGoodsStoreService;
	@Autowired
	ESynEdiMessageDAO eSynEdiMessageDAO;
	@Autowired
	private BopsReceiveSuccessDAO bopsReceiveSuccessDAO;
	@Autowired 
	ESynEdiReceiveMessageDAO eSynEdiReceiveMessageDAO;
	@Autowired
	private BopsCreditMemoDAO bopsCreditMemoDAO;
	@Autowired
	private BopsInventoryDAO bopsInventoryDAO;
	@Autowired
	private BopsCreditMemoItemDAO bopsCreditMemoItemDAO;
	@Autowired
	private BopsPurchaseDAO bopsPurchaseDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Override
	@Transactional("bops_txManager")
	public Message saveStockTaking(ESynEdiReceiveMessage eSynEdiReceiveMessage){
		logger.info("getStockTakingToSave.........eSynEdiReceiveMessage"+eSynEdiReceiveMessage);
		Message message = Message.success();
		BopsReceiveSuccess bopsReceiveSuccess=bopsReceiveSuccessDAO.selectByMsgId(eSynEdiReceiveMessage.getId().toString());
		String body=eSynEdiReceiveMessage.getBody();
		InvCheckUploadVO invCheckUploadVO=(InvCheckUploadVO)JSONObject.parseObject(body, InvCheckUploadVO.class);
		List<InvCheckInventoryRecordVO> itemList = new ArrayList<InvCheckInventoryRecordVO>();
		itemList=invCheckUploadVO.getInvCheckInventoryRecordVO();
		try {
			if(null == bopsReceiveSuccess){
				BopsReceiveSuccess bopsReceive=new BopsReceiveSuccess();
				bopsReceive.setMsgId(eSynEdiReceiveMessage.getId().toString());
				bopsReceiveSuccessDAO.insert(bopsReceive);
				BopsStockTakingPO bopsStockTakingPO=new BopsStockTakingPO();
				bopsStockTakingPO.setStockTakingNo(invCheckUploadVO.getUploadNo().toString());
				//状态默认为审核未通过
				bopsStockTakingPO.setStockStatus(AuditStatusEnums.WAIT_AUDIT.code);
				bopsStockTakingPO.setCreateAt(invCheckUploadVO.getCreateAt());
				bopsStockTakingPO.setCreateBy(invCheckUploadVO.getCreateBy().toString());
				bopsStockTakingPO.setRemark(invCheckUploadVO.getComment());
				//查询商品库存,如果不足,状态为待审核,不进行修改库存
				if(itemList.size()>0){
					bopsStockTakingPO.setWarehouseid(Integer.parseInt(itemList.get(0).getWarehouseId().toString()));
					bopsStockTakingDAO.insert(bopsStockTakingPO);
					for (InvCheckInventoryRecordVO invRecord : itemList) {
						BopsStockTakingItemPO bopsStockTakingItemPO=new BopsStockTakingItemPO();
						bopsStockTakingItemPO.setStockTakingId(bopsStockTakingPO.getId());
						bopsStockTakingItemPO.setGoodsCode(invRecord.getSkuId().toString());
						//根据商品code查询商品信息
						BopsGoods bopsGoods=bopsGoodsDAO.selectByGoodsCode(invRecord.getSkuId().toString());
						bopsStockTakingItemPO.setGoodsName(bopsGoods.getGoodsName());
						bopsStockTakingItemPO.setInventoryState(Integer.parseInt(invRecord.getInventoryState().toString()));
						bopsStockTakingItemPO.setQty(Integer.parseInt(invRecord.getQty().toString()));
						bopsStockTakingItemPO.setPlanQty(Integer.parseInt(invRecord.getPlanQty().toString()));
						bopsStockTakingItemPO.setRealQty(Integer.parseInt(invRecord.getRealQty().toString()));
						bopsStockTakingItemPO.setReason(invRecord.getReason());
						//插入盘点附表
						bopsStockTakingItemDAO.insert(bopsStockTakingItemPO);
					}
					
				}
				eSynEdiReceiveMessage.setStatus(2L);
				eSynEdiReceiveMessage.setHandleDate(new Date());
				eSynEdiReceiveMessageDAO.updateByPrimaryKey(eSynEdiReceiveMessage);
				
				//判断库存是否够用
				for (InvCheckInventoryRecordVO invCheckInventoryRecordVO : itemList) {
					BopsGoods bopsGoods=bopsGoodsDAO.selectByGoodsCode(invCheckInventoryRecordVO.getSkuId().toString());
					GoodsMainPO storeGoods = goodsMainDAO.selectByPrimaryKey(bopsGoods.getGoodsId());
					int qty = invCheckInventoryRecordVO.getQty().intValue();
					if(qty<0){
						if(Math.abs(qty) > storeGoods.getStoreCount().intValue()){
							logger.info("........store is not engough.........");
							throw new Exception(Constants.STORE_IS_NOT_ENOUGH);
						}
					}
					bopsStockTakingPO.setStockStatus(AuditStatusEnums.SUCCESS.code);
					//修改盘点主表状态
					bopsStockTakingDAO.updateByPrimaryKey(bopsStockTakingPO);
				}
				//成功后回传wms
				//根据仓库id查询仓库是否安装wms系统
				String flag=bopsPurchaseDAO.selectWareHouseDredge(bopsStockTakingPO.getWarehouseid());
				logger.info("in saveStockTaking ... towms.... flag=... " +flag);
				//1为安装
				if("1".equals(flag)){
					/**
					 * 盘点下发至wms
					 */
					logger.info("in saveStockTaking ... towms.... flag: " +flag);
					OutCheckUploadVO outCheckUploadVO = new OutCheckUploadVO();
					outCheckUploadVO.setUploadNo(bopsStockTakingPO.getStockTakingNo());
					logger.info("in saveStockTaking ........bopsStockTakingPO.getStockTakingNo()......."+bopsStockTakingPO.getStockTakingNo().toString());
					//保存到报文发送表
					ESynEdiMessage eSynEdiMessage = new ESynEdiMessage(outCheckUploadVO.getUploadNo().toString(), "212",JSONObject.toJSONString(outCheckUploadVO),bopsStockTakingPO.getWarehouseid());
					logger.info("in saveStockTaking .................JSONObject.toJSONString........"+JSONObject.toJSONString(outCheckUploadVO).toString());
					eSynEdiMessageDAO.insert(eSynEdiMessage);
					//发送到wms
					NeedProducer needProducer = NeedProducer.getInstance();
					Boolean result = needProducer.sendSync("zz_erp2wms_212", eSynEdiMessage.getId()+"",JSONObject.toJSONString(eSynEdiMessage));
					logger.info("in saveStockTaking .....eSynEdiMessage.getId()..."+eSynEdiMessage.getId().toString());
					Long status;
					//改变结果状态
					if(result){
						status=2L;
					}else{
						status=9L;
					}
					eSynEdiMessage.setStatus(status);
					eSynEdiMessageDAO.updateByPrimaryKey(eSynEdiMessage);
					
				}
			}
		} catch (Exception e) {
			eSynEdiReceiveMessage.setStatus(9L);
			eSynEdiReceiveMessage.setHandleDate(new Date());
			eSynEdiReceiveMessageDAO.updateByPrimaryKey(eSynEdiReceiveMessage);
		}
		
		/**
		 * 修改库存,记录流水
		 */
		if(null == bopsReceiveSuccess){
			List<BopsStockTakingItemPO> itemGoodsList=bopsStockTakingItemDAO.selectGoodsListByTaking(invCheckUploadVO.getUploadNo());
			if(itemGoodsList.size()>0){
				for (BopsStockTakingItemPO bopsStockTakingItemPO : itemGoodsList) {
					StoreChangeVO storeChangeVO=new StoreChangeVO();
					//根据商品code查询id
					BopsGoods storeGoods=bopsGoodsDAO.selectByGoodsCode(bopsStockTakingItemPO.getGoodsCode());
					//根据仓库类型,查询仓库id
					storeChangeVO.setGoodsId(storeGoods.getGoodsId());
					if(1== bopsStockTakingItemPO.getInventoryState()){
						//正品
						storeChangeVO.setNowStoreCount(bopsStockTakingItemPO.getQty());
					}else if(2 == bopsStockTakingItemPO.getInventoryState()){ 
						//残品
						storeChangeVO.setDefectiveStore(bopsStockTakingItemPO.getQty());
					}
					storeChangeVO.setStoreType(StoreTypeEnum.STOCKTAKING.code);
					storeChangeVO.setStoreNo(invCheckUploadVO.getUploadNo());
					//查询仓库id
					storeChangeVO.setWarehouseId(itemList.get(0).getWarehouseId().intValue());
					storeChangeVO.setAuthorId(Constants.WMS_USER_ID);
					message = bopsGoodsStoreService.saveStoreChange(storeChangeVO);
					if(200 != message.getCode()){
						try {
							throw new Exception(message.getMsg());
						} catch (Exception e) {
							logger.info("store is not enough........");
							e.printStackTrace();
						}
					}
					//记录流水
					creditStockStore(storeChangeVO.getStoreNo(),Constants.WMS_USER_DEEFAULT);
				}
				
			}
		}
		return message;
		
	}
	/**
	 * 
	 * @author liuhongyang 2015年12月18日 下午5:11:40
	 * @Method: creditStore 
	 * @Description: 记录库存变化流水
	 * @param purchaseId
	 * @param havestUserId 
	 * @throws
	 */
	private void creditStockStore(String stockTakingNo,String havestUserId){
		BopsStockTakingPO bopsStockTakingPO = bopsStockTakingDAO.selectByStockNo(stockTakingNo);
		if(null != bopsStockTakingPO){
			BopsCreditMemoPO bopsCreditMemoPO = new BopsCreditMemoPO();
			bopsCreditMemoPO.setReportType(ReportTypeEnum.STOCKTAKING.code);
			bopsCreditMemoPO.setSerialNumber(stockTakingNo);
			bopsCreditMemoPO.seteWarehouseId(bopsStockTakingPO.getWarehouseid());
			bopsCreditMemoPO.setRemark(bopsStockTakingPO.getRemark());
			bopsCreditMemoPO.setCreatedBy(havestUserId);
			bopsCreditMemoPO.setCreatedAt(new Date());
			int changeCount=0;
			int changeTotalCost=0;
			int changeCreditAmount=0;
			bopsCreditMemoDAO.insert(bopsCreditMemoPO);
			List<BopsStockTakingItemPO> goodsList = bopsStockTakingItemDAO.selectGoodsList(bopsStockTakingPO.getId());
			if(goodsList.size()>0){
				for (BopsStockTakingItemPO bopsStockTakingItemPO : goodsList) {
					BopsCreditMemoItemPO bopsCreditMemoItemPO = new BopsCreditMemoItemPO();
					bopsCreditMemoItemPO.setBopsCreditMemoId(bopsCreditMemoPO.getId());
					bopsCreditMemoItemPO.setGoodsCode(bopsStockTakingItemPO.getGoodsCode());
					//根据goodsCode查询库存id
					BopsInventoryPO bopsInventoryPO = bopsInventoryDAO.selectByGoodsCode(bopsStockTakingItemPO.getGoodsCode());
					if(null != bopsInventoryPO){
						bopsCreditMemoItemPO.setBopsInventoryId(bopsInventoryPO.getId());
						bopsCreditMemoItemPO.setNormalChange(bopsInventoryPO.getNormalQuantity());
						bopsCreditMemoItemPO.setDemageQuantity(bopsInventoryPO.getDemageQuantity());
						bopsCreditMemoItemPO.setPrice(bopsInventoryPO.getPurchasePrice());
						bopsCreditMemoItemPO.setCost(bopsInventoryPO.getCost());
					}
					bopsCreditMemoItemPO.setGoodsName(bopsStockTakingItemPO.getGoodsName());
					if(1 == bopsStockTakingItemPO.getInventoryState().intValue()){
						//正品
						bopsCreditMemoItemPO.setNormalChange(bopsStockTakingItemPO.getQty());
						bopsCreditMemoItemPO.setDemageChange(0);
					}else if(2 == bopsStockTakingItemPO.getInventoryState().intValue()){
						//残品
						bopsCreditMemoItemPO.setNormalChange(0);
						bopsCreditMemoItemPO.setDemageChange(bopsStockTakingItemPO.getQty());
					}
					bopsCreditMemoItemPO.setCreatedAt(new Date());
					bopsCreditMemoItemPO.setCreatedBy(havestUserId);
					bopsCreditMemoItemDAO.insert(bopsCreditMemoItemPO);
					changeCount = changeCount + bopsCreditMemoItemPO.getDemageChange().intValue() + bopsCreditMemoItemPO.getNormalChange().intValue();
					changeTotalCost = changeTotalCost + changeCount*bopsCreditMemoItemPO.getPrice().intValue();
					changeCreditAmount = changeCreditAmount + changeCount*bopsCreditMemoItemPO.getCost().intValue();
				}
				bopsCreditMemoPO.setTotalCost(changeTotalCost);
				bopsCreditMemoPO.setCreditAmount(changeCreditAmount);
				bopsCreditMemoDAO.updateCount(bopsCreditMemoPO);
			}
		}
		
	}

}
