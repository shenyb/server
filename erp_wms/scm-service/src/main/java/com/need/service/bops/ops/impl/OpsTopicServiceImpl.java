package com.need.service.bops.ops.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.ops.OpsTopicDAO;
import com.need.dao.bops.ops.BopsOpsTopicDAO;
import com.need.domain.common.enums.AuditStatusEnums;
import com.need.domain.po.bops.ops.BopsOpsTopic;
import com.need.domain.vo.ops.PageTopicVO;
import com.need.service.bops.ops.OpsTopicService;

@Service
public class OpsTopicServiceImpl implements OpsTopicService{
	@Autowired
	 private BopsOpsTopicDAO bopsOpsTopicDAO;
	
	@Autowired
	 private OpsTopicDAO opsTopicDAO;
 	
	@Override
	public BopsOpsTopic addOpsTopic(BopsOpsTopic bopsOpsTopic) {
		// TODO Auto-generated method stub
		bopsOpsTopic.setTopicStatus(AuditStatusEnums.WAIT_AUDIT.code);
		bopsOpsTopicDAO.insertTopic(bopsOpsTopic);	
		return bopsOpsTopic;
	}
	
	@Override
	public void deleteOpsTopic(String topicId) {
		// TODO Auto-generated method stub
		bopsOpsTopicDAO.deleteByTopicId(topicId);
		opsTopicDAO.deleteApiTopic(topicId);
		
	}
	@Override
	@Transactional
	public void updateOpsTopic(BopsOpsTopic bopsOpsTopic) {
		// TODO Auto-generated method stub
		bopsOpsTopicDAO.updateByTopicIdSelective(bopsOpsTopic);
	}
	@Override
	public List<BopsOpsTopic> getOpsTopicList(PageTopicVO opsTopicPage) {
		// TODO Auto-generated method stub
		opsTopicPage.setTotal(bopsOpsTopicDAO.selectCount(opsTopicPage));
		List<BopsOpsTopic> BopsOpsTopicList = bopsOpsTopicDAO.queryByPage(opsTopicPage);
		return BopsOpsTopicList;
	}
	@Override
	public void deleteApiOpsTopic(String topicId) {
		// TODO Auto-generated method stub
		opsTopicDAO.deleteApiTopic(topicId);
	}

	@Override
	public void updateApiOpsTopic(BopsOpsTopic bopsOpsTopic) {
		// TODO Auto-generated method stub
		opsTopicDAO.updateByPrimaryKeySelective(bopsOpsTopic);
	}

	@Override
	public BopsOpsTopic getTopic(String topicId) {
		// TODO Auto-generated method stub
		return bopsOpsTopicDAO.selectByTopicId(Integer.parseInt(topicId));
		
	}

	@Override
	public void auditOpsTopic(BopsOpsTopic bopsOpsTopic) {
		// TODO Auto-generated method stub
		if(AuditStatusEnums.SUCCESS.code.equals(bopsOpsTopic.getTopicStatus())){
			opsTopicDAO.deleteApiTopic(String.valueOf(bopsOpsTopic.getTopicId()));
			bopsOpsTopicDAO.updateByTopicId(bopsOpsTopic);
			opsTopicDAO.insertTopic(bopsOpsTopic);
			
			return;
			}else if(AuditStatusEnums.FAIL.code.equals(bopsOpsTopic.getTopicStatus())){
				bopsOpsTopicDAO.updateByTopicId(bopsOpsTopic);
				return;
			}
	}

}
