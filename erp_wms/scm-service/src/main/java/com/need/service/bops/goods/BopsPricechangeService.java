package com.need.service.bops.goods;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.need.domain.vo.goods.GoodsPriceChangeParamVO;
import com.need.domain.vo.goods.GoodsPriceChangeResultVO;
import com.need.domain.vo.goods.GoodsPriceChangeVO;
import com.need.domain.vo.goods.GoodsPriceDetailParamVO;
import com.need.domain.vo.goods.GoodsPriceDetailResultVO;
import com.need.domain.vo.goods.GoodsPriceResultVO;

public interface BopsPricechangeService {

	public int savePricechange(List<GoodsPriceChangeVO> priceList, int userId, String pricechangeType, String startTime,
			String endTime, String mark);

	public List<GoodsPriceChangeResultVO> queryPriceChange(GoodsPriceChangeParamVO goodsPriceChangeParamVO);

	public GoodsPriceChangeResultVO getPriceChangeById(int pricechangeId);

	public List<GoodsPriceResultVO> queryPriceList(int pricechangeId);

	public List<GoodsPriceDetailResultVO> queryGoodsPriceDetailsList(GoodsPriceDetailParamVO param);

	public int updateAudit(String audit, Integer pricechangeId, Integer auditorId);
	
	public XSSFWorkbook exportPriceTemplate();

	public int updateExecute(String excuted, Integer pricechangeId);
}
