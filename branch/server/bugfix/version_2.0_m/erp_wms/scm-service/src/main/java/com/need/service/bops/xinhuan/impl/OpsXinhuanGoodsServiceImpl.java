package com.need.service.bops.xinhuan.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.xinhuan.OpsXinhuanGoodsDAO;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.dao.bops.xinhuan.BopsOpsMainDAO;
import com.need.dao.bops.xinhuan.BopsOpsXinhuanGoodsDAO;
import com.need.domain.po.bops.xinhuan.BopsOpsMain;
import com.need.domain.po.bops.xinhuan.BopsOpsXinhuanGoods;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.GoodsStoreDetailResultVO;
import com.need.domain.vo.xinhuan.XinhuanGoodsId;
import com.need.domain.vo.xinhuan.XinhuanGoodsParamPageVO;
import com.need.domain.vo.xinhuan.XinhuanGoodsVO;
import com.need.service.bops.xinhuan.OpsXinhuaGoodsService;

@Service
public class OpsXinhuanGoodsServiceImpl implements OpsXinhuaGoodsService {

	@Autowired
	private BopsOpsXinhuanGoodsDAO bopsOpsXinhuanGoodsDAO;
	
	@Autowired
	private OpsXinhuanGoodsDAO opsXinhuanGoodsDAO;
	
	@Autowired
	private BopsOpsMainDAO bopsOpsMainDAO;
	
	@Autowired
	private BopsGoodsDAO bopsGoodsDAO;
	
	/**
	 * @author xiehao 2015年9月11日 下午4:33:13
	 * @Method: addXinhuanGoods 
	 * @Description: TODO 添加运营位商品
	 * @param xinhuanGoods
	 * @return 
	 * @throws
	 */
	@Override
	@Transactional
	public Message addOpsXinhuanGoods(XinhuanGoodsVO xinhuanGoodsVO) {
		// TODO Auto-generated method stub
		Message message = Message.success();
		String [] goodsIds = xinhuanGoodsVO.getGoodsIds();
		String opsId = xinhuanGoodsVO.getOpsId().trim();
		BopsOpsMain bopsOpsXinhuan = bopsOpsMainDAO.selectByPrimaryKey(opsId);
		/**
		 * 所查询的运营位不存在
		 */
		if(null == bopsOpsXinhuan){
			return Message.error(3014);
		}
		BopsOpsXinhuanGoods xinhuanGoods = new BopsOpsXinhuanGoods();
		xinhuanGoods.setOpsId(opsId);
		for(int i = 0; i < goodsIds.length; i++){
			/**
			 * 该商品已经添加到这个运营位，不需要重复添加
			 */
			BopsOpsXinhuanGoods goods = bopsOpsXinhuanGoodsDAO.getByOpsIdAndGoodsId(opsId, goodsIds[i]);
			if(goods != null){
				continue;
			}
			xinhuanGoods.setGoodsId(goodsIds[i]);
			xinhuanGoods.setId(XinhuanGoodsId.generateXinhuanGoodsId());
			/**
			 * 添加运营位商品到后台库
			 */
			bopsOpsXinhuanGoodsDAO.insertNewXinhuanGoods(xinhuanGoods);
			/**
			 * 添加运营位上到前台库
			 */
			opsXinhuanGoodsDAO.insertNewXinhuanGoods(xinhuanGoods);
		}
		
		return message;
	}

	/**
	 * @author xiehao 2015年9月11日 下午4:43:45
	 * @Method: queryXinhuanGoods 
	 * @Description: TODO 查询某个运营位下的所有商品
	 * @param opsId
	 * @return 
	 * @throws
	 */
	@Override
	public Message queryXinhuanGoods(XinhuanGoodsParamPageVO pageVO) {
		// TODO Auto-generated method stub
		Message message = Message.success();

		/**
		 * 查询这个运营位商品的总个数
		 */
		int xinhuanGoodsCount = bopsOpsXinhuanGoodsDAO.countOpsGoods(pageVO);

		pageVO.setTotal(xinhuanGoodsCount);
		message.addData("list", bopsOpsXinhuanGoodsDAO.queryXinhuanGoods(pageVO));
		message.addData("page", pageVO);
	
		
	
		return message;
	}

	/**
	 * @author xiehao 2015年9月11日 下午6:02:40
	 * @Method: deleteXinhuanGoods 
	 * @Description: TODO 删除运营位商品
	 * @param id
	 * @return 
	 * @throws
	 */
	@Override
	@Transactional
	public Message deleteXinhuanGoods(String id) {
		// TODO Auto-generated method stub
		Message message = Message.success();
		/**
		 * 删除后台运营位商品
		 */
		bopsOpsXinhuanGoodsDAO.deleteByPrimaryKey(id);
		/**
		 * 删除前台运营位商品
		 */
		opsXinhuanGoodsDAO.deleteByPrimaryKey(id);
		return message;
	}

