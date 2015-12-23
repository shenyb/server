package com.need.share.service.template.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.need.share.dao.jdbc.bops.template.BopsTemplateDAO;
import com.need.share.dao.jdbc.bops.template.po.BopsTemplatePO;
import com.need.share.service.template.TemplateService;
import com.need.share.web.controller.template.vo.TemplateBaseVO;

@Service
public class TemplateServiceImpl implements TemplateService{
	
	
    public BopsTemplateDAO bopsTemplateDAO;
	
	
	@Override
	public List<TemplateBaseVO> queryTemplate() {
		/** TODO Auto-generated method stub*/
		return null;
	}
	@Override
	public TemplateBaseVO getTempalteById(int id) {
		/** TODO Auto-generated method stub*/
		return null;
	}
	@Override
	public int updateTempalteById(int id) {
		/** TODO Auto-generated method stub*/
		return 0;
	}
	@Override
	public int deleteTempalteById(int id) {
		/** TODO Auto-generated method stub*/
		return 0;
	}
	@Override
	public int saveTemplate(String htmlContent, String path) {
		/** TODO Auto-generated method stub*/
		return 0;
	}

}
