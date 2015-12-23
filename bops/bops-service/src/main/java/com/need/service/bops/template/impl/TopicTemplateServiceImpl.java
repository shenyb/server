package com.need.service.bops.template.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.ops.OpsPositionDAO;
import com.need.dao.api.ops.TopicCategoryDAO;
import com.need.dao.api.template.OpsTopicTemplateDAO;
import com.need.dao.bops.ops.BopsOpsDAO;
import com.need.dao.bops.ops.BopsTopicCategoryDAO;
import com.need.dao.bops.template.BopsTopicTemplateDAO;
import com.need.domain.common.enums.AuditStatusEnums;
import com.need.domain.common.enums.RecordStatusEnums;
import com.need.domain.po.api.template.OpsTopicTemplate;
import com.need.domain.po.bops.ops.BopsOpsTopicCategory;
import com.need.domain.po.bops.template.BopsTopicTemplatePO;
import com.need.domain.pub.Message;
import com.need.domain.vo.template.TemplatePageParamVO;
import com.need.domain.vo.template.TemplateParamVO;
import com.need.domain.vo.template.TemplateResultVO;
import com.need.service.bops.template.TopicTemplateService;
import com.need.utils.StringUtil;

/**
 * <p></p>
 * @author Rylan 2015年9月8日 下午11:44:47
 * @ClassName TemplateServiceImpl
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月9日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class TopicTemplateServiceImpl implements TopicTemplateService{
	
	@Autowired
	private BopsTopicTemplateDAO bopsTemplateDAO;
	@Autowired
	private OpsTopicTemplateDAO opsTopicTemplateDAO;
	@Autowired
	private BopsTopicCategoryDAO bopsTopicCategoryDAO;
	@Autowired
	private TopicCategoryDAO topicCategoryDAO;
	@Autowired
	private BopsOpsDAO bopsOpsDAO;
	@Autowired
	private OpsPositionDAO opsPositionDAO;
	
	/**
	 * @author Rylan 2015年9月9日 下午11:45:01
	 * @Method: saveTemplate 
	 * @Description: TODO
	 * @param templateParamVO
	 * @return 
	 * @see com.need.bops.service.template.TopicTemplateService#saveTemplate(com.need.bops.web.controller.template.vo.TemplateParamVO)
	 */
	@Transactional
	@Override
	public Message saveTemplate(TemplateParamVO templateParamVO) {
		// TODO Auto-generated method stub
		Message success = Message.success();

		BopsTopicTemplatePO bopsTemplatePO=new  BopsTopicTemplatePO();	
		BeanUtils.copyProperties(templateParamVO,bopsTemplatePO);
		if(templateParamVO.getGoodsCodes()!=null){
			String goodsCodes= StringUtil.arrayToFormatString(templateParamVO.getGoodsCodes(), ",");
			bopsTemplatePO.setGoodsCodes(goodsCodes);
		}
		bopsTemplatePO.setAuditStatus(AuditStatusEnums.WAIT_AUDIT.code);
		bopsTemplatePO.setRecordStatus(RecordStatusEnums.VALID.code);		
		bopsTemplateDAO.insert(bopsTemplatePO); //保存bops
		

		success.addData("visitUrl", templateParamVO.getVisitUrl());	
		return success;
	}
	
	@Override
	public List<TemplateResultVO> queryTemplate(TemplatePageParamVO param) {
		/** TODO Auto-generated method stub*/
		param.setTotal(bopsTemplateDAO.countTopicCodePage(param));
		List<TemplateResultVO> list= bopsTemplateDAO.queryTempalte(param);
		for(TemplateResultVO templateResultVO:list){
			BopsOpsTopicCategory topicCategory= bopsTopicCategoryDAO.getByTopicId(templateResultVO.getTopicId());
			if(topicCategory!=null){
				templateResultVO.setCategoryName(topicCategory.getTopicCategoryName());
			}		
		}

		return list;
	}
	@Override
	public TemplateResultVO getTempalteById(int id) {
		/** TODO Auto-generated method stub*/
		TemplateResultVO templateResultVO=bopsTemplateDAO.getByTempalteId(id);
	    String[] goodsCodes= StringUtil.stringToArrayBySplit(templateResultVO.getGoodsCodesString());
	    templateResultVO.setGoodsCodes(goodsCodes);
		return templateResultVO;
	}
	@Transactional
	@Override
	public Message updateTempalteById(TemplateParamVO vo) {
		/** TODO Auto-generated method stub*/
		Message success = Message.success();
		if(vo.getGoodsCodes()!=null){
			String goodsCodes= StringUtil.arrayToFormatString(vo.getGoodsCodes(), ",");
			vo.setGoodsCodesString(goodsCodes);
		}
		bopsTemplateDAO.updateContentById(vo);//修改bops库
		
		OpsTopicTemplate opsTopicTemplate=new OpsTopicTemplate();
		BeanUtils.copyProperties(vo,opsTopicTemplate);
		if(vo.getGoodsCodes()!=null){
			String goodsCodes= StringUtil.arrayToFormatString(vo.getGoodsCodes(), ",");
			opsTopicTemplate.setGoodsCodes(goodsCodes);
		}
		opsTopicTemplateDAO.updateById(opsTopicTemplate);//修改前台库			
		success.addData("visitUrl", vo.getVisitUrl());
		return success;
		
	}
	@Transactional
	@Override
	public int deleteTempalteById(int id) {
		/** TODO Auto-generated method stub*/
		int updataRecored=0;
		updataRecored=bopsTemplateDAO.deleteById(id);
		updataRecored=opsTopicTemplateDAO.deleteById(id);
		/***
		 * 删除和此专题关联的数据
		 */
		deleteRelationId(id);
		return updataRecored;
	}
	/**
	 * 
	 * @author LXD 2015年9月16日 下午4:03:09
	 * @Method: deleteRelationId 
	 * @Description: TODO 删除与专题相关联的数据
	 * @param id 
	 * @throws
	 */
	private void deleteRelationId(int id) {
		bopsTopicCategoryDAO.deleteByTopicId(id);
		topicCategoryDAO.deleteByTopicId(id);
		bopsOpsDAO.deleteByTypeId(id+"");
		opsPositionDAO.deleteByTypeId(id+"");
		
	}

	@Transactional
	@Override
	public Message auditTemplate(TemplateParamVO templateParamVO) {
		// TODO Auto-generated method stub
		Message success = Message.success();		
		if(templateParamVO.getAuditStatus().equals(AuditStatusEnums.SUCCESS.code)){//审核成功查询前台库		
			OpsTopicTemplate opsTopicTemplate=new OpsTopicTemplate();
			BeanUtils.copyProperties(templateParamVO,opsTopicTemplate);
			if(templateParamVO.getGoodsCodes()!=null){
				String goodsCodes= StringUtil.arrayToFormatString(templateParamVO.getGoodsCodes(), ",");
				opsTopicTemplate.setGoodsCodes(goodsCodes);
			}
			opsTopicTemplateDAO.insert(opsTopicTemplate);//保存portal
		}		
		BopsTopicTemplatePO bopsTopicTemplatePO=new BopsTopicTemplatePO();
		BeanUtils.copyProperties(templateParamVO,bopsTopicTemplatePO);
		bopsTemplateDAO.updateById(bopsTopicTemplatePO);

		return success;
	}
	
//	@Transactional("bops_txManager")
//	public Message test(int id) {
//		// TODO Auto-generated method stub
//		Message success = Message.success();
//
//		BopsTopicTemplatePO po=bopsTemplateDAO.getByTempalteId2(id);
//		po.setTopicContents("222");
//		bopsTemplateDAO.updateById(po);
//		
//       System.out.println("---------------------");
//       bopsTemplateDAO.insert(po);
//		
//		return success;
//	}

}
