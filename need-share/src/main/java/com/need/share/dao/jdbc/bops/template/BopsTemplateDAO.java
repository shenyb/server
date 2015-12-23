package com.need.share.dao.jdbc.bops.template;

import java.util.List;

import com.need.share.dao.jdbc.bops.template.po.BopsTemplatePO;

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
public interface BopsTemplateDAO {
    int deleteByTempalteId(Integer templateId);

    int insert(BopsTemplatePO record);

    BopsTemplatePO getByTempalteId(int templateId);

    List<BopsTemplatePO> queryTempalte();
    
    int updateByTempalteId(BopsTemplatePO record);
        

}