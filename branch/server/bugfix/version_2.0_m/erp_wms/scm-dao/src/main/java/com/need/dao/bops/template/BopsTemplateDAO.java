package com.need.dao.bops.template;

import java.util.List;

import com.need.domain.po.bops.template.BopsTemplate;
import com.need.domain.pub.Page;

/**
 * <p></p>
 * @author Rylan 2015年9月10日 下午2:43:11
 * @ClassName BopsTemplateDAO
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月10日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsTemplateDAO {

	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:52:58
	 * @Method: insert 
	 * @Description: TODO新增专题模板
	 * @param record
	 * @return 
	 * @throws
	 */
    int insert(BopsTemplate record);

    /**
     * 
     * @author peiboli 2015年9月16日 下午2:52:49
     * @Method: getById 
     * @Description: TODO查看模板
     * @param templateId
     * @return 
     * @throws
     */
    BopsTemplate getById(String templateId);
    /**
     * 
     * @author peiboli 2015年9月16日 下午2:52:39
     * @Method: queryByPage 
     * @Description: TODO获得模板列表
     * @param templatePage
     * @return 
     * @throws
     */
    List<BopsTemplate> queryByPage(Page templatePage);

    /**
     * 
     * @author peiboli 2015年9月16日 下午2:52:25
     * @Method: updateById 
     * @Description: TODO更新模板
     * @param record
     * @return 
     * @throws
     */
    int updateById(BopsTemplate record);
    /**
     * 
     * @author peiboli 2015年9月16日 下午2:52:13
     * @Method: deleteById 
     * @Description: TODO删除
     * @param templateId 
     * @throws
     */
	void deleteById(String templateId);

	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:51:34
	 * @Method: count 
	 * @Description: TODO获得总条数
	 * @return 
	 * @throws
	 */
	int count();
	/**
	 * 
	 * @author peiboli 2015年9月16日 下午2:51:49
	 * @Method: countByTemplateName 
	 * @Description: TODO校验名称是否存在
	 * @param templateName
	 * @return 
	 * @throws
	 */
	int  countByTemplateName(String templateName);
}