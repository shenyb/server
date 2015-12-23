package com.need.common.core.dao.jdbc.ops;

import com.github.pagehelper.Page;
import com.need.common.domain.po.api.ops.OpsTopicTemplatePO;
import com.need.common.domain.vo.ops.XinhuanTemplateVO;

public interface OpsTopicTemplateDAO {
    int deleteByPrimaryKey(Integer topicId);

    int insert(OpsTopicTemplatePO record);

    int insertSelective(OpsTopicTemplatePO record);

    OpsTopicTemplatePO selectByPrimaryKey(Integer topicId);

    int updateByPrimaryKeySelective(OpsTopicTemplatePO record);

    int updateByPrimaryKeyWithBLOBs(OpsTopicTemplatePO record);

    int updateByPrimaryKey(OpsTopicTemplatePO record);

    /**
     * 
     * @author peiboli 2015年9月11日 上午10:44:58
     * @Method: queryByPage 
     * @Description: TODO获取新欢专场show
     * @return 
     * @throws
     */
	Page<XinhuanTemplateVO> queryByPage();
}