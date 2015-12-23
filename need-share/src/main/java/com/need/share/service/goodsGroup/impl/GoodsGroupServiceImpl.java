package com.need.share.service.goodsGroup.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.need.share.common.enums.GoodsStatusEnums;
import com.need.share.common.enums.GroupRuleTypeEnums;
import com.need.share.constant.BizReturnCode;
import com.need.share.dao.jdbc.api.goodsGroup.GoodsGroupBaseDAO;
import com.need.share.dao.jdbc.api.goodsGroup.GoodsGroupDAO;
import com.need.share.dao.jdbc.api.goodsGroup.po.GoodsGroupBase;
import com.need.share.pub.Message;
import com.need.share.service.goodsGroup.GoodsGroupService;
import com.need.share.web.controller.goodsGroup.vo.GoodsGroupShowVO;
import com.need.share.web.controller.goodsGroup.vo.GoodsShowVO;
import com.need.share.web.controller.goodsGroup.vo.GroupRuleVO;

import net.sf.json.JSONObject;

@Service
public class GoodsGroupServiceImpl implements GoodsGroupService {
	private static final Logger logger = Logger.getLogger(GoodsGroupServiceImpl.class);
	

	@Autowired
	private GoodsGroupBaseDAO goodsGroupBaseDAO;
	@Autowired
	private GoodsGroupDAO goodsGroupDAO;
	
	


	@Override
	public Message getGoodsGroupByBatch(String groupBatch) {
		Message success=Message.success();
		GoodsGroupShowVO goodsGroupShowVO=new GoodsGroupShowVO();
		//根据批次查询已经生效且在规定时间内的base
		GoodsGroupBase groupbase= goodsGroupBaseDAO.getByBatch(groupBatch);
		if(groupbase==null){
			return Message.error(BizReturnCode.GOODS_GROUP_NO_EXIST);
		}else{
		//查询该base下的商品
			List<GoodsShowVO> goodsList=goodsGroupDAO.getByBatch(groupBatch);
			for(GoodsShowVO goodsShowVO:goodsList){
				if(goodsShowVO.getStoreCount()>0&&GoodsStatusEnums.ONLINE.code.equals(goodsShowVO.getGoodsStatus())){
					goodsShowVO.setIsSoldout(Boolean.FALSE.toString().toUpperCase());
				}else{
					goodsShowVO.setIsSoldout(Boolean.TRUE.toString().toUpperCase());
				}
			}
			if(goodsList!=null&&goodsList.size()>0){
			 goodsGroupShowVO.setGoodsShow(goodsList);
			 goodsGroupShowVO.setWarehouseType(goodsList.get(0).getWarehouseType());
			 goodsGroupShowVO.setGroupBatch(groupbase.getGroupBatch());
			 goodsGroupShowVO.setGroupBrief(groupbase.getGroupBrief());
			 goodsGroupShowVO.setRuleType(groupbase.getRuleType());
			 List<GroupRuleVO> ruleList=getRule(groupbase.getRuleContent(),groupbase.getRuleType());
			 goodsGroupShowVO.setGroupRule(ruleList);
			}else{
				return Message.error(BizReturnCode.GOODS_GROUP_NO_EXIST);
			}
		}
		//封装数据
		success.addData("goodsGroup", goodsGroupShowVO);
		//返回message
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
	
}
