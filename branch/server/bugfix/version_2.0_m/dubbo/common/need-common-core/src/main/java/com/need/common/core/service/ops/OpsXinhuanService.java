package com.need.common.core.service.ops;

import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.ops.XinhuanOpsResultVO;

import java.util.List;

public interface OpsXinhuanService {
	
	/**
	 * @author xiehao 2015年9月11日 下午6:10:33
	 * @Method: getOpsPosition 
	 * @Description: TODO 获取新欢的运营位
	 * @return 
	 * @throws
	 */
	public Message getOpsPosition();
	
	/**
	 * @author xiehao 2015年9月11日 下午6:12:59
	 * @Method: getOpsXinhuanGoods 
	 * @Description: TODO 获取新欢页某个运营位的商品列表 
	 * @param opsId
	 * @param userId
	 * @return 
	 * @throws
	 */
	public Message getOpsXinhuanGoods(String opsId, String userId, int pageNum, int pageSize);

	public List<XinhuanOpsResultVO> queryScrollOps();

	public Message getOpsXinhuanGoods_V1_3(String opsId, String userId, Integer pageNum, Integer pageSize);
	
	/***
	 * 
	 * @author LXD 2015年10月22日 下午3:52:05
	 * @Method: queryPrefectureGoods_v1_3 
	 * @Description: TODO 新欢专区商品列表
	 * @param pageNum
	 * @param pageSize
	 * @return 
	 * @throws
	 */
	public Message queryPrefectureGoods_v1_3(Integer pageNum, Integer pageSize,String userId);

}
