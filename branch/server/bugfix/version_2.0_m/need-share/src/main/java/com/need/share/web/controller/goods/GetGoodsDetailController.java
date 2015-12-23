package com.need.share.web.controller.goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.set.SynchronizedSortedSet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StandardSocketFactory;
import com.need.biz.utils.CurrencyUtil;
import com.need.framework.utils.ImageUtils;
import com.need.share.constant.Constants;
import com.need.share.constant.ControllerMappings;
import com.need.share.constant.ViewMappings;
import com.need.share.dao.jdbc.api.goods.GoodsDetailDAO;
import com.need.share.dao.jdbc.api.goods.GoodsMainDAO;
import com.need.share.dao.jdbc.api.goods.po.GoodsDetailPO;
import com.need.share.dao.jdbc.api.trade.TradeJudgementDAO;
import com.need.share.pub.Message;
import com.need.share.util.PropertiesUtil;
import com.need.share.web.controller.goods.vo.GoodsIdAndUpdateTimeVo;
import com.need.share.web.controller.goods.vo.GoodsJudgementListVO;
import com.need.share.web.controller.goods.vo.GoodsResultVO;
import com.need.utils.DateUtil;

/**
 * <p>获取商品详情</p>
 * @author Rylan 2015年8月23日 下午8:23:31
 * @ClassName GetGoodsDetailController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月23日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value=ControllerMappings.WEB_GOODS_DETAIL)
public class GetGoodsDetailController {
	
	private static final Logger logger = Logger.getLogger(GetGoodsDetailController.class);
	
	@Autowired
	private GoodsMainDAO goodsMainDAO;
	@Autowired
	private TradeJudgementDAO tradeJudgementDAO;
	@Autowired
	private GoodsDetailDAO  goodsDetailDAO;
	/**
     * @author Rylan 2015年8月23日 下午4:30:40
     * @Method: getGoodsDetail 
     * @Description: TODO
     * @param goodsId
     * @return 
     * @throws
     */
	@RequestMapping(value="/{goodsId}",method = RequestMethod.GET)
	public String getGoodsDetail(@PathVariable String goodsId,Model model) {
		logger.debug("getGoodsDetail in.. goodsId :"+goodsId);  		
		
		GoodsResultVO  goodsResult =goodsMainDAO.selectByGoodId(goodsId);//查询商品基本信息
		if(goodsResult==null){
			return ViewMappings.GOODS_DETAIL;
		}
		goodsResult.setDiscountPriceStr(CurrencyUtil.fromFenToYuan(goodsResult.getDiscountPrice().toString()));//设置分到元的转换
		goodsResult.setOnsalePriceStr(CurrencyUtil.fromFenToYuan(goodsResult.getOnsalePrice().toString()));
		
		GoodsDetailPO goodsDetail =goodsDetailDAO.getGoodsDetailByGoodsId(goodsId);//查询商品详情
		if(goodsDetail!=null){
			goodsResult.setDetailPicKeys(ImageUtils.imageSplit(goodsDetail.getDetailPicKeys()));
		}
		
		model.addAttribute("goodsInfo", goodsResult);
		PageHelper.startPage(1, 3);
		Page<GoodsJudgementListVO> page= tradeJudgementDAO.queryJudgementByGoodsId(goodsId);
		model.addAttribute("judeList", page.getResult());
		model.addAttribute("total", page.getTotal());
		
		model.addAttribute("picDomain", PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "pic_domain"));
		
		return ViewMappings.GOODS_DETAIL;
	}
	
	
	/**
	 * @author Rylan 2015年10月31日 上午10:56:21
	 * @Method: checkGoodsInfo 
	 * @Description: 专题分享页,重新检查商品基本信息。防止商品价格不一致
	 * @param goodsId
	 * @param model
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/checkGoodsInfo",method = RequestMethod.POST)
	public Message checkGoodsInfo(@RequestBody GoodsIdAndUpdateTimeVo  vo ,Model model) {
		String[] goodsIdAndUpdateTime =vo.getGoodsIdAndUpdateTime();
		logger.info("checkGoodsInfo in.. goodsIdAndUpdateTime :"+goodsIdAndUpdateTime);  		
		Message success=Message.success();
		if(goodsIdAndUpdateTime.length==0){
			return success;
		}
		System.out.println(goodsIdAndUpdateTime.length);
		List<GoodsResultVO> list =new ArrayList<GoodsResultVO>();
		for (int i = 0; i < goodsIdAndUpdateTime.length; i++) {
			logger.info("goodsIdAndUpdateTime : "+goodsIdAndUpdateTime[i]);
			String tmp[]=goodsIdAndUpdateTime[i].split("-");		
			String goodsId=tmp[0];//商品id
			String updateTime=tmp[1];//更新时间	
			try {
				Date updateTimeDate=new Date(Integer.parseInt(updateTime) * 1000l);//更新时间
				logger.info("goodsId :"+goodsId+" , updateTime :"+updateTimeDate.toLocaleString()); 
				GoodsResultVO goodsResultVO=goodsMainDAO.selectByGoodIdAndUpdateTime(goodsId, updateTimeDate);
				if(goodsResultVO==null){
					continue;
				}
				goodsResultVO.setGoodsTopPics(ImageUtils.imageSplit(goodsResultVO.getTopPicKeys()));
				list.add(goodsResultVO);
				
			} catch (Exception e) {
				/** TODO Auto-generated catch block */
				
				e.printStackTrace();
				return success;
			}		
			
		}
		logger.info("list.size() :"+list.size());  	
		if(list.size()>0){
			success.addData("list", list);	
		}				
		return success;
	}
	
	public static void main(String[] args) {
			  
		  String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(1446533155 * 1000l));
		  System.out.println(date);
		  
		 
		  
		  
	}
	
	

}
