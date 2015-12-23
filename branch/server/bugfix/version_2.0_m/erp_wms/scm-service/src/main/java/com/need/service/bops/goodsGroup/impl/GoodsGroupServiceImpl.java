package com.need.service.bops.goodsGroup.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.biz.utils.BizCodeUtil;
import com.need.biz.utils.CurrencyUtil;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.api.goodsgroup.GoodsGroupBaseDAO;
import com.need.dao.api.goodsgroup.GoodsGroupDAO;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.goodsgroup.BopsGoodsGroupBaseDAO;
import com.need.dao.bops.goodsgroup.BopsGoodsGroupDAO;
import com.need.domain.common.enums.GoodsGroupStatusEnums;
import com.need.domain.common.enums.GoodsTypeEnums;
import com.need.domain.common.enums.GroupRuleTypeEnums;
import com.need.domain.common.enums.WarehouseTypeEnum;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.api.goodsgroup.GoodsGroup;
import com.need.domain.po.api.goodsgroup.GoodsGroupBase;
import com.need.domain.po.api.goodsgroup.GoodsGroupKey;
import com.need.domain.po.bops.goodsgroup.BopsGoodsGroup;
import com.need.domain.po.bops.goodsgroup.BopsGoodsGroupBase;
import com.need.domain.po.bops.goodsgroup.BopsGoodsGroupKey;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.GoodsStoreDetailResultVO;
import com.need.domain.vo.goodsgroup.GoodsGroupParaVO;
import com.need.domain.vo.goodsgroup.GoodsGroupResultVO;
import com.need.domain.vo.goodsgroup.GoodsGroupShowVO;
import com.need.domain.vo.goodsgroup.GoodsGroupVO;
import com.need.domain.vo.goodsgroup.GoodsShowVO;
import com.need.domain.vo.goodsgroup.GroupRuleVO;
import com.need.service.bops.goodsGroup.GoodsGroupService;
import com.need.service.constant.BizReturnCode;
import net.sf.json.JSONObject;

@Service
public class GoodsGroupServiceImpl implements GoodsGroupService {
	private static final Logger logger = Logger.getLogger(GoodsGroupServiceImpl.class);
	
	@Autowired
	private BopsGoodsGroupBaseDAO bopsGoodsGroupBaseDAO;
	@Autowired
	private BopsGoodsGroupDAO bopsGoodsGroupDAO;
	@Autowired
	private GoodsGroupBaseDAO goodsGroupBaseDAO;
	@Autowired
	private GoodsGroupDAO goodsGroupDAO;
	
	@Autowired
	private BopsGoodsDAO bopsGoodsDAO; 
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	
	@Override
	public List<GoodsGroupResultVO> getAll(GoodsGroupVO goodsGroupVO) {
		logger.info("getAll in GoodsGroupServiceImpl goodsGroupVO:"+goodsGroupVO.toString());
		int count= bopsGoodsGroupBaseDAO.getCount(goodsGroupVO);
		goodsGroupVO.setTotal(count);
		List<GoodsGroupResultVO> list= bopsGoodsGroupBaseDAO.queryAll(goodsGroupVO);
		return list;
	}
    
