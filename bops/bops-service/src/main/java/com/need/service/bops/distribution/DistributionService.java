package com.need.service.bops.distribution;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.need.domain.po.bops.distribution.DistributionGoodsPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.distribution.DistributionEditVO;
import com.need.domain.vo.distribution.DistributionGoodsStatisticsVO;
import com.need.domain.vo.distribution.DistributionListVO;
import com.need.domain.vo.distribution.DistributionPageVO;
import com.need.domain.vo.distribution.DistributionUserStatisticsVO;
import com.need.domain.vo.distribution.DistributionVO;

public interface DistributionService {

	/**
	 * 
	 * @author peiboli 2015年11月30日 下午3:11:35
	 * @Method: exportPriceTemplate 
	 * @Description: TODO下载模板
	 * @return 
	 * @throws
	 */
	XSSFWorkbook exportPriceTemplate();

	/**
	 * 
	 * @author peiboli 2015年11月30日 下午7:42:23
	 * @Method: excelToList 
	 * @Description: TODOexcel转list
	 * @param in
	 * @param is07Or10
	 * @return 
	 * @throws
	 */
	List<DistributionVO> excelToList(InputStream in, Boolean is07Or10);

	/**
	 * 
	 * @author peiboli 2015年11月30日 下午7:42:36
	 * @Method: addDistributionGoods 
	 * @Description: TODO添加分销商品
	 * @param distribution
	 * @return
	 * @throws Exception 
	 * @throws
	 */
	int addDistributionGoods(DistributionVO distribution,DistributionGoodsPO distributionGoods) throws Exception;

	/**
	 * 
	 * @author peiboli 2015年12月1日 上午10:59:45
	 * @Method: getDistributionList 
	 * @Description: TODO获取分销列表
	 * @param params
	 * @return 
	 * @throws
	 */
	List<DistributionListVO> getDistributionList(DistributionPageVO params);

	/**
	 * 
	 * @author peiboli 2015年12月1日 下午4:19:54
	 * @Method: audit 
	 * @Description: TODO分销商品审核
	 * @param id
	 * @return 
	 * @throws
	 */
	Message audit(String id);

	/**
	 * 
	 * @author peiboli 2015年12月1日 下午6:29:16
	 * @Method: frozen 
	 * @Description: TODO冻结
	 * @param id
	 * @return 
	 * @throws
	 */
	Message frozen(String id);

	Message edit(DistributionEditVO params,BopsUser user);

	/**
	 * 
	 * @author peiboli 2015年12月1日 下午7:38:06
	 * @Method: getDistributionById 
	 * @Description: TODO根据id获取详情
	 * @param id
	 * @return 
	 * @throws
	 */
	DistributionListVO getDistributionById(String id);

	/**
	 * 
	 * @author peiboli 2015年12月2日 下午8:50:33
	 * @Method: getDistributionGoodsStatisticsList 
	 * @Description: TODO获得分销商品统计列表
	 * @param params
	 * @return 
	 * @throws
	 */
	List<DistributionGoodsStatisticsVO> getDistributionGoodsStatisticsList(DistributionPageVO params);

	/**
	 * 
	 * @author peiboli 2015年12月3日 上午10:54:43
	 * @Method: getDistributionUserStatisticsList 
	 * @Description: TODO用户佣金统计
	 * @param params
	 * @return 
	 * @throws
	 */
	List<DistributionUserStatisticsVO> getDistributionUserStatisticsList(DistributionPageVO params);

}
