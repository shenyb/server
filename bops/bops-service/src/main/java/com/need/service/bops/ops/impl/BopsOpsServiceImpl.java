package com.need.service.bops.ops.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.biz.utils.MD5Util;
import com.need.dao.api.goods.GoodsMainDAO;
import com.need.dao.api.kol.UserKolDAO;
import com.need.dao.api.ops.OpsPositionDAO;
import com.need.dao.api.template.OpsTopicTemplateDAO;
import com.need.dao.bops.ops.BopsOpsDAO;
import com.need.dao.bops.xinhuan.BopsOpsMainDAO;
import com.need.domain.common.enums.CheckStatusEnums;
import com.need.domain.common.enums.OpsPositionEnums;
import com.need.domain.common.enums.OpsPositionTypeEnum;
import com.need.domain.po.api.goods.GoodsMainPO;
import com.need.domain.po.api.kol.UserKol;
import com.need.domain.po.api.ops.OpsPositionPO;
import com.need.domain.po.api.template.OpsTopicTemplate;
import com.need.domain.po.bops.ops.BopsOps;
import com.need.domain.po.bops.xinhuan.BopsOpsMain;
import com.need.domain.pub.Message;
import com.need.domain.vo.ops.PageBopsOpsVO;

import com.need.service.bops.ops.BopsOpsService;
@Service
public class BopsOpsServiceImpl implements BopsOpsService {
    
	@Autowired
	private BopsOpsDAO bopsOpsDAO;
	@Autowired
	private OpsPositionDAO opsPositionDAO;
	@Autowired
	private BopsOpsMainDAO bopsOpsMainDAO;
	@Autowired
	private UserKolDAO userKolDAO;
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private OpsTopicTemplateDAO TopicTemplateDAO;
	
	private static final Logger logger = Logger.getLogger(BopsOpsServiceImpl.class);
	/**
	 *  
	 * @author LXD 2015年8月19日 下午2:55:08
	 * @Method: addBopsOps 
	 * @Description: TODO 插入运营位
	 * @param ops 
	 * @see com.need.service.bops.ops.BopsOpsService#addBopsOps(com.need.bops.dao.jdbc.bops.ops.po.BopsOps)
	 */
	@Override
	public void addBopsOps(BopsOps ops) {
		bopsOpsDAO.insert(ops);
		

	}
   /**
    * 
    * @author LXD 2015年8月19日 下午2:55:25
    * @Method: getBopsOpsById 
    * @Description: TODO 根据ID获取运营位信息
    * @param opsId
    * @return 
    * @see com.need.service.bops.ops.BopsOpsService#getBopsOpsById(java.lang.String)
    */
	@Override
	public BopsOps getBopsOpsById(String opsId) {
		return bopsOpsDAO.getById(opsId);
	}
    /**
     * 
     * @author LXD 2015年8月19日 下午2:55:48
     * @Method: updateBopsOps 
     * @Description: TODO 更新运营位信息
     * @param ops 
     * @see com.need.service.bops.ops.BopsOpsService#updateBopsOps(com.need.bops.dao.jdbc.bops.ops.po.BopsOps)
     */
	@Override
	@Transactional
	public void updateBopsOps(BopsOps ops) {
		bopsOpsDAO.update(ops);
	}

