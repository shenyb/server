package com.need.service.bops.template.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.need.dao.bops.template.BopsTemplateDAO;
import com.need.domain.common.enums.RecordStatusEnums;
import com.need.domain.po.bops.template.BopsTemplate;
import com.need.domain.pub.Page;
import com.need.service.bops.template.TemplateService;

/**
 * 
 * <p></p>
 * @author peiboli 2015年9月10日 下午4:59:15
 * @ClassName TemplateServiceImpl
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class TemplateServiceImpl implements TemplateService{

	@Autowired
	BopsTemplateDAO bopsTemplateDAO;
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:49:12
	 * @Method: saveTemplate 
	 * @Description: TODO添加专题模板
	 * @param bopsTemplate 
	 * @see com.need.bops.service.template.TemplateService#saveTemplate(com.need.bops.dao.jdbc.bops.template.po.BopsTemplate)
	 */
	@Override
	public void saveTemplate(BopsTemplate bopsTemplate) {
		// TODO Auto-generated method stub
		if(bopsTemplate.getTemplateCode()==null){
			bopsTemplate.setTemplateCode("");
		}
		bopsTemplate.setRecordStatus(RecordStatusEnums.VALID.code);
		bopsTemplateDAO.insert(bopsTemplate);
	}
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:49:48
	 * @Method: delete 
	 * @Description: TODO删除模板
	 * @param templateId 
	 * @see com.need.bops.service.template.TemplateService#delete(java.lang.String)
	 */
	@Override
	public void delete(String templateId) {
		// TODO Auto-generated method stub
		bopsTemplateDAO.deleteById(templateId);
	}
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:50:00
	 * @Method: gettemplateList 
	 * @Description: TODO获得模板列表
	 * @param templatePage
	 * @return 
	 * @see com.need.bops.service.template.TemplateService#gettemplateList(com.need.bops.pub.Page)
	 */
	@Override
	public List<BopsTemplate> gettemplateList(Page templatePage) {
		// TODO Auto-generated method stub
		int total = bopsTemplateDAO.count();
		templatePage.setTotal(total);
		List<BopsTemplate> templatelist= bopsTemplateDAO.queryByPage(templatePage);
		
		return templatelist;
	}
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:50:34
	 * @Method: updateTemplate 
	 * @Description: TODO编辑模板
	 * @param bopsTemplate 
	 * @see com.need.bops.service.template.TemplateService#updateTemplate(com.need.bops.dao.jdbc.bops.template.po.BopsTemplate)
	 */
	@Override
	public void updateTemplate(BopsTemplate bopsTemplate) {
		// TODO Auto-generated method stub
		bopsTemplateDAO.updateById(bopsTemplate);
	}
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:50:49
	 * @Method: checkTemplateName 
	 * @Description: TODO校验名称是否存在着
	 * @param templateName
	 * @return 
	 * @see com.need.bops.service.template.TemplateService#checkTemplateName(java.lang.String)
	 */
	@Override
	public Boolean checkTemplateName(String templateName) {
		// TODO Auto-generated method stub
		int isExist = bopsTemplateDAO.countByTemplateName(templateName);
		if(isExist==1){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	

}
