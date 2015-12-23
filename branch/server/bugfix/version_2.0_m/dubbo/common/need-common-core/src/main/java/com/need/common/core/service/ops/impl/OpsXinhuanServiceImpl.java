package com.need.common.core.service.ops.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.common.core.dao.jdbc.need.NeedGoodsDAO;
import com.need.common.core.dao.jdbc.ops.OpsMainDAO;
import com.need.common.core.dao.jdbc.ops.OpsXinhuanGoodsDAO;
import com.need.common.core.service.ops.OpsXinhuanService;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.ops.GoodsVO;
import com.need.common.domain.vo.ops.NeedVO;
import com.need.common.domain.vo.ops.XinhuanGoodsResultVO;
import com.need.common.domain.vo.ops.XinhuanOpsResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpsXinhuanServiceImpl implements OpsXinhuanService {

//	@Autowired
//	private OpsXinhuanDAO opsXinhuanDAO;
	
	@Autowired
	private OpsMainDAO opsMainDAO;
	
	@Autowired
	private OpsXinhuanGoodsDAO opsXinhuanGoodsDAO;
	
	@Autowired
	private NeedGoodsDAO needGoodsDAO;
	
	/**
	 * @author xiehao 2015年9月11日 下午6:10:33
	 * @Method: getOpsPosition 
	 * @Description: TODO 获取新欢的运营位
	 * @return 
	 * @throws
	 */
	@Override
	public Message getOpsPosition() {
		// TODO Auto-generated method stub
		Message message = Message.success();
		List<XinhuanOpsResultVO> opsList = opsMainDAO.queryOpsPosition();
		message.addData("xinhuanList", opsList);
		return message;
	}

	/**
	 * @author xiehao 2015年9月11日 下午6:12:59
	 * @Method: getOpsXinhuanGoods 
	 * @Description: TODO 获取新欢页某个运营位的商品列表 
	 * @param opsId
	 * @param userId
	 * @return 
	 * @throws
	 */
	@Override
	public Message getOpsXinhuanGoods(String opsId, String userId, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Message message = Message.success();
		/**
		 * 获取商品属性的List
		 */
		PageHelper.startPage(pageNum, pageSize);//用分页工具对结果进行分页
		Page<GoodsVO> page = (Page<GoodsVO>)opsXinhuanGoodsDAO.queryXinhuanGoods(opsId);
		List<GoodsVO> xinhuanGoodsList = page.getResult();
		List<XinhuanGoodsResultVO> xinhuanList = new ArrayList<XinhuanGoodsResultVO>();
		/**
		 * 按照客户端的要求组装数据
		 */
		for(GoodsVO goods: xinhuanGoodsList){
			XinhuanGoodsResultVO xinhuanGoodsResultVO = new XinhuanGoodsResultVO();
			xinhuanGoodsResultVO.setGoods(goods);
			NeedVO need = new NeedVO();
			/**
			 * 得到该商品的被Need的次数
			 */
			int needCount = needGoodsDAO.getNeedGoodsCount(goods.getGoodsId());
			need.setNeedCount(needCount);
			/**
			 * 用户没有登录的时候，返回isNeed="FALSE"
			 */
			if(!StringUtils.hasText(userId)){
				need.setIsNeed(Boolean.FALSE.toString().toUpperCase());
			}
			else{
				/**
				 * 用户登录了，去数据库查该用户是否Need过该商品
				 */
				String isNeed = needGoodsDAO.countIsNeeded(userId, goods.getGoodsId()).toString().toUpperCase();
				need.setIsNeed(isNeed);
			}
			xinhuanGoodsResultVO.setNeed(need);
			xinhuanList.add(xinhuanGoodsResultVO);
		}
		message.addData("xinhuanList", xinhuanList);
		message.addData("totalCount", page.getTotal());
		return message;
	}

	@Override
	public List<XinhuanOpsResultVO> queryScrollOps() {
		List<XinhuanOpsResultVO> opsList = opsMainDAO.queryXinhuanPosition();
		return opsList;
	}

	@Override
	public Message getOpsXinhuanGoods_V1_3(String opsId, String userId, Integer pageNum, Integer pageSize) {
				Message message = Message.success();
				/**
				 * 获取商品属性的List
				 */
				PageHelper.startPage(pageNum, pageSize);//用分页工具对结果进行分页
				Page<GoodsVO> page = (Page<GoodsVO>)opsXinhuanGoodsDAO.queryXinhuanGoods_V1_3(opsId);
				List<GoodsVO> xinhuanGoodsList = page.getResult();
				List<XinhuanGoodsResultVO> xinhuanList = new ArrayList<XinhuanGoodsResultVO>();
				/**
				 * 按照客户端的要求组装数据
				 */
				for(GoodsVO goods: xinhuanGoodsList){
					XinhuanGoodsResultVO xinhuanGoodsResultVO = new XinhuanGoodsResultVO();
					xinhuanGoodsResultVO.setGoods(goods);
					NeedVO need = new NeedVO();
					/**
					 * 得到该商品的被Need的次数
					 */
					int needCount = needGoodsDAO.getNeedGoodsCount(goods.getGoodsId());
					need.setNeedCount(needCount);
					/**
					 * 用户没有登录的时候，返回isNeed="FALSE"
					 */
					if(!StringUtils.hasText(userId)){
						need.setIsNeed(Boolean.FALSE.toString().toUpperCase());
					}
					else{
						/**
						 * 用户登录了，去数据库查该用户是否Need过该商品
						 */
						String isNeed = needGoodsDAO.countIsNeeded(userId, goods.getGoodsId()).toString().toUpperCase();
						need.setIsNeed(isNeed);
					}
					xinhuanGoodsResultVO.setNeed(need);
					xinhuanList.add(xinhuanGoodsResultVO);
				}
				message.addData("xinhuanList", xinhuanList);
				message.addData("totalCount", page.getTotal());
				return message;
	}

	@Override
	public Message queryPrefectureGoods_v1_3(Integer pageNum, Integer pageSize,String userId) {
		Message message = Message.success();
		
		/**
		 * 获取商品属性的List
		 */
		PageHelper.startPage(pageNum, pageSize);//用分页工具对结果进行分页
		Page<GoodsVO> page = (Page<GoodsVO>)opsXinhuanGoodsDAO.queryPrefectureGoods_V1_3();
		List<GoodsVO> xinhuanGoodsList = page.getResult();
		List<XinhuanGoodsResultVO> xinhuanList = new ArrayList<XinhuanGoodsResultVO>();
		/**
		 * 组装数据
		 */
		for(GoodsVO goods: xinhuanGoodsList){
			XinhuanGoodsResultVO xinhuanGoodsResultVO = new XinhuanGoodsResultVO();
			xinhuanGoodsResultVO.setGoods(goods);
			NeedVO need = new NeedVO();
			/**
			 * 得到该商品的被Need的次数
			 */
			int needCount = needGoodsDAO.getNeedGoodsCount(goods.getGoodsId());
			need.setNeedCount(needCount);
			/**
			 * 用户没有登录的时候，返回isNeed="FALSE"
			 */
			if(!StringUtils.hasText(userId)){
				need.setIsNeed(Boolean.FALSE.toString().toUpperCase());
			}
			else{
				/**
				 * 用户登录了，去数据库查该用户是否Need过该商品
				 */
				String isNeed = needGoodsDAO.countIsNeeded(userId, goods.getGoodsId()).toString().toUpperCase();
				need.setIsNeed(isNeed);
			}
			xinhuanGoodsResultVO.setNeed(need);
			xinhuanList.add(xinhuanGoodsResultVO);
		}
		message.addData("xinhuanList", xinhuanList);
		if(xinhuanGoodsList!=null&&xinhuanGoodsList.size()>0){
		message.addData("opsName", xinhuanGoodsList.get(0).getOpsName());
		}else{
	    message.addData("opsName", "特价专区");	
		}
		message.addData("totalCount", page.getTotal());
		return message;
	}

}
