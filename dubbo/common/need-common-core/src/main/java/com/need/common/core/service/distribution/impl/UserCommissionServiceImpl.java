package com.need.common.core.service.distribution.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.distribution.UserCommissionAccountDAO;
import com.need.common.core.dao.jdbc.distribution.UserCommissionDAO;
import com.need.common.core.dao.jdbc.distribution.UserDistributionGoodsDAO;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.core.service.distribution.UserCommissionService;
import com.need.common.domain.enums.UserCommissionStatusEnum;
import com.need.common.domain.po.api.distribution.UserCommissionPO;
import com.need.common.domain.po.api.distribution.UserDistributionGoodsPO;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.distribution.DistributionGoodsVO;
import com.need.common.domain.vo.distribution.DistributionTradeVO;
import com.need.common.domain.vo.distribution.IncomeGoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCommissionServiceImpl implements UserCommissionService {
	@Autowired
	private UserDistributionGoodsDAO userDistributionGoodsDAO;
	@Autowired
	private UserCommissionDAO userCommissionDAO;
	@Autowired
	private UserCommissionAccountDAO userCommissionAccountDAO;
	@Autowired
	private TradeBaseDAO tradeBaseDAO;

	@Autowired
	private GoodsMainDAO goodsMainDAO;
    /***
     * 
     * @author LXD 2015年12月1日 下午6:54:31
     * @Method: getGoodsByuserId 
     * @Description: TODO 查询用户分销的商品
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return 
     * @see com.need.api.service.distribution.UserCommissionService#getGoodsByuserId(java.lang.String, int, int)
     */
	@Override
	public Message getGoodsByuserId(String userId, int pageNum, int pageSize) {
		Message message =Message.success();
		PageHelper.startPage(pageNum, pageSize);//用分页工具对结果进行分页
		Page<DistributionGoodsVO> page = (Page<DistributionGoodsVO>) userDistributionGoodsDAO.getGoodsByUserId(userId);
		message.addData("goodsList", page.getResult());
		message.addData("totalCount", page.getTotal());
		return message;
	}
   /***
    * 
    * @author LXD 2015年12月1日 下午6:54:45
    * @Method: getIncomeByUserId 
    * @Description: TODO 查询用户的收益
    * @param userId
    * @param pageNum
    * @param pageSize
    * @return 
    * @see com.need.api.service.distribution.UserCommissionService#getIncomeByUserId(java.lang.String, int, int)
    */
	@Override
	public Message getIncomeByUserId(String userId, int pageNum, int pageSize) {
		//用户待入账和已入账的商品
				Message message =Message.success();
				PageHelper.startPage(pageNum, pageSize);//用分页工具对结果进行分页
				Page<UserCommissionPO> page = (Page<UserCommissionPO>)userCommissionDAO.queryGoodsByUserId(userId);
				List<IncomeGoodsVO> list =new ArrayList<IncomeGoodsVO>();
				for (UserCommissionPO userCommissionPO : page.getResult()) {
					IncomeGoodsVO IncomeGoodsVO =new IncomeGoodsVO();
					BeanUtils.copyProperties(userCommissionPO, IncomeGoodsVO);
					IncomeGoodsVO.setTradeTime(userCommissionPO.getCreateTime().getTime());			
					List<DistributionTradeVO> tradeBaseList =tradeBaseDAO.selectBytradeNo(userCommissionPO.getUserTradeNo());
					if(tradeBaseList!=null&&!tradeBaseList.isEmpty()){
					 IncomeGoodsVO.setUserId(tradeBaseList.get(0).getBuyerId());
					 IncomeGoodsVO.setNickName(tradeBaseList.get(0).getNickName());
					 }
					/***
					 * 入账时间
					 * 
					 */
					if(UserCommissionStatusEnum.HAS_INCOME.status.equals(userCommissionPO.getCommissionStatus())){
					  IncomeGoodsVO.setInTime(userCommissionPO.getUpdateTime().getTime());
					}
					list.add(IncomeGoodsVO);
				}
				message.addData("goodsList", list);
				message.addData("totalCount", page.getTotal());
				return message;
	}

	/***
	 * 
	 * @author LXD 2015年12月4日 下午4:27:21
	 * @Method: getSumIncomeByUserId 
	 * @Description: TODO 我的总收益
	 * @param userId
	 * @return 
	 * @see com.need.api.service.distribution.UserCommissionService#getSumIncomeByUserId(java.lang.String)
	 */
		@Override
		public Message getSumIncomeByUserId(String userId) {
			Message message =Message.success();
			/***
			 * 用户累计收益
			 */
			Integer accumulation= userCommissionDAO.getSumIncome(userId);
			if(accumulation==null){
				accumulation=0;
			}
			message.addData("accumulation", accumulation);
			/***
			 * 用户可用收益
			 */
			Integer availableBalance = userCommissionAccountDAO.getAvailableByUserId(userId);
			if(availableBalance==null){
				availableBalance=0;
			}
			message.addData("availableBalance", availableBalance);
			return message;
		}
	@Override
	public Message addGoods(String goodsId, String userId) {
		Message message =Message.success();
		GoodsMainPO goods = goodsMainDAO.selectByPrimaryKey(goodsId);
		if (goods == null) {
			return Message.error(BizReturnCode.GOODS_NOT_EXIST);
		}
		UserDistributionGoodsPO record = userDistributionGoodsDAO.getByUserIdAndGoodsId(userId, goodsId);
		if (record == null) {
			UserDistributionGoodsPO po = new UserDistributionGoodsPO();
			po.setGoodsId(goodsId);
			po.setGoodsName(goods.getGoodsName());
			po.setUserId(userId);
			userDistributionGoodsDAO.insert(po);
		}
		return message;
	}
	
	/**
	 * 二、 增加佣金后的订单计算规则： 1. 先计算商品总价 2. 根据总价计算运费、运费优惠 3. 根据总价确定可用优惠券 4.
	 * 根据总价的30%计算可用佣金 5. 判断是否使用优惠券 6. 判断是否使用佣金 7. 总支付价格=总价+运费-运费优惠-优惠券-可用佣金 8.
	 * 订单分拆价格：（总价-总支付价格）/价格权重
	 * 
	 * @author shenyb 2015年11月30日 上午11:25:06
	 * @Method: getAvailableCommission
	 * @Description:
	 * @param useId
	 *            用户id
	 * @param totalPrice
	 *            商品总价
	 * @return
	 * @see com.need.api.service.distribution.UserCommissionService#getAvailableCommission(java.lang.String,
	 *      int)
	 */
	
}
