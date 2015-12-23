package com.need.dao.bops.template;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.need.domain.po.bops.template.BopsTopicTemplatePO;
import com.need.domain.vo.template.TemplatePageParamVO;
import com.need.domain.vo.template.TemplateParamVO;
import com.need.domain.vo.template.TemplateResultVO;


/**
 * <p></p>
 * @author Rylan 2015年9月7日 下午12:14:41
 * @ClassName BopsTemplatePOMapper
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月7日
 * @modify by reason:{方法名}:{原因}
 */
public interface BopsTopicTemplateDAO {
    int deleteById(Integer templateId);

    int insert(BopsTopicTemplatePO record);

    TemplateResultVO getByTempalteId(int templateId);

    List<TemplateResultVO> queryTempalte(TemplatePageParamVO param);
    
    int updateById(BopsTopicTemplatePO record);
        
    int updateContentById(TemplateParamVO record);
    
    int countTopicCode(@Param("topicCode")String topicCode); 
    
    int countTopicCodePage(TemplatePageParamVO param);
    
    
    
}