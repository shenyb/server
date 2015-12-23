package com.need.common.core.service.goods.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.need.common.core.constant.Constants;
import com.need.common.core.dao.jdbc.distribution.DistributionGoodsDAO;
import com.need.common.core.dao.jdbc.goods.GoodsItemsDAO;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.need.NeedGoodsDAO;
import com.need.common.core.service.goods.GoodsMainService;
import com.need.common.core.service.system.SystemSettingService;
import com.need.common.domain.enums.GoodsTypeEnums;
import com.need.common.domain.enums.WarehouseTypeEnum;
import com.need.common.domain.po.api.goods.GoodsItemsPO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.goods.*;
import com.need.utils.StringUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class GoodsMainServiceImpl implements GoodsMainService {

    private static final Logger logger = Logger.getLogger(GoodsMainServiceImpl.class);

    @Autowired
    private GoodsMainDAO goodsMainDAO;

    @Autowired
    private NeedGoodsDAO needGoodsDAO;
    @Autowired
    private GoodsItemsDAO goodsItemsDAO;
    @Autowired
    private DistributionGoodsDAO distributionGoodsDAO;
    @Autowired
    private SystemSettingService systemSettingService;

    @Override
    public GoodsPageResultVO getGoodsListBySceneId(int sceneId, String userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);//用分页工具对结果进行分页
        Page<GoodsVO> page = (Page<GoodsVO>) goodsMainDAO.queryGoodsListBySceneId(sceneId);
        List<GoodsVO> goodsParamList = page.getResult();
        List<GoodsNeedResultVO> goodsNeedList = new ArrayList<GoodsNeedResultVO>();
        //对得到的商品List进行遍历，并且重新封装成客户端需要的数据格式
        for (GoodsVO goodsParam : goodsParamList) {
            //Addy liyongran 20150920 1.0版本过滤调保税仓商品
            if (this.checkGoodsIsGlobalState(goodsParam.getGoodsId())) {
                logger.info("the goods " + goodsParam.getGoodsId() + " is global goods  1.0 ignore .");
                continue;
            }
            //Addy liyongran 20150920 1.0版本过滤调保税仓商品 end

            GoodsNeedResultVO goods = new GoodsNeedResultVO();
            NeedVO need = new NeedVO();
            goods.setGoods(goodsParam);
            int needCount = needGoodsDAO.getNeedGoodsCount(goodsParam.getGoodsId());//得到该商品的被Need的次数
            String isNeed = needGoodsDAO.countIsNeeded(userId, goodsParam.getGoodsId()).toString().toUpperCase();
            need.setNeedCount(needCount);
            need.setIsNeed(isNeed);
            goods.setNeed(need);
            goodsNeedList.add(goods);//重新组装List
        }
        GoodsPageResultVO goodsPageResultVO = new GoodsPageResultVO();
        goodsPageResultVO.setGoodsList(goodsNeedList);
        goodsPageResultVO.setTotalCount(page.getTotal());

        return goodsPageResultVO;
    }

    @Override
    public Message getGoodsProfileById(String goodsId, String userId) {
        Message message = Message.success();
        GoodsProfileResultVO goods = goodsMainDAO.getGoodsProfileById(goodsId);
        goods.setTopPicKeys(goods.getTopPicKeyString().split(","));
        goods.setTopPicKeyString(null);
        if (StringUtils.hasText(userId)) {
            goods.setIsNeed(needGoodsDAO.countIsNeeded(userId, goodsId).toString().toUpperCase());
        } else {
            goods.setIsNeed(Boolean.FALSE.toString().toUpperCase());
        }
        message.addData("goods", goods);
        return message;
    }

    @Override
    public Message getGoodsProfileById_V1_3(String goodsId, String userId) {
        Message message = Message.success();
        GoodsProfileResultVO goods = goodsMainDAO.getGoodsProfileById_V1_3(goodsId);
        goods.setTopPicKeys(goods.getTopPicKeyString().split(","));
        goods.setTopPicKeyString(null);
        if (StringUtils.hasText(userId)) {
            goods.setIsNeed(needGoodsDAO.countIsNeeded(userId, goodsId).toString().toUpperCase());
        } else {
            goods.setIsNeed(Boolean.FALSE.toString().toUpperCase());
        }
        if (GoodsTypeEnums.MORE.code.equals(goods.getGoodsType())) {
            goods.setIsSoldout(String.valueOf(itemsGoodsIsSoldout(goodsId)).toUpperCase());
            setItemsGoodsPrice(goods);
        }

        message.addData("goods", goods);
        return message;
    }

    @Override
    public boolean itemsGoodsIsSoldout(String goodsId) {
        List<GoodsItemsPO> goodsItemsList = goodsItemsDAO.queryByGoodsGroupId(goodsId);
        if (null != goodsItemsList && goodsItemsList.size() > 0) {
            for (GoodsItemsPO goodsItems : goodsItemsList) {
                if (goodsItems.getGoodsCount() > goodsMainDAO.selectByPrimaryKey(goodsItems.getGoodsId()).getStoreCount()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void setItemsGoodsPrice(GoodsProfileResultVO goods) {
        List<GoodsItemsPO> goodsItemsList = goodsItemsDAO.queryByGoodsGroupId(goods.getGoodsId());
        int onsalePrice = 0;
        int discountPrice = 0;
        if (null != goodsItemsList && goodsItemsList.size() > 0) {
            for (GoodsItemsPO goodsItems : goodsItemsList) {
                onsalePrice = onsalePrice + goodsMainDAO.selectByPrimaryKey(goodsItems.getGoodsId()).getOnsalePrice() * goodsItems.getGoodsCount();
                discountPrice = discountPrice + goodsMainDAO.selectByPrimaryKey(goodsItems.getGoodsId()).getDiscountPrice() * goodsItems.getGoodsCount();
            }
        }
        goods.setOnsalePrice(onsalePrice);
        goods.setDiscountPrice(discountPrice);
    }

    @Override
    public Message getGoodsProfileById_V2_0(String goodsId, String userId) {
        Message message = Message.success();
        GoodsProfileResultVO goods = goodsMainDAO.getGoodsProfileById_V2_0(goodsId);
        goods.setTopPicKeys(goods.getTopPicKeyString().split(","));
        goods.setTopPicKeyString(null);
        //是否营销商品
        // 1后台审核通过
        // 1.1将前台goods表is_share设置为TRUE,默认为FALSE
        // 1.2往前台分销商品表插入记录,记录有效时间和佣金等信息
        //2前台查询是否为分销商品
        // 2.1首先查看商品表的is_share标志位
        // 2.2如果是TRUE,则查看前台分销表有效期和分销状态是否可用
        //distributionGoodsDAO.getByGoodsId该查询已过滤有效期和状态
        if (Constants.TRUE.equals(goods.getIsShare()) && distributionGoodsDAO.getByGoodsId(goodsId) != null) {
            goods.setIsShare(Constants.TRUE);
        }else{
        	 goods.setIsShare(Constants.FALSE);
        }

        //是否新品 (需增加后台逻辑)
        if (goods.getGoodsOnlineTime() != null)

        {
            //超过72小时
            if (Calendar.getInstance().getTime().getTime() - goods.getGoodsOnlineTime().getTime() < Constants.GOODS_TIME_OUT) {
                goods.setIsNew(Boolean.TRUE.toString().toUpperCase());
            } else {
                goods.setIsNew(Boolean.FALSE.toString().toUpperCase());
            }
        } else

        {
            goods.setIsNew(Boolean.FALSE.toString().toUpperCase());
        }


        if (GoodsTypeEnums.MORE.code.equals(goods.getGoodsType()))

        {
            goods.setIsSoldout(String.valueOf(itemsGoodsIsSoldout(goodsId)).toUpperCase());
            setItemsGoodsPrice(goods);
        }

        goods.setAmount(systemSettingService.getTransportLimit(WarehouseTypeEnum.valueOf(goods.getWarehouseType())));

        message.addData("goods", goods);
        return message;
    }

    @Override
    public GoodsResultVO getGoodsById(String goodsId) {
        // TODO Auto-generated method stub
        return goodsMainDAO.getGoodsById(goodsId);
    }

    @Override
    public boolean checkGoodsIsGlobalState(String goodsId) {
        /** TODO Auto-generated method stub*/
        String global = goodsMainDAO.getGoodsGlobal(goodsId);
        if (global == null) {
            return false;
        }
        if (global.equals("TRUE")) {
            return true;
        }
        return false;
    }

    @Override
    public GoodsProfileResultVO getgoodsProfiled(String goodsId) {
        GoodsProfileResultVO goods = goodsMainDAO.getGoodsProfileById_V2_0(goodsId);
        if(goods!=null){
        if(!StringUtil.isBlank(goods.getTopPicKeyString())){
        goods.setTopPicKeys(goods.getTopPicKeyString().split(","));
        }
        }
        return goods;
    }

}
