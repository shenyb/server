package com.need.service.bops.template;

import java.util.List;

import com.need.domain.po.bops.template.BopsTemplate;
import com.need.domain.pub.Page;
/**
 * 
 * <p></p>
 * @author peiboli 2015年9月10日 下午4:57:52
 * @ClassName TemplateService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */
public interface TemplateService {

	void saveTemplate(BopsTemplate bopsTemplate);

	void delete(String templateId);

	List<BopsTemplate> gettemplateList(Page templatePage);

	void updateTemplate(BopsTemplate bopsTemplate);

	Boolean checkTemplateName(String templateName);
	
	
}
