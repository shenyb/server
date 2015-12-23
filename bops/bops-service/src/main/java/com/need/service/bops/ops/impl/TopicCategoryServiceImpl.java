package com.need.service.bops.ops.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.ops.TopicCategoryDAO;
import com.need.dao.bops.ops.BopsOpsTopicCategoryDAO;
import com.need.dao.bops.ops.BopsTopicCategoryDAO;
import com.need.domain.common.enums.CategoryPositionEnum;
import com.need.domain.po.api.ops.TopicCategory;
import com.need.domain.po.bops.ops.BopsOpsTopicCategory;
import com.need.domain.po.bops.ops.BopsTopicCategory;
import com.need.domain.pub.Message;
import com.need.domain.vo.ops.TopicCategroyVO;
import com.need.service.bops.ops.TopicCategoryService;
import com.need.service.constant.BizReturnCode;

@Service
public class TopicCategoryServiceImpl implements TopicCategoryService {
	@Autowired
	private BopsTopicCategoryDAO bopsTopicCategoryDAO;
	@Autowired
	private TopicCategoryDAO topicCategoryDAO;
	@Autowired
	private BopsOpsTopicCategoryDAO BopsOpsTopicCategoryDAO;
	
	@Override
	@Transactional
	public Message addCategory(TopicCategroyVO bopsTopicCategoryVO,Message message) {
		

		
		/**
		 * 校验该专题是否已和该专题关联
		 */
		BopsTopicCategory checkTopicCategory=bopsTopicCategoryDAO.getByParams(bopsTopicCategoryVO.getTopicId(),bopsTopicCategoryVO.getTopicCategoryId());
		if(checkTopicCategory!=null){
			return Message.error(BizReturnCode.TOPIC_CATEGORY_CHECK);	
		}
		BopsTopicCategory bopsTopicCategory =new BopsTopicCategory();
		BeanUtils.copyProperties(bopsTopicCategoryVO, bopsTopicCategory);
		//BopsTopicCategory bopsTopicCategory =convertObject(bopsTopicCategoryVO);
		bopsTopicCategoryDAO.insert(bopsTopicCategory);
		TopicCategory topicCategory= new TopicCategory();
        BeanUtils.copyProperties(bopsTopicCategory, topicCategory);
		topicCategoryDAO.insert(topicCategory);
		return message;
	}

