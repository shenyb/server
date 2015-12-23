package com.need.service.bops.template;

import java.util.List;

import com.need.domain.pub.Message;
import com.need.domain.vo.template.TemplatePageParamVO;
import com.need.domain.vo.template.TemplateParamVO;
import com.need.domain.vo.template.TemplateResultVO;

/**
 * <p></p>
 * @author Rylan 2015年9月7日 下午2:22:25
 * @ClassName TemplateService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年9月7日
 * @modify by reason:{方法名}:{原因}
 */
public interface TopicTemplateService {
	
	/**
	 * @author Rylan 2015年9月7日 下午2:34:48
	 * @Method: saveTemplate 
	 * @Description: 保存模板并上传到服务器
	 * @param htmlContent
	 * @param path
	 * @return 
	 * @throws
	 */
	public Message saveTemplate(TemplateParamVO templateParamVO);
	
	/**
	 * @author Rylan 2015年9月7日 下午2:45:26
	 * @Method: queryTemplate 
	 * @Description: TODO
	 * @return 
	 * @throws
	 */
	public List<TemplateResultVO> queryTemplate(TemplatePageParamVO param); 
	
	/**
	 * @author Rylan 2015年9月7日 下午2:45:29
	 * @Method: getTempalteById 
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @throws
	 */
	public TemplateResultVO getTempalteById(int id);
	
	/**
	 * @author Rylan 2015年9月7日 下午2:47:33
	 * @Method: updateTempalteById 
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @throws
	 */
	public Message updateTempalteById(TemplateParamVO vo);
	
	/**
	 * @author Rylan 2015年9月7日 下午2:48:29
	 * @Method: deleteTempalteById 
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @throws
	 */
	public int deleteTempalteById(int id);
	
	/**
	 * @author Rylan 2015年9月11日 下午10:02:10
	 * @Method: auditTemplate 
	 * @Description: TODO 审核操作
	 * @param templateParamVO
	 * @return 
	 * @throws
	 */
	public Message auditTemplate(TemplateParamVO templateParamVO);
	
	/**
	 * @author Rylan 2015年12月8日 上午10:37:40
	 * @Method: test 
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @throws
	 */
	//public Message test(int id);
	
}
