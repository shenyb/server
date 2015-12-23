package com.need.service.bops.xinhuan;

import java.util.List;

import com.need.domain.po.bops.xinhuan.BopsOpsMain;
import com.need.domain.pub.Message;
import com.need.domain.pub.Page;
import com.need.domain.vo.xinhuan.OpsXinhuanParamVO;


public interface OpsXinhuanService {
	
	/**
	 * @author xiehao 2015年9月11日 下午4:27:03
	 * @Method: addNewOpsPosition 
	 * @Description: TODO 添加运营位
	 * @param xinhuan
	 * @return 
	 * @throws
	 */
	public Message addNewOpsPosition(OpsXinhuanParamVO xinhuan);
	
	/**
	 * @author xiehao 2015年9月11日 下午4:29:41
	 * @Method: queryOpsPosition 
	 * @Description: TODO 查询运营位
	 * @return 
	 * @throws
	 */
	public List<BopsOpsMain> queryOpsPosition(OpsXinhuanParamVO categoryPage);
	
	public int getcount(OpsXinhuanParamVO categoryPage);
	
	/**
	 * @author xiehao 2015年9月10日 上午11:21:11
	 * @Method: updateOpsPosition 
	 * @Description: TODO 更新运营位信息
	 * @param xinhuan
	 * @return 
	 * @throws
	 */
	public Message updateOpsPosition(OpsXinhuanParamVO xinhuan);

}
