package com.need.operation.web.controller.distribution;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.need.biz.utils.CurrencyUtil;
import com.need.common.validate.ValidatorUtil;
import com.need.domain.po.bops.distribution.DistributionGoodsPO;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.distribution.DistributionEditVO;
import com.need.domain.vo.distribution.DistributionGoodsStatisticsVO;
import com.need.domain.vo.distribution.DistributionListVO;
import com.need.domain.vo.distribution.DistributionPageVO;
import com.need.domain.vo.distribution.DistributionUserStatisticsVO;
import com.need.domain.vo.distribution.DistributionVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.ViewMappings;
import com.need.service.bops.distribution.DistributionService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.utils.StringUtil;

@Controller
@RequestMapping(ControllerMappings.DISTRIBUTION)
public class SocialDistributionController {

	private static final Logger logger= Logger.getLogger(SocialDistributionController.class);
	@Autowired
	private DistributionService distributionService;

	
	/**
	 * 
	 * @author peiboli 2015年12月23日 下午12:01:28
	 * @Method: getDistributionGoods 
	 * @Description: TODO分销商品分页列表
	 * @param params
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value="/page",method= RequestMethod.GET)
	public String getDistributionGoods(DistributionPageVO params,Model model){
		logger.info("getDistributionGoods.....in");
		List<DistributionListVO> list= distributionService.getDistributionList(params);
		model.addAttribute("list", list);
		model.addAttribute("page", params);
		return ViewMappings.DISTRIBUTION_LIST;
	}
	/**
	 * 
	 * @author peiboli 2015年12月23日 下午12:01:42
	 * @Method: addDistributionGoods 
	 * @Description: TODO通过上传的xlsx表添加分销商品
	 * @param files
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/add",method= RequestMethod.POST)
	public Message addDistributionGoods(@RequestParam("files") MultipartFile[] files,  HttpServletRequest request) throws Exception{
		logger.info("addDistributionGoods.....in");
		StringBuilder badGoods= new StringBuilder();
		List<DistributionVO> distributionList= new ArrayList<DistributionVO>();
		DistributionGoodsPO distributionGoods= new DistributionGoodsPO();
		BopsUser user= (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		distributionGoods.setCreatorId(user.getUserId().toString());
		if(StringUtil.isBlank(user.getUserRealName())){
			distributionGoods.setCreatorName("admin");
			distributionGoods.setOperatorName("admin");
		}else{
			distributionGoods.setCreatorName(user.getUserRealName());
			distributionGoods.setOperatorName(user.getUserRealName());
		}
		
		distributionGoods.setOperatorId(user.getUserId().toString());
		
		for (MultipartFile file : files) {
			InputStream in = file.getInputStream();
			Boolean is07Or10 = file.getOriginalFilename().endsWith("xlsx");
			distributionList = distributionService.excelToList(in, is07Or10);

			for (DistributionVO distribution : distributionList) {
				if(!(StringUtil.isBlank(distribution.getGoodsCode())||StringUtil.isBlank(distribution.getCommission())||StringUtil.isBlank(distribution.getStartTimeString())||StringUtil.isBlank(distribution.getEndTimeString()))){

					int isSuccess= distributionService.addDistributionGoods(distribution,distributionGoods);	
				}else{
					if(!StringUtil.isBlank(distribution.getGoodsCode())){
					badGoods.append(distribution.getGoodsCode()+",");
					}
				}
			}				
		}
		
		Message message= Message.success();
		message.addData("badGoods", badGoods.toString());
		return message;
	}
	/**
	 * 
	 * @author peiboli 2015年12月23日 下午12:02:40
	 * @Method: distributiontemplateDownload 
	 * @Description: TODO下载分销模板
	 * @param response 
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.POST, value = "templateDownload")
	public void distributiontemplateDownload(HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		logger.info("distributiontemplateDownload .....in");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			String fileName = "分销商品模板";
			fileName = URLEncoder.encode(fileName, "UTF8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
			response.setContentType("application / vnd.ms-excel; charset=utf-8");
			XSSFWorkbook workbook = distributionService.exportPriceTemplate();
			try {
				workbook.write(os);
			} catch (IOException e) {
				logger.error(String.format("distributiontemplateDownload write error", e.toString()));
				e.printStackTrace();
			}
			os.flush();
		} catch (Exception e) {
			logger.error(String.format("distributiontemplateDownload exportTrade error", e.toString()));
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error(
							String.format("distributiontemplateDownload os close error", e.toString()));
					e.printStackTrace();
				}
			}
		}

	}
	/**
	 * 
	 * @author peiboli 2015年12月1日 下午6:27:22
	 * @Method: audit 
	 * @Description: TODO审核
	 * @param id
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/audit/{id}",method= RequestMethod.POST)
	public Message audit(@PathVariable(value = "id") String id){
		logger.info("audit.....in...params:"+id);
		Message message= distributionService.audit(id);
		return message;
	}
	/**
	 * 
	 * @author peiboli 2015年12月1日 下午6:27:43
	 * @Method: frozen 
	 * @Description: TODO冻结
	 * @param id
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/frozen/{id}",method= RequestMethod.POST)
	public Message frozen(@PathVariable(value = "id") String id){
		logger.info("frozen.....in...params:"+id);
		Message message= distributionService.frozen(id);
		return message;
	}
	/**
	 * 
	 * @author peiboli 2015年12月1日 下午7:34:35
	 * @Method: toEditPage 
	 * @Description: TODO跳转
	 * @param params
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value="/toEditPage/{id}",method= RequestMethod.GET)
	public String toEditPage(@PathVariable(value = "id") String id,Model model){
		logger.info("getDistributionGoods.....in");
		DistributionListVO distribution= distributionService.getDistributionById(id);
		model.addAttribute("distribution", distribution);
		return ViewMappings.DISTRIBUTION_EDIT;
	}
	/**
	 * 
	 * @author peiboli 2015年12月1日 下午7:33:30
	 * @Method: edit 
	 * @Description: TODO编辑
	 * @param params
	 * @param startTimeString
	 * @param endTimeString
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/edit",method= RequestMethod.POST)
	public Message edit(DistributionEditVO params, HttpServletRequest request){
		logger.info("frozen.....in...params:"+params);
		Set<ConstraintViolation<DistributionEditVO>> result = ValidatorUtil.getInstance().validate(params);
		if(result.size()>0){
			for(ConstraintViolation<?> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		if(!(StringUtil.isBlank(params.getStartTimeString())||StringUtil.isBlank(params.getEndTimeString()))){
			try {
				params.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(params.getEndTimeString()));
				params.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(params.getStartTimeString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			return Message.error(BizReturnCode.FIELD_VALIDATE_ERR);
		}
		params.setCommission(String.valueOf(Integer.parseInt(CurrencyUtil.fromYuanToFen(params.getCommission().toString()))));
		BopsUser user= (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		Message message= distributionService.edit(params,user);
		return message;
	}
	/**
	 * 
	 * @author peiboli 2015年12月2日 下午8:23:58
	 * @Method: getDistributionGoodsStatistics 
	 * @Description: TODO分销商品统计
	 * @param params
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value="/goods/page",method= RequestMethod.GET)
	public String getDistributionGoodsStatistics(DistributionPageVO params,Model model){
		logger.info("getDistributionGoodsStatistics.....in");
		List<DistributionGoodsStatisticsVO> list= distributionService.getDistributionGoodsStatisticsList(params);
		model.addAttribute("list", list);
		model.addAttribute("page", params);
		return ViewMappings.DISTRIBUTION_GOODS_STATISTICS_LIST;
		
	}
	/**
	 * 
	 * @author peiboli 2015年12月3日 上午11:33:37
	 * @Method: getDistributionUserStatistics 
	 * @Description: TODO用户佣金统计
	 * @param params
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value="/user/page",method= RequestMethod.GET)
	public String getDistributionUserStatistics(DistributionPageVO params,Model model){
		logger.info("getDistributionUserStatistics....in..params:"+params.getMaxAmount()+";"+params.getMinAmount());
		if(!StringUtil.isBlank(params.getMaxAmount())){
			params.setMaxAmount(String.valueOf(Integer.parseInt(CurrencyUtil.fromYuanToFen(params.getMaxAmount()))));
		}
		if(!StringUtil.isBlank(params.getMinAmount())){
			params.setMinAmount(String.valueOf(Integer.parseInt(CurrencyUtil.fromYuanToFen(params.getMinAmount()))));
		}
		logger.info("getDistributionGoodsStatistics.....in");
		List<DistributionUserStatisticsVO> list= distributionService.getDistributionUserStatisticsList(params);
		model.addAttribute("list", list);
		model.addAttribute("page", params);
		return ViewMappings.DISTRIBUTION_USER_STATISTICS_LIST;
		
	}
}
