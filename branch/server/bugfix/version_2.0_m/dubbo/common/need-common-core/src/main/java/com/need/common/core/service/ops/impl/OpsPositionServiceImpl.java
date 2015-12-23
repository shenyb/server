package com.need.common.core.service.ops.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.need.NeedGoodsDAO;
import com.need.common.core.dao.jdbc.ops.*;
import com.need.common.core.dao.jdbc.user.UserBaseDAO;
import com.need.common.core.pub.ConstantsProConfig;
import com.need.common.core.service.ops.OpsPositionService;
import com.need.common.core.service.ops.OpsXinhuanService;
import com.need.common.domain.enums.NeedStatusEnum;
import com.need.common.domain.enums.OpsTypeEnum;
import com.need.common.domain.enums.UserNeedStatus;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.need.NeedGoodsPO;
import com.need.common.domain.po.api.ops.*;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.ops.*;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class OpsPositionServiceImpl implements OpsPositionService {
    
	private static final Logger logger = Logger.getLogger(OpsPositionServiceImpl.class);

	
	@Autowired
	private OpsPositionDAO opsPositionDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private UserBaseDAO userBaseDAO;
	@Autowired
	private OpsTopicDAO opsTopicDAO;
	@Autowired
	private NeedGoodsDAO needGoodsDAO;
	@Autowired
	private OpsHotGooodsDAO opsHotGooodsDAO;
	
	@Autowired
	private OpsSelectedDAO opsSelectedDAO;
	@Autowired
	private OpsTopicTemplateDAO opsTopicTemplateDAO;
	@Autowired
	private OpsXinhuanService opsXinhuanService;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	
	@Override
	public List<OpsPositionPO> getOpsPosition() {
		return opsPositionDAO.querBypageHome();
		
	}

    /**
     * 
     * @author LXD 2015年8月25日 下午2:16:02
     * @Method: queryhomeOps 
     * @Description: TODO 商店运营位
     * @return 
     * @see com.need.api.service.ops.OpsPositionService#queryShopOps()
     */
	@Override
	public Message queryShopOps() {
		Message success = Message.success();
		List<OpsPositionVO> opsList = new ArrayList<OpsPositionVO>();
		List<OpsPositionPO> opsPOList = opsPositionDAO.querBypageOther();
		//封装对象
		getOpsList(opsList, opsPOList);
		//无用对象置为null
		opsPOList=null;
		success.addData("opsList", opsList);
		return success;
	}
     /**
      * 
      * @author LXD 2015年8月25日 下午2:25:30
      * @Method: queryHomeOps 
      * @Description: TODO 首页运营位
      * @return 
      * @see com.need.api.service.ops.OpsPositionService#queryHomeOps()
      */
	@Override
	public Message queryHomeOps() {
	Message success = Message.success();
        List<OpsPositionVO> opsList =new ArrayList<OpsPositionVO>();
		List<OpsPositionPO> opsPOList= opsPositionDAO.querBypageHome();
		//封装对象
		getOpsList(opsList, opsPOList);
		//无用对象置为null
		opsPOList=null;
		success.addData("opsList", opsList);
		return success;
		
	}
    /**
     * 
     * @author LXD 2015年8月25日 下午2:31:22
     * @Method: getOpsList 
     * @Description: TODO 封装VO对象的方法
     * @param opsList
     * @param opsPOList 
     * @throws
     */
	private void getOpsList(List<OpsPositionVO> opsList, List<OpsPositionPO> opsPOList) {
		if (opsPOList != null && opsPOList.size() > 0) {
			for (OpsPositionPO opsPositionPO : opsPOList) {
				OpsPositionVO opsPositionVO = new OpsPositionVO();
				if (OpsTypeEnum.GOODS.code.equals(opsPositionPO.getOpsType())) {
					// 如果是商品
					GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(opsPositionPO.getTypeId());
					//Addy liyongran 20150920 1.0版本过滤调保税仓商品
					if(goods.getIsGlobal().equals("TRUE")){
						logger.info("the goods "+goods.getGoodsId()+" is global goods  1.0 ignore .");
						continue;
					}
					//Addy liyongran 20150920 1.0版本过滤调保税仓商品 end
					opsPositionVO.setOpsId(goods.getGoodsId());
					opsPositionVO.setOpsPickey(goods.getScenePicKey());
					opsPositionVO.setOpsType(opsPositionPO.getOpsType());

				} else if (OpsTypeEnum.KOL.code.equals(opsPositionPO.getOpsType())) {
					// 如果是行家
					UserBasePO user = userBaseDAO.selectByPrimaryKey(opsPositionPO.getTypeId());
					opsPositionVO.setOpsId(user.getUserId());
					opsPositionVO.setOpsPickey(user.getProfilePicKey());
					opsPositionVO.setOpsType(opsPositionPO.getOpsType());

				} else if (OpsTypeEnum.TOPIC.code.equals(opsPositionPO.getOpsType())) {
					// 如果是专题
					OpsTopicPO ops = opsTopicDAO.selectByPrimaryKey(Integer.parseInt(opsPositionPO.getTypeId()));
					opsPositionVO.setOpsId(ops.getTopicId().toString());
					opsPositionVO.setOpsPickey(ops.getCoverKey());
					opsPositionVO.setOpsType(opsPositionPO.getOpsType());

				}else{
					//如果是团便宜
					opsPositionVO.setOpsId(opsPositionPO.getTypeId());
					opsPositionVO.setOpsName(opsPositionPO.getOpsName());
					opsPositionVO.setOpsPickey(opsPositionPO.getOpsPicKey());
					
				}

				opsList.add(opsPositionVO);

			}
		}
	}
    /**
     * 
     * @author LXD 2015年8月25日 下午2:48:09
     * @Method: queryHotGoods 
     * @Description: TODO 获取人气列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return 
     * @see com.need.api.service.ops.OpsPositionService#queryHotGoods(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
	@Override
	public Message queryHotGoods(String userId, Integer pageNum, Integer pageSize) {
		Message success = Message.success();
		PageHelper.startPage(pageNum, pageSize);
		List<HotGoodsVO> resultList = new ArrayList<HotGoodsVO>();
		Page<OpsHotGooodsPO> page = (Page<OpsHotGooodsPO>) opsHotGooodsDAO.queyByPage();
		for (OpsHotGooodsPO hotgoods : page.getResult()) {
			GoodsMainPO goodsPO = goodsMainDAO.selectByPrimaryKey(hotgoods.getGoodsId());
			HotGoodsVO VO = new HotGoodsVO();
			if (goodsPO != null) {
				//Addy liyongran 20150920 1.0版本过滤调保税仓商品
				if(goodsPO.getIsGlobal().equals("TRUE")){
					logger.info("the goods "+goodsPO.getGoodsId()+" is global goods  1.0 ignore .");
					continue;
				}
				//Addy liyongran 20150920 1.0版本过滤调保税仓商品 end
				
				
				OpsHotGoodsVO goods = new OpsHotGoodsVO();
				BeanUtils.copyProperties(goodsPO, goods);
				goods.setTopicPicKey(goodsPO.getScenePicKey());
				VO.setGoods(goods);
				int needCount = needGoodsDAO.getNeedGoodsCount(hotgoods.getGoodsId());
				NeedVO need = new NeedVO();
				need.setNeedCount(needCount);
				NeedGoodsPO needGoodsPO= needGoodsDAO.queryByParams(userId,hotgoods.getGoodsId(),UserNeedStatus.NEED.code);
				if(needGoodsPO!=null){
					need.setIsNeed(NeedStatusEnum.NEED.code);
				}else{
					need.setIsNeed(NeedStatusEnum.CANCEL.code);
				}
				VO.setNeed(need);
				resultList.add(VO);
			}

		}

		success.addData("hotList", resultList);
		success.addData("totalCount", page.getTotal());
		return success;

	}
   /**
    * 
    * @author LXD 2015年8月25日 下午2:57:24
    * @Method: querySelectedGoods 
    * @Description: TODO 精选列表
    * @param userId
    * @param pageNum
    * @param pageSize
    * @return 
    * @see com.need.api.service.ops.OpsPositionService#querySelectedGoods(java.lang.String, java.lang.Integer, java.lang.Integer)
    */
	@Override
	public Message querySelectedGoods(String userId, Integer pageNum, Integer pageSize) {
		Message success =Message.success();
		 PageHelper.startPage(pageNum, pageSize);
		  List<HotGoodsVO> resultList=new ArrayList<HotGoodsVO>();
			Page<OpsSelectedPO> page = (Page<OpsSelectedPO>)  opsSelectedDAO.queyByPage();
			for(OpsSelectedPO selectedgoods:page.getResult()){
				GoodsMainPO goodsMain= goodsMainDAO.selectByPrimaryKey(selectedgoods.getGoodsId());
				//Addy liyongran 20150920 1.0版本过滤调保税仓商品
				if(goodsMain.getIsGlobal().equals("TRUE")){
					logger.info("the goods "+goodsMain.getGoodsId()+" is global goods  1.0 ignore .");
					continue;
				}
				//Addy liyongran 20150920 1.0版本过滤调保税仓商品 end
				
				 HotGoodsVO VO=new HotGoodsVO();  
				 if(goodsMain!=null){
				OpsHotGoodsVO goods=new OpsHotGoodsVO();
				BeanUtils.copyProperties(goodsMain, goods);
				goods.setTopicPicKey(goodsMain.getScenePicKey());
				VO.setGoods(goods);
				int needCount= needGoodsDAO.getNeedGoodsCount(selectedgoods.getGoodsId());
				NeedVO need =new NeedVO();
				need.setNeedCount(needCount);
				NeedGoodsPO needGoodsPO= needGoodsDAO.queryByParams(userId,selectedgoods.getGoodsId(),UserNeedStatus.NEED.code);
				if(needGoodsPO!=null){
					need.setIsNeed(NeedStatusEnum.NEED.code);
				}else{
					need.setIsNeed(NeedStatusEnum.CANCEL.code);
				}
				VO.setNeed(need);
				 resultList.add(VO);
				 }
				
			}
			success.addData("discountList", resultList);
			success.addData("totalCount", page.getTotal());
			return success;
		
	}
/**
 * 
 * @author LXD 2015年9月9日 下午5:55:25
 * @Method: queryHomeOps_v1_1 
 * @Description: TODO need1.1 首页运营位
 * @return 
 * @see com.need.api.service.ops.OpsPositionService#queryHomeOps_v1_1()
 */
@Override
public Message queryHomeOps_v1_1() {
	Message success = Message.success();
	 List<OpsPositionVO_V1_1> opsList =new ArrayList<OpsPositionVO_V1_1>();
		List<OpsPositionPO> opsPOList= opsPositionDAO.queryHomeOps();
		//封装对象
		getOpsList_v1_1(opsList, opsPOList);
		//无用对象置为null
		opsPOList=null;
		success.addData("opsList", opsList);
		return success;
}

private void getOpsList_v1_1(List<OpsPositionVO_V1_1> opsList, List<OpsPositionPO> opsPOList) {
	if (opsPOList != null && opsPOList.size() > 0) {
		for (OpsPositionPO opsPositionPO : opsPOList) {
			OpsPositionVO_V1_1 opsPositionVO = new OpsPositionVO_V1_1();
			opsPositionVO.setOpsType(opsPositionPO.getOpsType());
			if (OpsTypeEnum.GOODS.code.equals(opsPositionPO.getOpsType())) {
				// 如果是商品
				GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(opsPositionPO.getTypeId());
				if(goods!=null){
				opsPositionVO.setOpsId(goods.getGoodsId());
				}
			} else if (OpsTypeEnum.KOL.code.equals(opsPositionPO.getOpsType())) {
				// 如果是行家
				UserBasePO user = userBaseDAO.selectByPrimaryKey(opsPositionPO.getTypeId());
				if(user!=null){
				opsPositionVO.setOpsId(user.getUserId());
				}
			} else if (OpsTypeEnum.TOPIC.code.equals(opsPositionPO.getOpsType())) {
				// 如果是专题
				OpsTopicTemplatePO ops= opsTopicTemplateDAO.selectByPrimaryKey(Integer.parseInt(opsPositionPO.getTypeId()));
				if(ops!=null){
				opsPositionVO.setOpsId(ops.getTopicId().toString());
				opsPositionVO.setOpsName(opsPositionPO.getOpsName());
				opsPositionVO.setOpsPicKey(opsPositionPO.getOpsPicKey());
				opsPositionVO.setOpsWebUrl(ops.getVisitUrl());
				opsPositionVO.setTopicName(ops.getTopicTitle());
				}

			}else if(OpsTypeEnum.CHEAP.code.equals(opsPositionPO.getOpsType())){
				//如果是团便宜
				opsPositionVO.setOpsId(opsPositionPO.getTypeId());
				opsPositionVO.setOpsName(opsPositionPO.getOpsName());
				opsPositionVO.setOpsPicKey(opsPositionPO.getOpsPicKey());
				
			}else if(OpsTypeEnum.COUPON.code.equals(opsPositionPO.getOpsType())){
				opsPositionVO.setOpsId(opsPositionPO.getTypeId());
				opsPositionVO.setOpsName(opsPositionPO.getOpsName());
				opsPositionVO.setOpsPicKey(opsPositionPO.getOpsPicKey());
				opsPositionVO.setOpsWebUrl(constantsProConfig.getCouponUrl());
				
			}else if(OpsTypeEnum.DISCOUNT.code.equals(opsPositionPO.getOpsType())){
				// 如果是促销
				OpsTopicTemplatePO ops= opsTopicTemplateDAO.selectByPrimaryKey(Integer.parseInt(opsPositionPO.getTypeId()));
				if(ops!=null){
				opsPositionVO.setOpsId(ops.getTopicId().toString());
				opsPositionVO.setOpsName(opsPositionPO.getOpsName());
				opsPositionVO.setOpsPicKey(opsPositionPO.getOpsPicKey());
				opsPositionVO.setOpsWebUrl(ops.getVisitUrl());
				opsPositionVO.setTopicName(ops.getTopicTitle());
				}
				
			}

			opsList.add(opsPositionVO);

		}
	}
	
}
/**
 * 
 * @author LXD 2015年9月9日 下午6:31:00
 * @Method: queryShijianOps_v1_1 
 * @Description: TODO need1.1 世间运营位
 * @return 
 * @see com.need.api.service.ops.OpsPositionService#queryShijianOps_v1_1()
 */
@Override
public Message queryShijianOps_v1_1() {
	Message success = Message.success();
	 List<OpsPositionVO_V1_1> opsList =new ArrayList<OpsPositionVO_V1_1>();
		List<OpsPositionPO> opsPOList= opsPositionDAO.queryShijianOps();
		//封装对象
		getOpsList_v1_1(opsList, opsPOList);
		//无用对象置为null
		opsPOList=null;
		success.addData("opsList", opsList);
		return success;
}
/**
 * 
 * @author LXD 2015年9月9日 下午7:10:28
 * @Method: queryShijianScrollOps_v1_1 
 * @Description: TODO 世间SCROLL运营位
 * @return 
 * @see com.need.api.service.ops.OpsPositionService#queryShijianScrollOps_v1_1()
 */
@Override
public Message queryShijianScrollOps_v1_1() {
	Message success = Message.success();
	 List<OpsPositionVO_V1_1> opsList =new ArrayList<OpsPositionVO_V1_1>();
		List<OpsPositionPO> opsPOList= opsPositionDAO.queryShijianScrollOps();
		//封装对象
		getOpsList_v1_1(opsList, opsPOList);
		//无用对象置为null
		opsPOList=null;
		success.addData("opsList", opsList);
		return success;
}
/**
 * 
 * @author LXD 2015年10月22日 上午11:36:39
 * @Method: queryKolOps_v1_2 
 * @Description: TODO 行家banner运营位
 * @param kolCategoryId
 * @return 
 * @see com.need.api.service.ops.OpsPositionService#queryKolOps_v1_2(int)
 */
@Override
public Message queryKolOps_v1_2(int kolCategoryId) {
	Message success = Message.success();
	 List<OpsPositionVO_V1_1> opsList =new ArrayList<OpsPositionVO_V1_1>();
		List<OpsPositionPO> opsPOList= opsPositionDAO.queryKolOps(kolCategoryId);
		//封装对象
		getOpsList_v1_1(opsList, opsPOList);
		//无用对象置为null
		opsPOList=null;
		success.addData("opsList", opsList);
		return success;
}
/**
 * 
 * @author LXD 2015年10月22日 上午11:36:55
 * @Method: queryStartOps_v1_3 
 * @Description: TODO 	启动页运营位
 * @return 
 * @see com.need.api.service.ops.OpsPositionService#queryStartOps_v1_3()
 */
@Override
public Message queryStartOps_v1_3() {
	Message success = Message.success();
	 List<OpsPositionVO_V1_1> opsList =new ArrayList<OpsPositionVO_V1_1>();
		List<OpsPositionPO> opsPOList= opsPositionDAO.queryStartBannerOps();
		//封装对象
		getOpsList_v1_1(opsList, opsPOList);
		//无用对象置为null
		opsPOList=null;
		success.addData("opsList", opsList);
		return success;
}
/***
 * 
 * @author LXD 2015年10月22日 下午12:15:50
 * @Method: queryXinhuanOps_v1_3 
 * @Description: TODO 获取新欢banner运营位
 * @return 
 * @see com.need.api.service.ops.OpsPositionService#queryXinhuanOps_v1_3()
 */
@Override
public Message queryXinhuanOps_v1_3() {
	Message success = Message.success();
	 List<OpsPositionVO_V1_1> opsList =new ArrayList<OpsPositionVO_V1_1>();
		List<OpsPositionPO> opsPOList= opsPositionDAO.queryXinhuanBannerOps();
		//封装对象
		getOpsList_v1_1(opsList, opsPOList);
		//无用对象置为null
		opsPOList=null;
		success.addData("opsList", opsList);
		return success;
}

@Override
public Message queryXinhuanScroll_v1_3() {
	Message success = Message.success();
	 List<XinhuanOpsResultVO> opsList= opsXinhuanService.queryScrollOps();
	 for(XinhuanOpsResultVO xinhuan:opsList){
		 xinhuan.setOpsType(OpsTypeEnum.GOODS.code);
	 }	
	 success.addData("opsList", opsList);
	 return success;
}

@Override
public Message queryXinhuanScroll_v2_0() {
	Message success = Message.success();
	 List<OpsPositionVO_V1_1> opsList =new ArrayList<OpsPositionVO_V1_1>();
		List<OpsPositionPO> opsPOList= opsPositionDAO.queryXinhuanScrollOps_2_0();
		//封装对象
		getOpsList_v1_1(opsList, opsPOList);
		//无用对象置为null
		opsPOList=null;
		success.addData("opsList", opsList);
		return success;
}


	
}