	/***
	 * 
	 * @author LXD 2015年11月25日 上午10:31:11
	 * @Method: addGroup 
	 * @Description: TODO 新增商品组合
	 * @param goodsGroupVO
	 * @return 
	 * @see com.need.service.bops.goodsGroup.GoodsGroupService#addGroup(com.need.domain.vo.goodsgroup.GoodsGroupVO)
	 */
	@Transactional
	@Override
	public Message addGroup(GoodsGroupVO goodsGroupVO) {
		Message success=Message.success();
		//添加组合
		BopsGoodsGroupBase bopsGoodsGroupBase =new BopsGoodsGroupBase();
		BeanUtils.copyProperties(goodsGroupVO, bopsGoodsGroupBase);
		//组合规则
		String ruleContent= createRule(goodsGroupVO);
		bopsGoodsGroupBase.setRuleContent(ruleContent);
		//确实规则
		String batchNo=BizCodeUtil.generateGoodsGroupId(goodsGroupVO.getGoodsCodes());
		bopsGoodsGroupBase.setGroupBatch(batchNo);
		bopsGoodsGroupBaseDAO.insert(bopsGoodsGroupBase);
		//插入前台
		GoodsGroupBase goodsGroupBase=new GoodsGroupBase();
		BeanUtils.copyProperties(bopsGoodsGroupBase, goodsGroupBase);
		goodsGroupBaseDAO.insert(goodsGroupBase);
		//前后台插入商品
		addGroupGoods(batchNo,goodsGroupVO.getGoodsCodes(),goodsGroupVO.getCreateId());
		return success;
	}
    
	
	//组合规则
	private String createRule(GoodsGroupVO goodsGroupVO) {

		  //折扣规则
			
		  if(GroupRuleTypeEnums.DISCOUNT.code.equals(goodsGroupVO.getRuleType())){
			Map<String,String> ruleMap =new HashMap<String,String>();
			ruleMap.put(goodsGroupVO.getRuleOne(), goodsGroupVO.getRuleOneDisc());
			ruleMap.put(goodsGroupVO.getRuleTwo(), goodsGroupVO.getRuleTwoDisc());
			ruleMap.put(goodsGroupVO.getRuleThree(), goodsGroupVO.getRuleThreeDisc());
			JSONObject jsonObject = JSONObject.fromObject(ruleMap);
			return  jsonObject.toString();
			  
		  }else{
		   //一口价规则  
			 Map<String,Integer> ruleMap =new HashMap<String,Integer>();
			 String price= CurrencyUtil.fromYuanToFen(goodsGroupVO.getRuleFixedPrice());
			 ruleMap.put(goodsGroupVO.getRuleFixed(),Integer.parseInt(price));
			 JSONObject jsonObject = JSONObject.fromObject(ruleMap);
			 return  jsonObject.toString();
		  }
			

	}
	 public static String generateGroupId() {
	    	SimpleDateFormat DF_DATETIME = new SimpleDateFormat("yyyyMMddHHmm");
	    	Random randomBase=new Random(99999999);
	    	 String dateFormat = DF_DATETIME.format(Calendar.getInstance().getTime());
	 		long timefator=Calendar.getInstance().getTimeInMillis();
	     	    	
	     	/**随机因子数   */
	     	Long random=(timefator+randomBase.nextInt())%99999999;
	     	int randomLength=String.valueOf(random).length();    	
	     	long placeHolderLength=8-randomLength;
	     	/**前6位是时间因子  */
	     	StringBuffer  groupNo=new StringBuffer(dateFormat); 	
	         //如果长度不够填充其他数字
	     	for(int i=0;i<placeHolderLength;i++) {
	     		groupNo.append("0");
	         }
	     	groupNo.append(random);
	     	return groupNo.toString();
	    }
    
	@Transactional
	@Override
	public void audit(String groupBatch, Integer userId) {
		logger.info("groupBatch:"+groupBatch);
		bopsGoodsGroupBaseDAO.updateStatus(groupBatch,GoodsGroupStatusEnums.VALID.code,userId);
		goodsGroupBaseDAO.updateStatus(groupBatch,GoodsGroupStatusEnums.VALID.code,userId);
		
		
	}

