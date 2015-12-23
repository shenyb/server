package com.need.share.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.biz.utils.CurrencyUtil;
import com.need.framework.utils.ImageUtils;
import com.need.share.common.enums.UserTypeEnum;
import com.need.share.dao.jdbc.api.goods.po.GoodsMainPO;
import com.need.share.dao.jdbc.api.user.UserBaseDAO;
import com.need.share.dao.jdbc.api.user.po.UserBasePO;
import com.need.share.service.user.UserBaseService;
import com.need.share.web.controller.kol.vo.GoodsMainVO;
import com.need.share.web.controller.kol.vo.KolInfoVo;
import com.need.share.web.controller.kol.vo.TradeGoodVO;
import com.need.share.web.controller.kol.vo.UserInfoVO;

/**
 * <p></p>
 * @author Rylan 2015年8月25日 上午11:03:34
 * @ClassName UserBaseServiceImpl
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月25日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class UserBaseServiceImpl implements UserBaseService {

	@Autowired
	private UserBaseDAO userBaseDAO;

	@Override
	public UserInfoVO getUserInfo(String userId) {
		/** TODO Auto-generated method stub*/
		UserBasePO  userBasePO=userBaseDAO.getUserBaseByUserId(userId);//查询用户信息
		if(userBasePO==null){
			return null;
		}
		UserInfoVO userInfoVO=new UserInfoVO();//用户信息
		if (userBasePO.getUserType().equals(UserTypeEnum.KOL.code)) {//如果是行家
			KolInfoVo   kolInfoVo  =userBaseDAO.getKolInfoBykolId(userId);
			BeanUtils.copyProperties(kolInfoVo, userInfoVO);//准备基础数据
			List<String> categoryList= userBaseDAO.querycategoryNameList(userId);
			userInfoVO.setKolCategories(categoryList.get(0));//设置分类的名字
			return userInfoVO;
		}
		BeanUtils.copyProperties(userBasePO, userInfoVO);
		return userInfoVO;
	}

	@Override
	public List<GoodsMainVO> getNeedGoodsList(String userId, Integer pageNum, Integer pageSize) {
		/** TODO Auto-generated method stub*/
		PageHelper.startPage(pageNum, pageSize);
		Page<GoodsMainPO> page =  userBaseDAO.queryNeedGoods(userId);
		List<GoodsMainPO> goodsMainPO = page.getResult();
		List<GoodsMainVO> list = new ArrayList<GoodsMainVO>();
		for (int i = 0; i < goodsMainPO.size(); i++) {
			if (goodsMainPO.get(i) != null) {
				GoodsMainVO goods = new GoodsMainVO();
				BeanUtils.copyProperties(goodsMainPO.get(i), goods);
				goods.setNeedPicKey(ImageUtils.firstImages(goodsMainPO.get(i).getTopPicKeys()));
				
				goods.setOnsalePriceStr(CurrencyUtil.fromFenToYuan(goods.getOnsalePrice().toString()));		
				goods.setDiscountPriceStr(CurrencyUtil.fromFenToYuan(goods.getDiscountPrice().toString()));
				
				list.add(goods);
			}
		}	
		return list;
	}

	@Override
	public List<TradeGoodVO> getTradeGoodsList(String userId,Integer pageNum, Integer pageSize) {
		/** TODO Auto-generated method stub*/
		PageHelper.startPage(pageNum, pageSize);
		Page<TradeGoodVO>  page=userBaseDAO.queryTradeGoods(userId);
		for (TradeGoodVO tradeGoodVO : page.getResult()) {//图片取第一张
			tradeGoodVO.setOwnPicKey(ImageUtils.firstImages(tradeGoodVO.getOwnPicKey()));
		}
		
		return page.getResult();
	}

    @Override
    public List<UserBasePO> selectByMobile(String mobile) {
        return userBaseDAO.selectByMobile(mobile);
    }
	
	
	
		
}