	@Override
	public Message editXinhuanGoods(XinhuanGoodsVO xinhuanGoodsVO) {
		// TODO Auto-generated method stub
		bopsOpsXinhuanGoodsDAO.deletByOpsId(xinhuanGoodsVO.getOpsId());
		opsXinhuanGoodsDAO.deletByOpsId(xinhuanGoodsVO.getOpsId());
		Message message = Message.success();
		String [] goodsIds = xinhuanGoodsVO.getGoodsIds();
		String opsId = xinhuanGoodsVO.getOpsId().trim();
		BopsOpsMain bopsOpsXinhuan = bopsOpsMainDAO.selectByPrimaryKey(opsId);
		/**
		 * 所查询的运营位不存在
		 */
		if(null == bopsOpsXinhuan){
			return Message.error(3014);
		}
		BopsOpsXinhuanGoods xinhuanGoods = new BopsOpsXinhuanGoods();
		xinhuanGoods.setOpsId(opsId);
		for(int i = 0; i < goodsIds.length; i++){
			/**
			 * 该商品已经添加到这个运营位，不需要重复添加
			 */
			BopsOpsXinhuanGoods goods = bopsOpsXinhuanGoodsDAO.getByOpsIdAndGoodsId(opsId, goodsIds[i]);
			if(goods != null){
				continue;
			}
			xinhuanGoods.setGoodsId(goodsIds[i]);
			xinhuanGoods.setId(XinhuanGoodsId.generateXinhuanGoodsId());
			/**
			 * 添加运营位商品到后台库
			 */
			bopsOpsXinhuanGoodsDAO.insertNewXinhuanGoods(xinhuanGoods);
			/**
			 * 添加运营位上到前台库
			 */
			opsXinhuanGoodsDAO.insertNewXinhuanGoods(xinhuanGoods);
		}
		
		return message;
	}

	@Override
	public int deleteXinhuanGoodsByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		bopsOpsXinhuanGoodsDAO.deleteByGoodsId(goodsId);
		int result = opsXinhuanGoodsDAO.deleteByGoodsId(goodsId);
		return result;
	}
   
	/**
	 * 
	 * @author LXD 2015年11月5日 下午6:40:11
	 * @Method: addXinhuanGoods 
	 * @Description: TODO 运营为添加商品
	 * @param goodsCods
	 * @return 
	 * @see com.need.service.bops.xinhuan.OpsXinhuaGoodsService#addXinhuanGoods(java.lang.String)
	 */
	@Transactional
	@Override
	public Message addXinhuanGoods(XinhuanGoodsVO goodsVO) {
				Message message = Message.success();
				//String [] goodsIds = goodsVO.getGoodsIds();
				String newGoodsCode=goodsVO.getGoodsCodes().trim();
				if(newGoodsCode.endsWith(",")){
					newGoodsCode=newGoodsCode.substring(0,newGoodsCode.length()-1);
				}
				String [] goodsCodes=newGoodsCode.split(",");
				List<String> goodsCodelist =Arrays.asList(goodsCodes);
				List<GoodsStoreDetailResultVO> list= bopsGoodsDAO.getByGoodsCodes(goodsCodelist);
				String opsId = goodsVO.getOpsId().trim();
				BopsOpsMain bopsOpsXinhuan = bopsOpsMainDAO.selectByPrimaryKey(opsId);
				/**
				 * 所查询的运营位不存在
				 */
				if(null == bopsOpsXinhuan){
					return Message.error(3014);
				}
				BopsOpsXinhuanGoods xinhuanGoods = new BopsOpsXinhuanGoods();
				xinhuanGoods.setOpsId(opsId);
				for (GoodsStoreDetailResultVO goodsStoreDetailResultVO : list) {
					/**
					 * 该商品已经添加到这个运营位，不需要重复添加
					 */
					BopsOpsXinhuanGoods goods = bopsOpsXinhuanGoodsDAO.getByOpsIdAndGoodsId(opsId, goodsStoreDetailResultVO.getGoodsId());
					if(goods != null){
						continue;
					}
					xinhuanGoods.setGoodsId(goodsStoreDetailResultVO.getGoodsId());
					xinhuanGoods.setId(XinhuanGoodsId.generateXinhuanGoodsId());
					/**
					 * 添加运营位商品到后台库
					 */
					bopsOpsXinhuanGoodsDAO.insertNewXinhuanGoods(xinhuanGoods);
					/**
					 * 添加运营位上到前台库
					 */
					opsXinhuanGoodsDAO.insertNewXinhuanGoods(xinhuanGoods);
				}
				
				return message;
	}
    
	@Transactional
	@Override
	public Message editGoods(XinhuanGoodsVO xinhuanGoods) {
		Message message = Message.success();
		bopsOpsXinhuanGoodsDAO.updateGoods(xinhuanGoods);
		opsXinhuanGoodsDAO.updateGoods(xinhuanGoods);
		return message;
	}
	

}