	@Override
	public Message addGroupGoods(String groupBatch, String goodsCodes, Integer userId) {
		Message success=Message.success();
		String newGoodsCode=goodsCodes.trim();
		if(newGoodsCode.endsWith(",")){
			newGoodsCode=newGoodsCode.substring(0,newGoodsCode.length()-1);
		}
		String [] goodsCodesArray=newGoodsCode.split(",");
		String [] newGoodsCodes=array_unique(goodsCodesArray);
		List<String> goodsCodelist =Arrays.asList(newGoodsCodes);
		List<GoodsStoreDetailResultVO> list= bopsGoodsDAO.getByGoodsCodes(goodsCodelist);
		BopsGoodsGroup groupGoods = new BopsGoodsGroup();
		//xinhuanGoods.setOpsId(opsId);
		groupGoods.setGroupBatch(groupBatch);
		for (GoodsStoreDetailResultVO goodsStoreDetailResultVO : list) {
			/**
			 * 该商品已经添加到这个运营位，不需要重复添加
			 */
			BopsGoodsGroupKey key=new BopsGoodsGroupKey();
			key.setGoodsId(goodsStoreDetailResultVO.getGoodsId());
			key.setGroupBatch(groupBatch);
			BopsGoodsGroup goods = bopsGoodsGroupDAO.selectByPrimaryKey(key);
			if(goods != null){
				continue;
			}
			groupGoods.setGoodsId(goodsStoreDetailResultVO.getGoodsId());
			groupGoods.setGoodsCode(goodsStoreDetailResultVO.getGoodsCode());
			groupGoods.setGoodsPicKey(goodsStoreDetailResultVO.getScenePicKey());
			groupGoods.setCreateId(userId);
			/**
			 * 插入商品
			 */
			bopsGoodsGroupDAO.insert(groupGoods);
			/**
			 * 插入前台
			 */
			GoodsGroup goodsGroup =new GoodsGroup();
			BeanUtils.copyProperties(groupGoods, goodsGroup);
			goodsGroupDAO.insert(goodsGroup);
		}
		
		return success;
		
	}

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
				if(goodsShowVO.getStoreCount()>0){
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

	@Override
	public Message checkGoods(String goodsCodes) {
		Message success=Message.success();
		//检验是否有保税仓
		String newGoodsCode=goodsCodes.trim();
		if(newGoodsCode.endsWith(",")){
			newGoodsCode=newGoodsCode.substring(0,newGoodsCode.length()-1);
		}
		String [] goodsCodesArray=newGoodsCode.split(",");
		List<String> goodsCodelist =Arrays.asList(goodsCodesArray);
		List<GoodsStoreDetailResultVO> list= bopsGoodsDAO.getByGoodsCodes(goodsCodelist);
		if(list.size()==0){
			return Message.error(BizReturnCode.NO_GOODS);
		}
		String warehouseType =list.get(0).getWarehouseType();
		for(GoodsStoreDetailResultVO goods:list){
			if(WarehouseTypeEnum.TAX_WAREHOUSE.code.equals(goods.getWarehouseType())){
				return Message.error(BizReturnCode.GOODS_GROUP_INCLUDE_TAX_WAREHOUSE);
			}else if(!warehouseType.equals(goods.getWarehouseType())){
				return Message.error(BizReturnCode.GOODS_GROUP_WAREHOSE_NO_SAME);
				
			}else if(GoodsTypeEnums.MORE.code.equals(goods.getGoodsType())){
				return Message.error(BizReturnCode.CONTAIN_PACK_GOODS);
				
			}
			
		}
		return success;
	}
	
    //去除数组中重复的记录  
    public static String[] array_unique(String[] a) {  
        // array_unique  
        List<String> list = new LinkedList<String>();  
        for(int i = 0; i < a.length; i++) {  
            if(!list.contains(a[i])) {  
                list.add(a[i]);  
            }  
        }  
        return (String[])list.toArray(new String[list.size()]);  
    }

	@Override
	public List<GoodsGroupParaVO> getBybatch(GoodsGroupParaVO goodsGroupVO) {
		logger.info("getBybatch in GoodsGroupServiceImpl GoodsGroupParaVO:"+goodsGroupVO.toString());
		int count= bopsGoodsGroupDAO.getCountBybatch(goodsGroupVO);
		goodsGroupVO.setTotal(count);
		List<GoodsGroupParaVO> list= bopsGoodsGroupDAO.queryByBatch(goodsGroupVO);
		for(GoodsGroupParaVO goods:list){
			goods.setGoodsPrice((double)(goods.getDiscountPrice()/100));
			GoodsMainPO goodsMain = goodsMainDAO.selectByPrimaryKey(goods.getGoodsId());
			if(goodsMain!=null){
				goods.setStoreCount(goodsMain.getStoreCount());	
			}
			
			
		}
		return list;
	}

	@Override
	public GoodsGroupVO getToEdit(String groupBatch) {
		BopsGoodsGroupBase group= bopsGoodsGroupBaseDAO.selectByPrimaryKey(groupBatch);
		GoodsGroupVO goodsGroupVO=new GoodsGroupVO();
		BeanUtils.copyProperties(group, goodsGroupVO);
        List<GroupRuleVO> ruleList=getRule(group.getRuleContent(),group.getRuleType());
        if(GroupRuleTypeEnums.DISCOUNT.code.equals(group.getRuleType())){
        	goodsGroupVO.setRuleOne(ruleList.get(2).getNumber().toString());
        	goodsGroupVO.setRuleOneDisc(ruleList.get(2).getDiscount());
        	goodsGroupVO.setRuleTwo(ruleList.get(1).getNumber().toString());
        	goodsGroupVO.setRuleTwoDisc(ruleList.get(1).getDiscount());
        	goodsGroupVO.setRuleThree(ruleList.get(0).getNumber().toString());
        	goodsGroupVO.setRuleThreeDisc(ruleList.get(0).getDiscount());	 
         }else{
        	 goodsGroupVO.setRuleFixed(ruleList.get(0).getNumber().toString());
         	 goodsGroupVO.setRuleFixedPrice(((float)(ruleList.get(0).getFixedPrice()/100))+"");
         }   
		  return goodsGroupVO;
        }
    /**
     * 
     * @author LXD 2015年11月27日 下午5:29:35
     * @Method: editGroup 
     * @Description: TODO 编辑商品
     * @param goodsGroupVO 
     * @see com.need.service.bops.goodsGroup.GoodsGroupService#editGroup(com.need.domain.vo.goodsgroup.GoodsGroupVO)
     */
	@Override
	public void editGroup(GoodsGroupVO goodsGroupVO) {
		//编辑组合
		BopsGoodsGroupBase bopsGoodsGroupBase =bopsGoodsGroupBaseDAO.selectByPrimaryKey(goodsGroupVO.getGroupBatch());
		bopsGoodsGroupBase.setGroupBrief(goodsGroupVO.getGroupBrief());
		bopsGoodsGroupBase.setGroupName(goodsGroupVO.getGroupName());
		bopsGoodsGroupBase.setRuleType(goodsGroupVO.getRuleType());
		//BeanUtils.copyProperties(goodsGroupVO, bopsGoodsGroupBase);
		//组合规则
		String ruleContent= createRule(goodsGroupVO);
		bopsGoodsGroupBase.setRuleContent(ruleContent);
		//确实规则
		bopsGoodsGroupBaseDAO.updateByPrimaryKey(bopsGoodsGroupBase);
		//插入前台
		GoodsGroupBase goodsGroupBase=new GoodsGroupBase();
		BeanUtils.copyProperties(bopsGoodsGroupBase, goodsGroupBase);
		goodsGroupBaseDAO.updateByPrimaryKey(goodsGroupBase);
		//前后台插入商品
		if(StringUtils.isNotBlank(goodsGroupVO.getGoodsCode())){
		addGroupGoods(goodsGroupBase.getGroupBatch(),goodsGroupVO.getGoodsCodes(),goodsGroupVO.getUpdateId());
		}
	}
    
	@Transactional
	@Override
	public Message deleteGoods(BopsGoodsGroupKey key) {
		Message success=Message.success();
		bopsGoodsGroupDAO.deleteByPrimaryKey(key);
		GoodsGroupKey apiKey=new GoodsGroupKey();
		BeanUtils.copyProperties(key, apiKey);
		goodsGroupDAO.deleteByPrimaryKey(apiKey);
		return success;
	}
		
	  
	
}
