package com.need.service.bops.ops;

import java.util.List;

import com.need.domain.po.bops.ops.BopsOps;
import com.need.domain.pub.Message;
import com.need.domain.vo.ops.PageBopsOpsVO;

public interface BopsOpsService {
	/**
	 * 
	 * @author LXD 2015年8月5日 下午3:30:55
	 * @Method: addBopsOps 
	 * @Description: TODO 添加一个运营位
	 * @param ops 
	 * @throws
	 */
	void addBopsOps(BopsOps ops);
	/**
	 * 
	 * @author LXD 2015年8月5日 下午3:31:29
	 * @Method: getBopsOpsById 
	 * @Description: TODO 根据运营位ID获取该运营位信息
	 * @param opsId
	 * @return 
	 * @throws
	 */
	BopsOps getBopsOpsById(String  opsId);
	
	
	void auditBopsOps(BopsOps ops);
	/**
	 * 
	 * @author LXD 2015年8月5日 下午3:31:58
	 * @Method: updateBopsOps 
	 * @Description: 更新运营位信息
	 * @param ops 
	 * @throws
	 */
	void updateBopsOps(BopsOps ops);
	
	/**
	 * 
	 * @author LXD 2015年8月5日 下午3:32:21
	 * @Method: deleteBopsOpsById 
	 * @Description: TODO 根据ID删除运营位
	 * @param opsId 
	 * @throws
	 */
	void deleteBopsOpsById(String  opsId);
	
	/**
	 * 
	 * @author LXD 2015年8月5日 下午3:32:45
	 * @Method: getPageOfBopsRole 
	 * @Description: TODO 运营位分页数据
	 * @param pageBopsOpsVO
	 * @return 
	 * @throws
	 */
	List<BopsOps> getPageOfBopsOps(PageBopsOpsVO page);
	
	
	public void deletePortalOps(String opsId);
	/**
	 * 
	 * @author LXD 2015年9月16日 下午2:51:06
	 * @Method: getOpsByopsNumber 
	 * @Description: TODO need1.1根据ops位ID查询运营位和专题的关联信息
	 * @param opsId
	 * @return 
	 * @throws
	 */
	BopsOps getOpsByopsNumber(String opsId);
	/**
	 * 
	 * @author LXD 2015年9月16日 下午2:51:49
	 * @Method: editOps 
	 * @Description: TODO need 1.1 保存或编辑运营位和专题的关联信息
	 * @param bopsOps
	 * @return 
	 * @throws
	 */
	Message editOps(BopsOps bopsOps);

}
