package com.need.common.core.service.goods.impl;

import com.alibaba.fastjson.JSONObject;
import com.need.common.core.dao.jdbc.goods.GoodsDetailDAO;
import com.need.common.core.service.goods.GoodsDetailService;
import com.need.common.domain.po.api.goods.GoodsDetailPO;
import com.need.common.domain.vo.goods.GoodsDetailVO;
import com.need.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsDetailServiceImpl implements GoodsDetailService {

	@Autowired
	private GoodsDetailDAO goodsDetailDAO;
	
	@Override
	public GoodsDetailVO getGoodsDetailById(String goodsId) {
		// TODO Auto-generated method stub
		GoodsDetailPO goodsDetail = new GoodsDetailPO();
		goodsDetail = goodsDetailDAO.selectByPrimaryKey(goodsId);
		String detailPicKeys = goodsDetail.getDetailPicKeys();
		List<String> detailList = StringUtil.stringToList(detailPicKeys, ",");//把图片字符串转换成List
		
		GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
		goodsDetailVO.setPicList(detailList);
		goodsDetailVO.setGoodsDesc(goodsDetail.getGoodsDesc());
		
		return goodsDetailVO;
	}

	@Override
	public JSONObject getGoodsParamsById(String goodsId) {
		// TODO Auto-generated method stub
		//获取商品的参数信息
		return JSONObject.parseObject(goodsDetailDAO.selectByPrimaryKey(goodsId).getGoodsParams());
	}
	
}
