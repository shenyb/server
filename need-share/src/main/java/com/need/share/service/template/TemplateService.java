package com.need.share.service.template;

import java.util.List;

import com.need.share.web.controller.template.vo.TemplateBaseVO;

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
public interface TemplateService {
	
	/**
	 * @author Rylan 2015年9月7日 下午2:34:48
	 * @Method: saveTemplate 
	 * @Description: 保存模板并上传到服务器
	 * @param htmlContent
	 * @param path
	 * @return 
	 * @throws
	 */
	public int saveTemplate(String htmlContent,String path);
	
	/**
	 * @author Rylan 2015年9月7日 下午2:45:26
	 * @Method: queryTemplate 
	 * @Description: TODO
	 * @return 
	 * @throws
	 */
	public List<TemplateBaseVO> queryTemplate(); 
	
	/**
	 * @author Rylan 2015年9月7日 下午2:45:29
	 * @Method: getTempalteById 
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @throws
	 */
	public TemplateBaseVO getTempalteById(int id);
	
	/**
	 * @author Rylan 2015年9月7日 下午2:47:33
	 * @Method: updateTempalteById 
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @throws
	 */
	public int updateTempalteById(int id);
	
	/**
	 * @author Rylan 2015年9月7日 下午2:48:29
	 * @Method: deleteTempalteById 
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @throws
	 */
	public int deleteTempalteById(int id);
	
}
