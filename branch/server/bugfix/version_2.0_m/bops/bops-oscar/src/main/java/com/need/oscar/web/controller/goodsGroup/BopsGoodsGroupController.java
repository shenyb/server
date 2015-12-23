package com.need.oscar.web.controller.goodsGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;

import com.need.biz.utils.BizCode;
import com.need.biz.utils.MD5Util;
import com.need.domain.common.enums.GoodsGroupStatusEnums;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.goods.AuditGoodsVO;
import com.need.domain.vo.goodsgroup.GoodsGroupResultVO;
import com.need.domain.vo.goodsgroup.GoodsGroupVO;
import com.need.oscar.constant.ControllerMappings;
import com.need.service.bops.goodsGroup.GoodsGroupService;
import com.need.service.constant.Constants;
import com.need.utils.DateUtil;

@Controller
@RequestMapping(ControllerMappings.BOPS_GOODS_GROUP)
public class BopsGoodsGroupController {

	private static final Logger logger = Logger.getLogger(BopsGoodsGroupController.class);
	
	@Autowired
	private GoodsGroupService goodsGroupService;

	
	/***
	 * 
	 * @author LXD 2015年11月25日 下午4:23:52
	 * @Method: addGroupGoods 
	 * @Description: TODO 添加商品
	 * @param groupStatus
	 * @param groupBatch
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{groupBatch}")
	public Message getGoodsGroup(@PathVariable String groupBatch) {

		logger.info("in BopsGoodsGroupController addGroupGoods groupBatch: " + groupBatch);

		Message success= goodsGroupService.getGoodsGroupByBatch(groupBatch);
		return success;
	}




}
