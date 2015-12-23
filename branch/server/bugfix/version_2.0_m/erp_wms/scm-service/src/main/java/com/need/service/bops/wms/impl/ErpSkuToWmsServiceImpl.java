package com.need.service.bops.wms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.need.dao.bops.goods.BopsGoodsCategoriesDAO;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.goods.BopsGoodsDetailDAO;
import com.need.dao.bops.store.BopsWarehouseBaseDAO;
import com.need.dao.bops.wms.ESynEdiMessageDAO;
import com.need.domain.common.constant.ERPBizReturnCode;
import com.need.domain.common.enums.WarehouseTypeEnum;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.goods.BopsGoodsCategoriesPO;
import com.need.domain.po.bops.goods.BopsGoodsDetail;
import com.need.domain.po.bops.wms.ESynEdiMessage;
import com.need.domain.pub.Message;
import com.need.domain.vo.wms.ErpSkuToWmsVO;
import com.need.kafka.services.producer.NeedProducer;
import com.need.service.bops.wms.ErpSkuToWmsService;
import com.need.service.constant.WmsConstants;
import com.need.utils.UUIDUtils;

@Service
public class ErpSkuToWmsServiceImpl implements ErpSkuToWmsService {
	
	@Autowired
	BopsGoodsDAO bopsGoodsDAO;
	@Autowired
	BopsGoodsDetailDAO bopsGoodsDetailDAO;
	@Autowired
	BopsGoodsCategoriesDAO bopsGoodsCategoriesDAO;
	@Autowired
	ESynEdiMessageDAO eSynEdiMessageDAO;
	@Autowired
	BopsWarehouseBaseDAO bopsWarehouseBaseDAO;
	
	@Transactional
	@Override
	public Message skuInfoToWms(String goodsId){
		Message message = Message.success();
		
		BopsGoods goods = bopsGoodsDAO.selectByGoodsId(goodsId);
		String warehouseId = WarehouseTypeEnum.getWarehouseId(goods.getWarehouseType());
		/**
		 * 判断仓库状态，如果是“2”，则不同步
		 */
		if("2".equals(bopsWarehouseBaseDAO.selectByPrimaryKey(Integer.parseInt(warehouseId)).getDredge())){
			return Message.error(ERPBizReturnCode.GOODS_3064);
		}
		
		BopsGoodsDetail goodsDetail = bopsGoodsDetailDAO.selectByPrimaryKey(goodsId);
		String goodsParam = goodsDetail.getGoodsParams();
		String goodsPlace = JSON.parseObject(goodsParam).getString("originPlace");
		BopsGoodsCategoriesPO categoryThree = bopsGoodsCategoriesDAO.selectByPrimaryKey(goods.getGoodsCategoryId());
		BopsGoodsCategoriesPO categoryTwo = null;
		BopsGoodsCategoriesPO categoryOne = null;
		if(categoryThree == null){
			categoryThree = new BopsGoodsCategoriesPO();
			categoryThree.setCategoryName(" ");
			categoryTwo = new BopsGoodsCategoriesPO();
			categoryTwo.setCategoryName(" ");
			categoryOne = new BopsGoodsCategoriesPO();
			categoryOne.setCategoryName(" ");
		}
		else{
			try {
				categoryTwo = bopsGoodsCategoriesDAO.selectByPrimaryKey(categoryThree.getParentId());
				categoryOne = bopsGoodsCategoriesDAO.selectByPrimaryKey(categoryTwo.getParentId());
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				categoryTwo = new BopsGoodsCategoriesPO();
				categoryTwo.setCategoryName(" ");
				categoryOne = new BopsGoodsCategoriesPO();
				categoryOne.setCategoryName(" ");
			}
		}
		
		ErpSkuToWmsVO erpSkuToWmsVO = new ErpSkuToWmsVO();
		erpSkuToWmsVO.setId(Long.parseLong(goods.getGoodsCode()));
		erpSkuToWmsVO.setProductId(Long.parseLong(goods.getGoodsCode()));
		erpSkuToWmsVO.setChineseName(goods.getGoodsName());
		erpSkuToWmsVO.setSkuStandard("");
		erpSkuToWmsVO.setProducingArea(goodsPlace);
		try{
			erpSkuToWmsVO.setOneCategory(categoryOne.getCategoryName());
		} catch (NullPointerException e){
			erpSkuToWmsVO.setOneCategory(" ");
		}
		erpSkuToWmsVO.setTwoCategory(categoryTwo.getCategoryName());
		erpSkuToWmsVO.setThreeCategory(categoryThree.getCategoryName());
		erpSkuToWmsVO.setFourCategory(categoryThree.getCategoryName());
		erpSkuToWmsVO.setActiveFlg("1");//审批状态 1
		erpSkuToWmsVO.setRetailPrice(((long)(int)goods.getDiscountPrice())/100);
		erpSkuToWmsVO.setQualityDate(goods.getValidDate() == null ? 0 : goods.getValidDate());
		erpSkuToWmsVO.setQualityUnit("月");//我们的商品的有效期的单位都是“月”
		erpSkuToWmsVO.setQualityDateFlg(Boolean.TRUE.toString().toUpperCase().equals(goods.getIsValidDate()) ? "1" : "0");
		erpSkuToWmsVO.setsPackBarcode(goods.getGoodsBarcode());
		String body = JSONObject.toJSONString(erpSkuToWmsVO);
		ESynEdiMessage eSynEdiMessage = new ESynEdiMessage(UUIDUtils.getUUID() + "_" + goods.getGoodsCode(), WmsConstants.BUSINESS_TYPE_IM_SKU, body, 99);
		eSynEdiMessageDAO.insert(eSynEdiMessage);
		NeedProducer needProducer = NeedProducer.getInstance();
		boolean result = false;
		/**
		 * 调用kafka生产者发布消息
		 */
		result = needProducer.sendSync("sku_erp2wms_001", eSynEdiMessage.getId().toString(), JSONObject.toJSONString(eSynEdiMessage));
		if(result){
			eSynEdiMessageDAO.updateStatus(eSynEdiMessage.getId());
		}
		return message;
	}
	
}
