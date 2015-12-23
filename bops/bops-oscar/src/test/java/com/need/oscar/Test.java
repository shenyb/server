package com.need.oscar;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.need.dao.bops.template.BopsTopicTemplateDAO;
import com.need.domain.po.bops.template.BopsTopicTemplatePO;
import com.need.domain.vo.template.TemplateResultVO;


public class Test {
	private static final Logger logger = Logger.getLogger(Test.class);
	
	 private ApplicationContext context;

	 @Before
	 public void before(){
		 context=new ClassPathXmlApplicationContext("applicationContext.xml");
	 }
	 
	 @org.junit.Test
	 public void testIsert(){
		 BopsTopicTemplateDAO  dao= (BopsTopicTemplateDAO) context.getBean("bopsTopicTemplateDAO");
		 TemplateResultVO vo= dao.getByTempalteId(26);
		 BopsTopicTemplatePO bopsTemplatePO=new  BopsTopicTemplatePO();
		 BeanUtils.copyProperties(vo,bopsTemplatePO);
		 bopsTemplatePO.setTopicId(323232);
		 dao.insert(bopsTemplatePO);
		 
		 
	 }
	 
	
	
	
}
