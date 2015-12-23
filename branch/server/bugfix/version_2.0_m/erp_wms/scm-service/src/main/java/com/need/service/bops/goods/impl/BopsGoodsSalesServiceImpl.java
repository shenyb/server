package com.need.service.bops.goods.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.need.dao.api.trade.TradeBaseDAO;
import com.need.dao.bops.goods.BopsGoodsSalesDAO;
import com.need.domain.po.bops.goods.BopsGoodsSales;
import com.need.domain.vo.goods.BopsGoodsSalesVO;
@Service
public class BopsGoodsSalesServiceImpl {
	@Autowired
	BopsGoodsSalesDAO bopsGoodsSalesDAO;
	@Autowired
	TradeBaseDAO tradeBaseDAO;
	@Transactional
	public void saveGoodsSales(){
		bopsGoodsSalesDAO.deleteAll();
		BopsGoodsSalesVO bopsGoodsSalesVO = new BopsGoodsSalesVO();
		int sevenDay=-7;
		int fifteenDay =-15;
		int thirtyDay=-30;
		int sixtyDay=-60;
		int ninetyDay=-90;
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.add(Calendar.DAY_OF_MONTH, -1);
        bopsGoodsSalesVO.setEndDate(c.getTime());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.DAY_OF_MONTH, sevenDay-1);
        List<BopsGoodsSales> list=null;
        bopsGoodsSalesVO.setServenDate(c.getTime());
        list = tradeBaseDAO.queryGoodsSales(bopsGoodsSalesVO);
        insertGoodsSales(list);
        c.add(Calendar.DAY_OF_MONTH, fifteenDay-1);
        bopsGoodsSalesVO.setServenDate(null);
        bopsGoodsSalesVO.setFifteenDate(c.getTime());
        list = tradeBaseDAO.queryGoodsSales(bopsGoodsSalesVO);
        insertGoodsSales(list);
        c.add(Calendar.DAY_OF_MONTH, thirtyDay-1);
        bopsGoodsSalesVO.setFifteenDate(null);
        bopsGoodsSalesVO.setThirtyDate(c.getTime());
        list = tradeBaseDAO.queryGoodsSales(bopsGoodsSalesVO);
        insertGoodsSales(list);
        
        c.add(Calendar.DAY_OF_MONTH, sixtyDay-1);
        bopsGoodsSalesVO.setThirtyDate(null);
        bopsGoodsSalesVO.setSixtyDate(c.getTime());
        list = tradeBaseDAO.queryGoodsSales(bopsGoodsSalesVO);
        insertGoodsSales(list);
        c.add(Calendar.DAY_OF_MONTH, ninetyDay-1);
        bopsGoodsSalesVO.setSixtyDate(null);
        bopsGoodsSalesVO.setNinetyDate(c.getTime());
        list = tradeBaseDAO.queryGoodsSales(bopsGoodsSalesVO);
        insertGoodsSales(list);
        
        
        
	}
	public void insertGoodsSales(List<BopsGoodsSales> list){
		if(list!=null && list.size()>0){
			for(BopsGoodsSales bopsGoodsSales:list){
				BopsGoodsSales temp= bopsGoodsSalesDAO.selectByPrimaryKey(bopsGoodsSales.getGoodsId());
				if(temp!=null){
					bopsGoodsSalesDAO.updateByPrimaryKeySelective(bopsGoodsSales);
				}else{
					bopsGoodsSalesDAO.insert(bopsGoodsSales);
				}
			}
		}
	}
}