     /**
      * 
      * @author LXD 2015年9月10日 下午4:39:08
      * @Method: getBopsOpsById 
      * @Description: TODO 根据ID查询关联数据
      * @param relationId
      * @return 
      * @see com.need.service.bops.ops.TopicCategoryService#getBopsOpsById(java.lang.String)
      */
	@Override
	public BopsTopicCategory getBopsOpsById(int relationId) {
		return bopsTopicCategoryDAO.getById(relationId);
	}
	/**
	 * 
	 * @author LXD 2015年9月10日 下午6:35:52
	 * @Method: queryBopsTopicCategoryBypage 
	 * @Description: TODO 分页数据
	 * @param topicCategroyVO
	 * @return 
	 * @see com.need.service.bops.ops.TopicCategoryService#queryBopsTopicCategoryBypage(com.need.bops.web.controller.vo.ops.TopicCategroyVO)
	 */
	@Override
	public List<BopsTopicCategory> queryBopsTopicCategoryBypage(TopicCategroyVO topicCategroyVO) {
		topicCategroyVO.setTotal(bopsTopicCategoryDAO.getCountByparams(topicCategroyVO));
		return bopsTopicCategoryDAO.queryByPage(topicCategroyVO);
	}
	/**
	 * 
	 * @author LXD 2015年9月10日 下午6:36:12
	 * @Method: editTopicCategory 
	 * @Description: TODO 保存或更新
	 * @param bopsTopicCategory 
	 * @see com.need.service.bops.ops.TopicCategoryService#editTopicCategory(com.need.bops.web.controller.vo.ops.TopicCategroyVO)
	 */
	@Transactional
	@Override
	public Message editTopicCategory(BopsTopicCategory bopsTopicCategory,Message message) {
		/**
		 * 根据分类位置的不同校验对应的图片是否上传
		 */
		BopsOpsTopicCategory BopsOpsTopicCategory=BopsOpsTopicCategoryDAO.getById(bopsTopicCategory.getTopicCategoryId());
		if(CategoryPositionEnum.SHIJIAN.code.equals(BopsOpsTopicCategory.getTopicCategoryPosition())){
			if(StringUtils.isBlank(bopsTopicCategory.getTopPicKey())||StringUtils.isBlank(bopsTopicCategory.getListPicKey())){
				return Message.error(5022);	
			}
			
		}else{
			if(StringUtils.isBlank(bopsTopicCategory.getNewPicKey())){
				return Message.error(5023);
			}
			
		}	
		/***
		 * getRelationId不为空，更新操作
		 */
		if(bopsTopicCategory.getRelationId()!=null&&bopsTopicCategory.getRelationId()!=0){
			BopsTopicCategory relationObject= bopsTopicCategoryDAO.getById(bopsTopicCategory.getRelationId());
			if(relationObject.getTopicId().intValue()==bopsTopicCategory.getTopicId().intValue()&&relationObject.getTopicCategoryId().intValue()==bopsTopicCategory.getTopicCategoryId().intValue()){
				//专题ID和场景ID没有变，		
			}else{
				
				/**
				 * 校验该专题是否已和该专题关联
				 */
				BopsTopicCategory checkTopicCategory=bopsTopicCategoryDAO.getByParams(bopsTopicCategory.getTopicId(),bopsTopicCategory.getTopicCategoryId());
				if(checkTopicCategory!=null){
					return Message.error(BizReturnCode.TOPIC_CATEGORY_CHECK);	
				}
				
				
			}
			/**
			 * 更新后台 
			 */
			bopsTopicCategoryDAO.update(bopsTopicCategory);
			/**
			 * 同步前台
			 */
			TopicCategory topicCategory = topicCategoryDAO.getById(bopsTopicCategory.getRelationId());
			BeanUtils.copyProperties(bopsTopicCategory, topicCategory);
			topicCategoryDAO.update(topicCategory);
			
		
		return message;
		}else{
			
			/**
			 * 校验该专题是否已和该专题关联
			 */
			BopsTopicCategory checkTopicCategory=bopsTopicCategoryDAO.getByParams(bopsTopicCategory.getTopicId(),bopsTopicCategory.getTopicCategoryId());
			if(checkTopicCategory!=null){
				return Message.error(BizReturnCode.TOPIC_CATEGORY_CHECK);	
			}
			/**
			 * getRelationId 不为空，增加操作
			 */
			
			BopsTopicCategory newRelation =new BopsTopicCategory();
			BeanUtils.copyProperties(bopsTopicCategory, newRelation);
			bopsTopicCategoryDAO.insert(newRelation);
			/**
			 * 同步前台
			 */
			TopicCategory topicCategory= new TopicCategory();
	        BeanUtils.copyProperties(newRelation, topicCategory);
			topicCategoryDAO.insert(topicCategory);
			return message;	
			
			
		}
		

	}
	/**
	 * 
	 * @author LXD 2015年9月10日 下午9:48:23
	 * @Method: removeTopicCategory 
	 * @Description: TODO 删除关联
	 * @param relationId 
	 * @see com.need.service.bops.ops.TopicCategoryService#removeTopicCategory(int)
	 */
	@Transactional
	@Override
	public void removeTopicCategory(int relationId) {
		bopsTopicCategoryDAO.delete(relationId);
		topicCategoryDAO.delete(relationId);
		
	}
    /**
     * 
     * @author LXD 2015年9月16日 下午3:00:14
     * @Method: getRelationByTopicId 
     * @Description: TODO need1.1 根据专题Id 查询关联信息
     * @param topicId
     * @return 
     * @see com.need.service.bops.ops.TopicCategoryService#getRelationByTopicId(int)
     */
	@Override
	public BopsTopicCategory getRelationByTopicId(int topicId) {
		return bopsTopicCategoryDAO.getRelationByTopicId(topicId);
	}
	
	
	
}
