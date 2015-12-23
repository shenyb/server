package com.need.service.bops.ops.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.ops.OpsTopicCategoryDAO;
import com.need.dao.bops.ops.BopsOpsTopicCategoryDAO;
import com.need.dao.bops.ops.BopsTopicCategoryDAO;
import com.need.domain.common.enums.AuditStatusEnums;
import com.need.domain.po.bops.ops.BopsOpsTopicCategory;
import com.need.domain.pub.Message;
import com.need.service.bops.ops.OpsTopicCategoryService;
@Service
public class OpsTopicCategoryServiceImpl implements OpsTopicCategoryService{

	@Autowired
	BopsOpsTopicCategoryDAO bopsOpsTopicCategoryDAO;
	@Autowired
	OpsTopicCategoryDAO opsTopicCategoryDAO;
	@Autowired
	BopsTopicCategoryDAO bopsTopicCategoryDAO;
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:33:18
	 * @Method: addTopicCategory 
	 * @Description: TODO添加专题分类
	 * @param topicCategory
	 * @return 
	 * @see com.need.service.bops.ops.OpsTopicCategoryService#addTopicCategory(com.need.bops.dao.jdbc.bops.ops.po.BopsOpsTopicCategory)
	 */
	@Override
	@Transactional
	public BopsOpsTopicCategory addTopicCategory(BopsOpsTopicCategory topicCategory) {
		// TODO Auto-generated method stub
		topicCategory.setAuditStatus(AuditStatusEnums.WAIT_AUDIT.code);
		bopsOpsTopicCategoryDAO.insert(topicCategory);

		topicCategory.setCreateTime(Calendar.getInstance().getTime());
		topicCategory.setUpdateTime(Calendar.getInstance().getTime());
		return topicCategory;
	}
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:35:38
	 * @Method: removeById 
	 * @Description: TODO根据id删除专题分类
	 * @param topicCategoryId
	 * @return 
	 * @see com.need.service.bops.ops.OpsTopicCategoryService#removeById(int)
	 */
	@Override
	@Transactional
	public Boolean removeById(int topicCategoryId) {
		// TODO Auto-generated method stub
		int isSuccess= bopsOpsTopicCategoryDAO.deleteById(topicCategoryId);
		opsTopicCategoryDAO.deleteById(topicCategoryId);
		if(isSuccess==1){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:36:14
	 * @Method: update 
	 * @Description: TODO更新专题分类
	 * @param topicCategory 
	 * @see com.need.service.bops.ops.OpsTopicCategoryService#update(com.need.bops.dao.jdbc.bops.ops.po.BopsOpsTopicCategory)
	 */
	@Override
	@Transactional
	public void update(BopsOpsTopicCategory topicCategory) {
		// TODO Auto-generated method stub
		topicCategory.setAuditStatus(AuditStatusEnums.WAIT_AUDIT.code);
		bopsOpsTopicCategoryDAO.update(topicCategory);
//		opsTopicCategoryDAO.update(topicCategory);
	}
	@Override
	@Transactional
	public Message auditOpsTopic(BopsOpsTopicCategory topicCategory) {
		// TODO Auto-generated method stub
		Message message = Message.success();
//		if(!CategoryPositionEnum.XINHUAN.code.equals(topicCategory.getTopicCategoryPosition())){
//		int size= bopsTopicCategoryDAO.getCountByCategoryId(topicCategory.getTopicCategoryId());
//		if(size<4){
//			return Message.error(5021);
//		}
//		}
		if(topicCategory.getAuditStatus().equals(AuditStatusEnums.SUCCESS.code)){
			int isExist= opsTopicCategoryDAO.countById(topicCategory.getTopicCategoryId());
			if(isExist==0){
			opsTopicCategoryDAO.insert(topicCategory);	
			}else{
			opsTopicCategoryDAO.update(topicCategory);	
			}
		}
		    bopsOpsTopicCategoryDAO.update(topicCategory);
		return message;
	}

}
