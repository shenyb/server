package com.need.dao.api.template;

import com.need.domain.po.api.template.OpsTopicTemplate;

/**
 * <p></p>
 * @author Rylan 2015年9月9日 下午11:27:30
 * @ClassName OpsTopicTemplateMapper
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月9日
 * @modify by reason:{方法名}:{原因}
 */
public interface OpsTopicTemplateDAO {
	
    int deleteById(Integer topicId);

    int insert(OpsTopicTemplate record);

    OpsTopicTemplate selectById(Integer topicId);

    int updateById(OpsTopicTemplate record);
}