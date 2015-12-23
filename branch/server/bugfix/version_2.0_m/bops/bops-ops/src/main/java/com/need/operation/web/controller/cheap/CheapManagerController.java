package com.need.operation.web.controller.cheap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.need.biz.utils.BizCodeUtil;
import com.need.biz.utils.CurrencyUtil;
import com.need.common.validate.ValidatorUtil;
import com.need.dao.bops.goods.BopsGoodsDAO;
import com.need.domain.common.enums.CheapStatusEnum;
import com.need.domain.common.enums.GoodsTypeEnums;
import com.need.domain.po.bops.cheap.BopsCheapBase;
import com.need.domain.po.bops.goods.BopsGoods;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.cheap.BopsCheapBaseVO;
import com.need.domain.vo.cheap.CheapPageVO;
import com.need.domain.vo.cheap.CheapStatVO;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.constant.UrlMappings;
import com.need.operation.constant.ViewMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.operation.web.controller.kolcategory.KolCategoryManagerController;
import com.need.service.bops.cheap.BopsCheapBaseService;
import com.need.service.constant.BizReturnCode;
import com.need.service.constant.Constants;
import com.need.utils.DateUtil;
/**
 * 
 * <p></p>
 * @author peiboli 2015年11月4日 上午10:22:04
 * @ClassName CheapManagerController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年11月4日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value = ControllerMappings.CHEAP)
public class CheapManagerController {
	@Autowired
	private BopsCheapBaseService bopsCheapservice;
	@Autowired
	private ConstantsProConfig constantsProConfig;
	@Autowired
	private BopsGoodsDAO bopsGoodsDAO;
	
	
	private static final Logger logger = Logger.getLogger(KolCategoryManagerController.class);
	/**
	 * 
	 * @author peiboli 2015年10月26日 下午7:55:31
	 * @Method: getCheapList 
	 * @Description: TODO获得团便宜列表
	 * @param page
	 * @param model
	 * @return 
	 * @throws
	 */
	
	@RequestMapping(value="/page", method = RequestMethod.GET)

	public String getCheapList( CheapPageVO page,Model model) {
         
		logger.info(String.format("getCheapList....in...params:",page));
        List<BopsCheapBaseVO> list= bopsCheapservice.getCheapList(page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		logger.info(String.format("getCheapList....out...params: ", page));
		return ViewMappings.CHEAP_LIST;

	}
	/**
	 * 
	 * @author peiboli 2015年10月26日 下午9:55:21
	 * @Method: addCheap 
	 * @Description: TODO添加团便宜，提交审核
	 * @param page
	 * @param model
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public Message addCheap( CheapPageVO CheapBaseVO,HttpServletRequest request) {
         
		logger.info(String.format("addCheap....in...params:",CheapBaseVO.toString()));
        Message message= Message.success();
        Set<ConstraintViolation<CheapPageVO>> result = ValidatorUtil.getInstance().validate(CheapBaseVO);
		if(result.size()>0){
			for(ConstraintViolation<CheapPageVO> c:result){
				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
			}
		}
		if(CheapBaseVO.getCheapCount() < Constants.MIN_CHEAP_COUNT) {
            return Message.error(BizReturnCode.CHEAP_COUNT_TOO_SMALL);
        }
        BopsCheapBase bopsCheapBase = new BopsCheapBase();
        BeanUtils.copyProperties(CheapBaseVO, bopsCheapBase);
        BopsGoods goods= bopsGoodsDAO.selectByGoodsId(bopsCheapBase.getGoodsId());
        if(GoodsTypeEnums.MORE.code.equals(goods.getGoodsType())){
        	return Message.error(BizReturnCode.CONTAIN_PACK_GOODS);
        }
        bopsCheapBase.setGoodsPicKey(goods.getScenePicKey());
        bopsCheapBase.setDuringTime(bopsCheapBase.getDuringTime()*60*60*1000);
        bopsCheapBase.setOnsalePrice(
        		Integer.parseInt(CurrencyUtil.fromYuanToFen(CheapBaseVO.getOnsalePrice())));
        bopsCheapBase.setCheapPrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(CheapBaseVO.getCheapPrice())));
        try {
			bopsCheapBase.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(CheapBaseVO.getEndTimeString()));
			bopsCheapBase.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(CheapBaseVO.getStartTimeString()));		
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        bopsCheapBase.setBopsUserId(user.getUserId());
        bopsCheapBase.setAuditStatus(CheapStatusEnum.INVALID.status);
        bopsCheapBase.setCheapNo(BizCodeUtil.generateCheapNo(bopsCheapBase.getGoodsId()));
        int inSuccess= bopsCheapservice.addCheap(bopsCheapBase);
        if(inSuccess==1){
        	return message;	
        }else{
        	return Message.error(BizReturnCode.CHEAP_ADD_FAIL);
        }

	}
	/**
	 * 
	 * @author peiboli 2015年10月28日 上午10:42:41
	 * @Method: addCheap 
	 * @Description: TODO提交草稿
	 * @param CheapBaseVO
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public Message saveCheap( CheapPageVO CheapBaseVO,HttpServletRequest request) {
         
		logger.info(String.format("saveCheap....in...params:",CheapBaseVO.toString()));
        Message message= Message.success();
        Set<ConstraintViolation<CheapPageVO>> result = ValidatorUtil.getInstance().validate(CheapBaseVO);
      		if(result.size()>0){
      			for(ConstraintViolation<CheapPageVO> c:result){
      				return Message.error(BizReturnCode.FIELD_VALIDATE_ERR, c.getMessage());
      			}
      		}
		if(CheapBaseVO.getCheapCount() < Constants.MIN_CHEAP_COUNT) {
            return Message.error(BizReturnCode.CHEAP_COUNT_TOO_SMALL);
        }
        BopsCheapBase bopsCheapBase = new BopsCheapBase();
        BeanUtils.copyProperties(CheapBaseVO, bopsCheapBase);
        BopsGoods goods= bopsGoodsDAO.selectByGoodsId(bopsCheapBase.getGoodsId());
        if(goods == null) {
            return Message.error(BizReturnCode.NOT_FIND_GOODS);
        }
        if(bopsCheapBase.getDuringTime() > Constants.MAX_DURATION) {
            return Message.error(BizReturnCode.CHEAP_DURING_TOO_LONG);
        }
        bopsCheapBase.setGoodsPicKey(goods.getScenePicKey());
        bopsCheapBase.setDuringTime(bopsCheapBase.getDuringTime()*60*60*1000);
        bopsCheapBase.setOnsalePrice(
        		Integer.parseInt(CurrencyUtil.fromYuanToFen(CheapBaseVO.getOnsalePrice())));
        bopsCheapBase.setCheapPrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(CheapBaseVO.getCheapPrice())));
        bopsCheapBase.setGoodsPicKey(bopsCheapBase.getCheapPicKey());
        try {
			bopsCheapBase.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(CheapBaseVO.getEndTimeString()));
			bopsCheapBase.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(CheapBaseVO.getStartTimeString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        bopsCheapBase.setBopsUserId(user.getUserId());
        bopsCheapBase.setAuditStatus(CheapStatusEnum.DRAFT.status);
        bopsCheapBase.setCheapNo(BizCodeUtil.generateCheapNo(bopsCheapBase.getGoodsId()));
        int inSuccess= bopsCheapservice.addCheap(bopsCheapBase);
        if(inSuccess==1){
        	return message;	
        }else{
        	return Message.error(BizReturnCode.CHEAP_ADD_FAIL);
        }

	}
	/**
	 * 
	 * @author peiboli 2015年10月28日 上午10:57:09
	 * @Method: toAddCheapPage 
	 * @Description: TODO跳转添加页面
	 * @return 
	 * @throws
	 */
	@RequestMapping(value="/toAddCheapPage", method = RequestMethod.GET)
	public String toAddCheapPage() {
        return ViewMappings.ADD_CHEAP_PAGE;

	}
	/**
	 * 
	 * @author peiboli 2015年10月28日 上午11:08:02
	 * @Method: toAuditPage 
	 * @Description: TODO跳审核页面
	 * @param CheapBaseVO
	 * @param request
	 * @return 
	 * @throws
	 */
	@RequestMapping(value="/toAuditPage/{cheapNo}", method = RequestMethod.GET)
	public String toAuditPage( @PathVariable(value = "cheapNo") String cheapNo,Model model) {
         
		logger.info(String.format("toAuditPage....in...params:",cheapNo));
        BopsCheapBaseVO bopsCheapBase = bopsCheapservice.getCheapByid(cheapNo);
        bopsCheapBase.setCheapPicKey(constantsProConfig.getPic_domain()+bopsCheapBase.getCheapPicKey());
        bopsCheapBase.setDuringTime(DateUtil.milliSecondToHour(bopsCheapBase.getDuringTime()));
        model.addAttribute("cheap", bopsCheapBase);
        return ViewMappings.AUDIT_CHEAP_PAGE;

	}
	/**
	 * 
	 * @author peiboli 2015年10月28日 下午6:36:29
	 * @Method: toCheckPage 
	 * @Description: TODO跳转查看页面
	 * @param cheapNo
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value="/toCheckPage/{cheapNo}", method = RequestMethod.GET)
	public String toCheckPage( @PathVariable(value = "cheapNo") String cheapNo,Model model) {
         
		logger.info(String.format("toCheckPage....in...params:",cheapNo));
        BopsCheapBaseVO bopsCheapBase = bopsCheapservice.getCheapByid(cheapNo);
        bopsCheapBase.setCheapPicKey(constantsProConfig.getPic_domain()+bopsCheapBase.getCheapPicKey());
        bopsCheapBase.setCheapSharePicKey(constantsProConfig.getPic_domain()+bopsCheapBase.getCheapSharePicKey());
        bopsCheapBase.setDuringTime(DateUtil.milliSecondToHour(bopsCheapBase.getDuringTime()));
        model.addAttribute("cheap", bopsCheapBase);
        return ViewMappings.CHECK_CHEAP_PAGE;

	}
	/**
	 * 
	 * @author peiboli 2015年10月28日 下午5:20:13
	 * @Method: toAuditPage 
	 * @Description: TODO跳转编辑页面
	 * @param cheapNo
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value=UrlMappings.TO_CHEAP_EDIT_PAGE, method = RequestMethod.GET)
	public String toeditPage( @PathVariable(value = "cheapNo") String cheapNo,Model model) {
         
		logger.info(String.format("toeditPage....in...params:",cheapNo));
        BopsCheapBaseVO bopsCheapBase = bopsCheapservice.getCheapByid(cheapNo);
        bopsCheapBase.setDuringTime(DateUtil.milliSecondToHour(bopsCheapBase.getDuringTime()));
        model.addAttribute("cheap", bopsCheapBase);
        model.addAttribute("imgUrl", constantsProConfig.getPic_domain());
        return ViewMappings.EDIT_CHEAP_PAGE;

	}
	
	/**
	 * 
	 * @author peiboli 2015年10月28日 下午5:58:11
	 * @Method: Audit 
	 * @Description: TODO审核
	 * @param cheapNo
	 * @param auditStatus
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/audit/{cheapNo}", method = RequestMethod.POST)
	public Message audit(String goodsId,@PathVariable(value = "cheapNo") String cheapNo,String auditStatus, HttpServletRequest request) {
         
		logger.info(String.format("toAuditPage....in...params:",cheapNo));
        BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        Message msg= bopsCheapservice.audit(cheapNo,auditStatus,goodsId,user.getUserId());
        return msg;

	}
	/**
	 * 
	 * @author peiboli 2015年11月7日 下午6:04:41
	 * @Method: frozen 
	 * @Description: TODO冻结操作
	 * @param goodsId
	 * @param cheapNo
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/frozen/{cheapNo}", method = RequestMethod.POST)
	public Message frozen(@PathVariable(value = "cheapNo") String cheapNo, HttpServletRequest request) {
         
		logger.info(String.format("frozen....in...params:",cheapNo));
        BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        Message msg= bopsCheapservice.frozen(cheapNo,user.getUserId());
        return msg;

	}
	/**
	 * 
	 * @author peiboli 2015年10月28日 下午6:47:49
	 * @Method: editSave 
	 * @Description: TODO编辑页提交草稿
	 * @param CheapBaseVO
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/editSave", method = RequestMethod.POST)
	public Message editSave(CheapPageVO CheapBaseVO,HttpServletRequest request) {
         
		logger.info(String.format("toAuditPage....in...params:",CheapBaseVO.toString()));
		if(CheapBaseVO.getCheapCount() < Constants.MIN_CHEAP_COUNT) {
            return Message.error(BizReturnCode.CHEAP_COUNT_TOO_SMALL);
        }
        Message message= Message.success();
        BopsCheapBase bopsCheapBase = new BopsCheapBase();
        BeanUtils.copyProperties(CheapBaseVO, bopsCheapBase);
        BopsGoods goods= bopsGoodsDAO.selectByGoodsId(bopsCheapBase.getGoodsId());
        bopsCheapBase.setGoodsPicKey(goods.getScenePicKey());
        bopsCheapBase.setDuringTime(bopsCheapBase.getDuringTime()*60*60*1000);
        bopsCheapBase.setOnsalePrice(
        		Integer.parseInt(CurrencyUtil.fromYuanToFen(CheapBaseVO.getOnsalePrice())));
        bopsCheapBase.setCheapPrice(Integer.parseInt(CurrencyUtil.fromYuanToFen(CheapBaseVO.getCheapPrice())));
        try {
			bopsCheapBase.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(CheapBaseVO.getEndTimeString()));
			bopsCheapBase.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(CheapBaseVO.getStartTimeString()));
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BopsUser user = (BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
        bopsCheapBase.setBopsUserId(user.getUserId());
        Boolean inSuccess= bopsCheapservice.update(bopsCheapBase);
        
        return message;

	}
	/**
	 * 
	 * @author peiboli 2015年11月9日 下午2:38:15
	 * @Method: cheapStatList 
	 * @Description: TODO团便宜统计
	 * @param param
	 * @param model
	 * @return 
	 * @throws
	 */
	@RequestMapping(value=UrlMappings.CHEAP_STAT, method = RequestMethod.GET)
	public String  cheapStatList(CheapStatVO param,Model model){
		logger.info("cheapStatList in param:"+param.toString());
		
		List<CheapStatVO> list= bopsCheapservice.getCheapStatList(param);
		model.addAttribute("list", list);
		model.addAttribute("page", param);
		return ViewMappings.CHEAP_stat_PAGE;
	}


}
