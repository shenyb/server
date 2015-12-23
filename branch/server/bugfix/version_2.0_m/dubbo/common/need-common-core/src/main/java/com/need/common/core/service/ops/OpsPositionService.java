package com.need.common.core.service.ops;

import com.need.common.domain.po.api.ops.OpsPositionPO;
import com.need.common.domain.pub.Message;

import java.util.List;


public interface OpsPositionService {
	/**
	 * 
	 * @author LXD 2015年8月8日 下午4:00:20
	 * @Method: getOpsPosition 
	 * @Description: TODO 获取首页运营位
	 * @return 
	 * @throws
	 */
	public List<OpsPositionPO> getOpsPosition();
     /**
      * 
      * @author LXD 2015年8月25日 下午2:15:31
      * @Method: queryhomeOps 
      * @Description: TODO 商店运营位
      * @return 
      * @throws
      */
	public Message queryShopOps();
	/***
	 * 
	 * @author LXD 2015年8月25日 下午2:24:31
	 * @Method: queryHomeOps 
	 * @Description: TODO 首页运营位
	 * @return 
	 * @throws
	 */
	public Message queryHomeOps();
	/***
	 * 
	 * @author LXD 2015年8月25日 下午2:38:07
	 * @Method: queryHotGoods 
	 * @Description: TODO 人气商品列表
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return 
	 * @throws
	 */
	public Message queryHotGoods(String userId, Integer pageNum, Integer pageSize);
	/**
	 * 
	 * @author LXD 2015年8月25日 下午2:53:31
	 * @Method: querySelectedGoods 
	 * @Description: TODO 精选商品
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return 
	 * @throws
	 */
	public Message querySelectedGoods(String userId, Integer pageNum, Integer pageSize);
	/**
	 * 
	 * @author LXD 2015年9月9日 下午5:54:14
	 * @Method: queryHomeOps_v1_1  
	 * @Description: TODO 1.1首页运营位
	 * @return 
	 * @throws
	 */
	public Message queryHomeOps_v1_1();
	/**
	 * 
	 * @author LXD 2015年9月9日 下午6:30:05
	 * @Method: queryShijianOps_v1_1 
	 * @Description: TODO need1.1世间首页
	 * @return 
	 * @throws
	 */
	public Message queryShijianOps_v1_1();
	/**
	 * 
	 * @author LXD 2015年9月9日 下午7:03:11
	 * @Method: queryShijianScrollOps_v1_1 
	 * @Description: TODO need1.1 世间SCROLL运营位
	 * @return 
	 * @throws
	 */
	public Message queryShijianScrollOps_v1_1();
	/***
	 * 
	 * @author LXD 2015年10月22日 上午11:20:57
	 * @Method: queryKolOps_v1_2 
	 * @Description: TODO 行家banner运营位
	 * @param kolCategoryId
	 * @return 
	 * @throws
	 */
	public Message queryKolOps_v1_2(int kolCategoryId);
	/***
	 * 
	 * @author LXD 2015年10月22日 上午11:27:58
	 * @Method: queryStartOps_v1_3 
	 * @Description: TODO 启动页运营位
	 * @return 
	 * @throws
	 */
	public Message queryStartOps_v1_3();
	/**
	 * 
	 * @author LXD 2015年10月22日 下午12:15:23
	 * @Method: queryXinhuanOps_v1_3 
	 * @Description: TODO 获取新欢banner运营位
	 * @return 
	 * @throws
	 */
	public Message queryXinhuanOps_v1_3();
	/***
	 * 
	 * @author LXD 2015年10月22日 下午2:35:40
	 * @Method: queryXinhuanScroll_v1_3 
	 * @Description: TODO 新欢scroll运营位
	 * @return 
	 * @throws
	 */
	public Message queryXinhuanScroll_v1_3();
	
	public Message queryXinhuanScroll_v2_0();

	
    
}
