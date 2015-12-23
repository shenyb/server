package com.need.api.web.controller.trade.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.need.api.constant.ControllerMappings;
import com.need.common.core.constant.BizReturnCode;
import com.need.common.core.dao.jdbc.goods.GoodsMainDAO;
import com.need.common.core.dao.jdbc.trade.TradeBaseDAO;
import com.need.common.domain.po.api.goods.GoodsMainPO;
import com.need.common.domain.po.api.trade.TradeBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.trade.TaxOrderToPayListVO;
import com.need.common.domain.vo.trade.TaxOrderToPayListVO.TaxOrderToPayGoodsVO;
import com.need.utils.StringUtil;
import com.need.web.core.annotion.ParamValidate;
import com.need.web.core.annotion.ParamsValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(ControllerMappings.GET_TAX_TRADE_TOPAY_LIST)
public class GetTaxTradeToPayListController {

    private static final Logger logger = Logger.getLogger(GetTaxTradeToPayListController.class);

    @Autowired
    private GoodsMainDAO goodsMainDAO;
    @Autowired
    private TradeBaseDAO tradeBaseDAO;

    @ParamsValidate(value = {@ParamValidate(name = "warehouseType", notNull = true, code = BizReturnCode.TRADE_WAREHOUSE_TYPE_NULL), @ParamValidate(name = "userId", notNull = true, code = BizReturnCode.USERID_NOT_EXIST), @ParamValidate(name = "orderNoList", notNull = true, code = BizReturnCode.TRADE_NO_NULL)})
    @ResponseBody
    @RequestMapping(params = "apiVersion=1.3", method = RequestMethod.GET)
    public Message getTradePrepayInfo(String warehouseType, String userId, String orderNoList) { //用户交易号
        List<TaxOrderToPayListVO> orderList = new ArrayList<TaxOrderToPayListVO>();
        Message message = Message.success();
        String[] tradeNoArray = JSON.parseObject(orderNoList, new TypeReference<String[]>() {});// 转换对象
        String tradeNos = StringUtil.arrayToFormatStringToSelect(tradeNoArray, ",");
        List<TradeBasePO> list = tradeBaseDAO.selectByTradeNos(tradeNos);
        for (TradeBasePO trade : list) {
            GoodsMainPO goodsMainPO = goodsMainDAO.selectByPrimaryKey(trade.getGoodsId());
            TaxOrderToPayListVO taxOrderToPayListVO = new TaxOrderToPayListVO();
            TaxOrderToPayGoodsVO goods = taxOrderToPayListVO.getGoods();
            //使用用户交易号
            taxOrderToPayListVO.setOrderNo(trade.getTradeNo());
            taxOrderToPayListVO.setBuyPrice(trade.getBuyPrice());
            //页面显示为该订单的支付价格
            taxOrderToPayListVO.setTotalPrice(trade.getPayPrice());
            taxOrderToPayListVO.setBuyCount(trade.getBuyCount());
            goods.setGoodsId(goodsMainPO.getGoodsId());
            goods.setBrief(goodsMainPO.getBrief());
            goods.setGoodsName(goodsMainPO.getGoodsName());
            goods.setTopPicKey(StringUtil.stringToList(goodsMainPO.getTopPicKeys(), ",").get(0));
            goods.setDiscountPrice(goodsMainPO.getDiscountPrice());
            goods.setOnsalePrice(goodsMainPO.getOnsalePrice());
            orderList.add(taxOrderToPayListVO);
        }
        message.addData("orderList", orderList);
        return message;

    }

}