	@Override
	@Transactional
	public void deleteBopsOpsById(String opsId) {
		bopsOpsDAO.deleteById(opsId);
		deletePortalOps(opsId);
		

	}
  /**
   * 
   * @author LXD 2015年8月19日 下午2:57:47
   * @Method: getPageOfBopsOps 
   * @Description: TODO 运营位分页
   * @param page
   * @return 
   * @see com.need.service.bops.ops.BopsOpsService#getPageOfBopsOps(com.need.bops.web.controller.vo.ops.PageBopsOpsVO)
   */
	@Override
	public List<BopsOps> getPageOfBopsOps(PageBopsOpsVO page) {
		page.setTotal(bopsOpsDAO.getCountById(page));
		List<BopsOps> list=bopsOpsDAO.queryByPage(page);
		for(BopsOps ops:list){
			BopsOpsMain opsPosition = bopsOpsMainDAO.selectByPrimaryKey(ops.getOpsNumber());
			if(opsPosition!=null){
			ops.setOpsNumber(opsPosition.getOpsName());
			}
		}
		return list;
	}
  /**
   * 
   * @author LXD 2015年8月19日 下午2:53:21
   * @Method: insertOrEditPortalOps 
   * @Description: TODO 插入或修改前端对象
   * @param opsPositionP 
   * @throws
   */
	public void insertOrEditPortalOps(OpsPositionPO opsPositionP) {
		OpsPositionPO opsPO=opsPositionDAO.getById(opsPositionP.getOpsId());
		if(opsPO!=null){
			opsPositionDAO.update(opsPositionP);	
		}else{
		opsPositionDAO.insert(opsPositionP);
		}
	}
   /**
    * 
    * @author LXD 2015年8月19日 下午2:58:07
    * @Method: auditBopsOps 
    * @Description: TODO 审核运营位：插入或更新前端信息
    * @param ops 
    * @see com.need.service.bops.ops.BopsOpsService#auditBopsOps(com.need.bops.dao.jdbc.bops.ops.po.BopsOps)
    */
	@Override
	@Transactional
	public void auditBopsOps(BopsOps ops) {
		//更新运营为信息
		updateBopsOps(ops);
		//如果审核通过，添加到portal数据库
		if(ops.getAuditStatus()!=null&&ops.getAuditStatus().equals("SUCCESS")){
			OpsPositionPO opsPosition=convert(ops);
			insertOrEditPortalOps(opsPosition);
		}
		
	}
    /**
     * 
     * @author LXD 2015年8月17日 下午7:31:03
     * @Method: convert 
     * @Description: TODO 封装前端对象
     * @param ops
     * @return 
     * @throws
     */
	private OpsPositionPO convert(BopsOps ops) {
		OpsPositionPO opsPosition =new OpsPositionPO();
		opsPosition.setOpsId(ops.getOpsId());
		opsPosition.setOpsNumber(ops.getOpsNumber());
		opsPosition.setOpsPicKey(ops.getOpsPicKey());
		opsPosition.setOpsType(ops.getOpsType());
		opsPosition.setTypeId(ops.getTypeId());
		opsPosition.setCategoryId(ops.getCategoryId());
		opsPosition.setTopicScore(ops.getTopicScore());
		opsPosition.setEffDate(ops.getEffDate());
		opsPosition.setExpDate(ops.getExpDate());
		return opsPosition;
	}
   /**
    * 
    * @author LXD 2015年8月19日 下午2:58:56
    * @Method: deletePortalOps 
    * @Description: TODO 删除前端对象
    * @param opsId 
    * @see com.need.service.bops.ops.BopsOpsService#deletePortalOps(java.lang.String)
    */
	@Override
	public void deletePortalOps(String opsId) {
		opsPositionDAO.deleteById(opsId);
	}
	/**
	 * 
	 * @author LXD 2015年9月16日 下午2:51:06
	 * @Method: getOpsByopsNumber 
	 * @Description: TODO need1.1根据ops位ID查询运营位和专题的关联信息
	 * @param opsId
	 * @return 
	 * @throws
	 */
	@Override
	public BopsOps getOpsByopsNumber(String opsId) {
		BopsOps ops=bopsOpsDAO.getByOpsNumber(opsId);
		if(ops!=null){
		if (ops.getEffDate() != null && ops.getExpDate() != null) {
			try {
				ops.setEffDateString(DateUtil.formatDate(ops.getEffDate(), "yyyy-MM-dd HH:mm:ss"));
				ops.setExpDateString(DateUtil.formatDate(ops.getExpDate(), "yyyy-MM-dd HH:mm:ss"));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		}
		return ops;
	}

	/***
	 * 
	 * @author LXD 2015年9月14日 上午11:42:01
	 * @Method: editOps
	 * @Description: TODO 保存或修改运营位
	 * @param bopsOps
	 * @return
	 * @see com.need.service.bops.ops.BopsOpsService#editOps(com.need.bops.dao.jdbc.bops.ops.po.BopsOps)
	 */
	@Transactional
	@Override
	public Message editOps(BopsOps bopsOps) {
		Message message = Message.success();
		if(StringUtils.isNotBlank(bopsOps.getOpsType())){
			if(OpsPositionTypeEnum.CHEAP.code.equals(bopsOps.getOpsType())){
			 bopsOps.setTypeId(OpsPositionTypeEnum.CHEAP.code);
			}
			if(OpsPositionTypeEnum.COUPON.code.equals(bopsOps.getOpsType())){
				bopsOps.setTypeId(OpsPositionTypeEnum.COUPON.code);					
			}
		}
		/**
		 * 校验参数
		 */
		if (StringUtils.isBlank(bopsOps.getOpsType()) || StringUtils.isBlank(bopsOps.getOpsNumber())
				|| StringUtils.isBlank(bopsOps.getTypeId())) {
			Message errorMessage = Message.error(3009);
			return errorMessage;

		}

        /***
         * 增加和修改 审核状态都为待审核
         */
		bopsOps.setAuditStatus(CheckStatusEnums.WAIT.code);
		/**
		 * 根据不同的添加类型校验类型Id是否正确
		 */
		switch (bopsOps.getOpsType()) {
		case "KOL":
			UserKol user = userKolDAO.selectByPrimaryKey(bopsOps.getTypeId());
			if (user == null) {
				Message errorMessage = Message.error(3004);
				return errorMessage;
			}
			break;
		case "GOODS":
			GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(bopsOps.getTypeId());
			if (goods == null) {
				Message errorMessage = Message.error(3005);
				return errorMessage;
			}

			break;
		case "TOPIC": 
			// OpsTopicPO topic =
			// opsTopicDAO.getById(Integer.parseInt(bopsOps.getTypeId()));
			OpsTopicTemplate tmplate = TopicTemplateDAO.selectById(Integer.parseInt(bopsOps.getTypeId()));
			if (tmplate == null) {
				Message errorMessage = Message.error(5024);
				return errorMessage;
			}
		case "DISCOUNT": 
		// OpsTopicPO topic =
		// opsTopicDAO.getById(Integer.parseInt(bopsOps.getTypeId()));
		OpsTopicTemplate tmplateDISCOUNT = TopicTemplateDAO.selectById(Integer.parseInt(bopsOps.getTypeId()));
		if (tmplateDISCOUNT == null) {
			Message errorMessage = Message.error(5024);
			return errorMessage;
		}
			break;
		}
		/**
		 * opsID不为空，为编辑
		 */
		if (StringUtils.isNotBlank(bopsOps.getOpsId())) {
			/**
			 * 校验是否关联过
			 */
			BopsOps ops= bopsOpsDAO.getById(bopsOps.getOpsId());
			if(!bopsOps.getTypeId().equals(ops.getTypeId())){
				/**
				 * 校验此类型是否已添加
				 */
 /*               if(!OpsPositionEnums.START_BANNER.code.equals(bopsOps.getOpsPosition())){
				BopsOps opsByTypeId = bopsOpsDAO.queryByTypeId(bopsOps.getTypeId());
				if (opsByTypeId != null) {
					Message errorMessage = Message.error(3007);
					return errorMessage;
				}
                 }*/
				BopsOps checkOps = bopsOpsDAO.queryByParams(bopsOps.getTypeId(), bopsOps.getOpsNumber());
				if (checkOps != null) {
					Message errorMessage = Message.error(3007);
					return errorMessage;
				}
			}
			bopsOpsDAO.update(bopsOps);
		} else {
			/**
			 * 校验此类型是否已添加
			 */
/*			if(!OpsPositionEnums.START_BANNER.code.equals(bopsOps.getOpsPosition())){
			BopsOps opsByTypeId = bopsOpsDAO.queryByTypeId(bopsOps.getTypeId());
			if (opsByTypeId != null) {
				Message errorMessage = Message.error(3007);
				return errorMessage;
			}
			 }*/
			/***
			 * 否则是添加，校验是否已经关联过
			 */
			BopsOps checkOps = bopsOpsDAO.queryByParams(bopsOps.getTypeId(), bopsOps.getOpsNumber());
			if (checkOps != null) {
				Message errorMessage = Message.error(3007);
				return errorMessage;
			}
			bopsOps.setOpsId(generateOpsId(bopsOps.getTypeId(), bopsOps.getOpsNumber()));
			bopsOpsDAO.insert(bopsOps);
		}
		message.addData("object", bopsOps);
		return message;

	}
  /***
   * 
   * @author LXD 2015年9月16日 下午2:58:06
   * @Method: generateOpsId 
   * @Description: TODO 根据类型Id和opsId 关联Id
   * @param topicId
   * @param opsId
   * @return 
   * @throws
   */
	public static String generateOpsId(String topicId, String opsId) {
		String SPLIT_SIGN = "|";
		SimpleDateFormat DF_LONG_DATETIME = new SimpleDateFormat("yyyyMMddHHmm");
		String dateFormat = DF_LONG_DATETIME.format(Calendar.getInstance().getTime());
		long timefator = Calendar.getInstance().getTimeInMillis();
		String md5Random = MD5Util.MD5Encode(topicId + SPLIT_SIGN + timefator + SPLIT_SIGN + opsId).substring(0, 13);

		return dateFormat + md5Random;
	}
	
}
