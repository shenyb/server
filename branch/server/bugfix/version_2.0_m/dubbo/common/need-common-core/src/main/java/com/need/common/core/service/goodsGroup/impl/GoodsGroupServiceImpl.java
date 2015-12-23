package com.need.common.core.service.goodsGroup.impl;

import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.goodsGroup.GoodsGroupBaseDAO;
import com.need.common.core.service.goodsGroup.GoodsGroupService;
import com.need.common.domain.enums.GroupRuleTypeEnums;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.goodsGroup.GoodsGroupBase;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.goodsgroup.GroupRuleVO;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


@Service
public class GoodsGroupServiceImpl implements GoodsGroupService {
    
	private static final Logger logger = Logger.getLogger(GoodsGroupServiceImpl.class);
	
	@Autowired
	private GoodsGroupBaseDAO goodsGroupBaseDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	/**
	 * 
	 * @author LXD 2015年11月25日 下午8:36:23
	 * @Method: getGroupPrice 
	 * @Description: TODO 根据batch号和商品数量计算此单价格
	 * @param groupBatch
	 * @param goodsNumber
	 * @return 
	 * @see com.need.api.service.goodsGroup.GoodsGroupService#getGroupPrice(java.lang.String, int)
	 */
	@Override
	public Message getGroupPrice(String groupBatch, int goodsNumber,List<String> goodsIds) {
		logger.info("getGroupPrice in GoodsGroupServiceImpl groupBatch="+groupBatch+"&&&goodsNumber="+goodsNumber+"goodsIds size"+goodsIds.size());
		Message success=Message.success();
		GoodsGroupBase groupBase= goodsGroupBaseDAO.selectByPrimaryKey(groupBatch);
		if(groupBase==null){
			return Message.error(BizReturnCode.GOODS_GROUP_NO_EXIST);	
			
		}
		if(GroupRuleTypeEnums.DISCOUNT.code.equals(groupBase.getRuleType())){
			int totalPrice =0;
			for(String goodsId:goodsIds){
			  Integer goodsPrice=	goodsMainDAO.getByGoodsIdAndBuyCount(goodsId, 1);
			  if (goodsPrice == null) {
				  logger.error("-+-+-+-+-+-+-+-+-+goods is 0 price which tradeNo is" + goodsIds);
					return Message.error(BizReturnCode.GOODS_STORE_NOT_ENOUGH);
				}
			  totalPrice+=goodsPrice;	
			  logger.info("-+-+-+-+-+-+-+-+-+goods Original TotalPrice totalPrice:"+totalPrice);
			}
			/**
			 * 计算折扣规则的价格
			 */
			List<GroupRuleVO> list=getRule(groupBase.getRuleContent(),groupBase.getRuleType());
             //大于最大件数
			if(goodsNumber>=list.get(0).getNumber()){
				logger.info("-+-+-+-+-+-+-+-+-+ruleType: DISCOUNT: -+-+-+-+-+-+-+-+-+rule maxNumber:"+list.get(0).getNumber()+"-+-+-+-+-+-+-+-+-+rule maxDiscount="+list.get(0).getDiscount());
				BigDecimal discount= (new BigDecimal(list.get(0).getDiscount()).divide(new BigDecimal(10d)));
				int newPrice =new BigDecimal(totalPrice).multiply(discount).intValue();
				logger.info("MaxNumber TotalPrice:"+newPrice);
				success.addData("totalPrice", newPrice);
				return success;
				
			}else if(goodsNumber>=list.get(1).getNumber()&&goodsNumber<list.get(0).getNumber()){
				logger.info("-+-+-+-+-+-+-+-+-+ruleType: DISCOUNT: -+-+-+-+-+-+-+-+-+rule secondaryNumber:"+list.get(1).getNumber()+"-+-+-+-+-+-+-+-+-+rule secondaryDiscount="+list.get(1).getDiscount());
				BigDecimal discount= (new BigDecimal(list.get(1).getDiscount()).divide(new BigDecimal(10d)));
				int newPrice =new BigDecimal(totalPrice).multiply(discount).intValue();
				logger.info("secondaryNumber TotalPrice:"+newPrice);
				success.addData("totalPrice", newPrice);
				return success;
				
			}else if(goodsNumber>=list.get(2).getNumber()&&goodsNumber<list.get(1).getNumber()){
				logger.info("-+-+-+-+-+-+-+-+-+ruleType: DISCOUNT: -+-+-+-+-+-+-+-+-+rule minNumber:"+list.get(1).getNumber()+"-+-+-+-+-+-+-+-+-+rule minDiscount="+list.get(2).getDiscount());
				BigDecimal discount= (new BigDecimal(list.get(2).getDiscount()).divide(new BigDecimal(10d)));
				int newPrice =new BigDecimal(totalPrice).multiply(discount).intValue();
				logger.info("minNumber TotalPrice:"+newPrice);
				success.addData("totalPrice", newPrice);
				return success;
			}else{
				logger.info("-+-+-+-+-+-+-+-+-+ruleType: NONUMBER NODISCOUNT:");
				success.addData("totalPrice", totalPrice);
				logger.info("NONUMBER NODISCOUNT TotalPrice:"+totalPrice);
				return success;
			}
			
		}else{
			/***
			 * 计算一口价规则的结构
			 */
			List<GroupRuleVO> list=getRule(groupBase.getRuleContent(),groupBase.getRuleType());
			List<GoodsMainPO> goodsList= goodsMainDAO.getgoodsByGoodsIds(goodsIds);
			
			int  goodsPrice=0;
            /***
             * 取倍数
             */
			int times=goodsNumber/list.get(0).getNumber();
			/**
			 * 取余
			 */
			int residue=goodsNumber%list.get(0).getNumber();
            /***
             * 可以被整除的总价
             */
			goodsPrice=times * list.get(0).getFixedPrice();
            /**
             * 不能被整除的总价
             */
			if(residue!=0){
            
			  for(int i=0; i< residue;i++ ){			  
				  goodsPrice+=goodsList.get(i).getDiscountPrice(); 
			  			  
             }

			}
			success.addData("totalPrice", goodsPrice);
			return success;
				
		}
		
	}
	
	
	public Message getTotalPrice(List<String> goodsIds){
		Message success=Message.success();
		int totalPrice =0;
		for(String goodsId:goodsIds){
		  Integer goodsPrice=	goodsMainDAO.getByGoodsIdAndBuyCount(goodsId, 1);
		  if (goodsPrice == null) {
			  logger.error("-+-+-+-+-+-+-+-+-+goods is 0 price which tradeNo is" + goodsIds);
				return Message.error(BizReturnCode.GOODS_STORE_NOT_ENOUGH);
			}
		  totalPrice+=goodsPrice;	
		}
		success.addData("totalPrice", totalPrice);
		return success;
	}
	
	
	
	
	@SuppressWarnings("rawtypes")
	public List<GroupRuleVO> getRule(String ruleContent,String ruleType) { 
		List<GroupRuleVO> listRule=new ArrayList<GroupRuleVO>();
		JSONObject jsonObject = JSONObject.fromObject(ruleContent);    
        
		for (Iterator iter = jsonObject.keys(); iter.hasNext();) {   
            String key = (String) iter.next();   
            //map.put(key, jsonObject.get(key));
            GroupRuleVO groupRuleVO =new GroupRuleVO();
            groupRuleVO.setNumber(Integer.parseInt(key));
            if(GroupRuleTypeEnums.DISCOUNT.code.equals(ruleType)){
            groupRuleVO.setDiscount(jsonObject.get(key).toString());
            }else{
             groupRuleVO.setFixedPrice((Integer)jsonObject.get(key));	
            }
            listRule.add(groupRuleVO);

        }   
		Collections.sort(listRule);
        return listRule;
	}

	
	public static void main(String[] args) {
		GoodsGroupServiceImpl test =new GoodsGroupServiceImpl();
		List<String> goodsIds=new ArrayList<String>();
		goodsIds.add("60d0671a72");
		goodsIds.add("85562a38b4");
		goodsIds.add("9d544f1648");
		Message message= test.getGroupPrice("20151125184399848315",3,goodsIds);
		System.out.println(message.getData().get("totalPrice"));
	}


}
