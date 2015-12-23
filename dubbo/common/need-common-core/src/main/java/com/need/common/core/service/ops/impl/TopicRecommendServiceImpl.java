package com.need.common.core.service.ops.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.common.core.dao.jdbc.ops.OpsTopicCategoryDAO;
import com.need.common.core.dao.jdbc.ops.TopicCategoryDAO;
import com.need.common.core.service.ops.TopicRecommendService;
import com.need.common.domain.po.api.ops.OpsTopicCategoryPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.ops.CategoryTopicVO;
import com.need.common.domain.vo.ops.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicRecommendServiceImpl implements TopicRecommendService {
   @Autowired
	private TopicCategoryDAO topicCategoryDAO;
   @Autowired
    private OpsTopicCategoryDAO opsTopicCategoryDAO;
	@Override
	public Message getCategoryTopic_v1_1(Integer pageNum, Integer pageSize) {
		/**
		 * 分页查询分类,封装数据
		 */
		Message message=Message.success();
		PageHelper.startPage(pageNum, pageSize);
		List<CategoryVO> resultList=new ArrayList<CategoryVO>();
		Page<OpsTopicCategoryPO> page = (Page<OpsTopicCategoryPO>)opsTopicCategoryDAO.queryByPage();
		if(page.getResult()!=null&&page.getResult().size()>0){
			for(OpsTopicCategoryPO topicCategoryPO:page.getResult()){
				CategoryVO categoryVO=new CategoryVO();
				List<CategoryTopicVO> list= topicCategoryDAO.queryForShijian(topicCategoryPO.getTopicCategoryId());
				categoryVO.setCategoryName(topicCategoryPO.getTopicCategoryName());
				categoryVO.setCategoryId(topicCategoryPO.getTopicCategoryId());
				categoryVO.setTopicList(list);	
				resultList.add(categoryVO);

			}
			
		}
		message.addData("categoryList", resultList);
		message.addData("totalCount", page.getTotal());
		return message;
	}
	/**
	 * 
	 * @author LXD 2015年9月10日 下午11:45:55
	 * @Method: getMoreTopic_v1_1 
	 * @Description: TODO 获取更多专题列表
	 * @param pageNum
	 * @param pageSize
	 * @return 
	 * @see com.need.api.service.ops.TopicRecommendService#getMoreTopic_v1_1(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Message getMoreTopic_v1_1(Integer categoryId,Integer pageNum, Integer pageSize) {
		Message success =Message.success();
		PageHelper.startPage(pageNum, pageSize);
		Page<CategoryTopicVO> page = (Page<CategoryTopicVO>)topicCategoryDAO.queryMoreTopic(categoryId);
		success.addData("topicList", page.getResult());
		success.addData("totalCount", page.getTotal());
		return success;
	}
	
}
