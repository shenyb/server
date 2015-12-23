package com.need.service.bops.xinhuan;

import com.need.domain.pub.Message;
import com.need.domain.vo.xinhuan.XinhuanGoodsParamPageVO;
import com.need.domain.vo.xinhuan.XinhuanGoodsVO;

public interface OpsXinhuaGoodsService {
	
	/**
	 * @author xiehao 2015年9月11日 下午4:33:13
	 * @Method: addXinhuanGoods 
	 * @Description: TODO 添加运营位商品
	 * @param xinhuanGoods
	 * @return 
	 * @throws
	 */
	public Message addOpsXinhuanGoods(XinhuanGoodsVO xinhuanGoods);
	
	/**
	 * @author xiehao 2015年9月11日 下午4:43:45
	 * @Method: queryXinhuanGoods 
	 * @Description: TODO 查询某个运营位下的所有商品
	 * @param opsId
	 * @return 
	 * @throws
	 */
	public Message queryXinhuanGoods(XinhuanGoodsParamPageVO pageVO);
	
	/**
	 * @author xiehao 2015年9月11日 下午6:02:40
	 * @Method: deleteXinhuanGoods 
	 * @Description: TODO 删除运营位商品
	 * @param id
	 * @return 
	 * @throws
	 */
	public Message deleteXinhuanGoods(String id);
	
	public int deleteXinhuanGoodsByGoodsId(String goodsId);
	
	public Message editXinhuanGoods(XinhuanGoodsVO xinhuanGoods);
    /***
     * 
     * @author LXD 2015年11月5日 下午6:39:45
     * @Method: addXinhuanGoods 
     * @Description: TODO 运营位添加商品
     * @param goodsCods
     * @return 
     * @throws
     */
	public Message addXinhuanGoods(XinhuanGoodsVO goodsVO);
    /***
     * 
     * @author LXD 2015年11月7日 下午6:16:08
     * @Method: editGoods 
     * @Description: TODO 编辑商品
     * @param xinhuanGoods
     * @return 
     * @throws
     */
	public Message editGoods(XinhuanGoodsVO xinhuanGoods);

}
