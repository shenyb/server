package com.need.task.service.goods;

import com.need.task.dao.jdbc.bops.goods.BopsPriceChangeDAO;
import com.need.task.dao.jdbc.bops.goods.po.BopsPriceChangeGoodsPO;
import com.need.task.pub.ConstantsProConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p></p>
 * @author Rylan 2015年11月18日 下午9:10:19
 * @ClassName GoodsPriceChangeTask
 * @Description 定时修改商品价格
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年11月18日
 * @modify by reason:{方法名}:{原因}
 */
@Component
public class GoodsPriceChangeTask {
	
	private static final Logger logger = Logger.getLogger(GoodsPriceChangeTask.class);
	
	@Autowired
	private BopsPriceChangeDAO bopsPriceChangeDAO;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private ConstantsProConfig constantsProConfig;
	
	/**
	 * @author Rylan 2015年11月18日 下午9:11:35
	 * @Method: dealGoodsPriceStartTime 
	 * @Description: 定时处理商品开始时间 
	 * @throws
	 */
	//@Scheduled(cron="${goodsPriceStartTime}")
	public void dealGoodsPriceStartTime(){
		logger.info("dealGoodsPriceStartTime in..");
		List<BopsPriceChangeGoodsPO>  startChangeList =bopsPriceChangeDAO.selectChangeStartTimeRecord();
		if(startChangeList==null||startChangeList.size()==0){
			logger.info("startChangeList is null or startChangeList.size() is 0,continue this.");
			startChangeList=null;
			return ;
		}
		logger.info("begin to deal startChangeList.size is :"+startChangeList.size());
		for (BopsPriceChangeGoodsPO bopsPriceChangeGoods : startChangeList) {
			logger.info("begin deal bopsPriceChangeGoods :"+bopsPriceChangeGoods);
			goodsService.updateStartedExcutedAndGoodsPrice(bopsPriceChangeGoods);
		}
		
	}
	
	
	
	/**
	 * @author Rylan 2015年11月18日 下午9:11:50
	 * @Method: dealGoodsPriceEndTime 
	 * @Description: 定时处理商品开始时间 
	 * @throws
	 */
	//@Scheduled(cron="${goodsPriceEndTime}")
	public void dealGoodsPriceEndTime(){
		logger.info("dealGoodsPriceEndTime in..");
		List<BopsPriceChangeGoodsPO>  endChangeList =bopsPriceChangeDAO.selectChangeEndTimeRecord();
		if(endChangeList==null||endChangeList.size()==0){
			logger.info("endChangeList is null or endChangeList.size() is 0,continue this.");
			endChangeList=null;
			return ;
		}
		logger.info("begin to deal endChangeList.size is :"+endChangeList.size());
		for (BopsPriceChangeGoodsPO bopsPriceChangeGoods : endChangeList) {
			goodsService.updateEndedExcutedAndGoodsPrice(bopsPriceChangeGoods);
		}	
		
	}
	
	
	
}
