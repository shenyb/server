package com.need.operation.web.controller.ops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.need.domain.po.bops.basedata.GoodsSceneResultPO;
import com.need.domain.po.bops.goods.BopsGoodsScene;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.domain.vo.ops.GoodsSceneParam;
import com.need.domain.vo.ops.GoodsSceneSearchParam;
import com.need.operation.constant.ControllerMappings;
import com.need.service.bops.ops.GoodsSceneService;
import com.need.service.constant.Constants;
import com.need.utils.StringUtil;

/**
 * <p>商品场景处理Controller</p>
 * @author Rylan 2015年8月10日 上午1:07:44
 * @ClassName GoodsSceneManagerController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月10日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value=ControllerMappings.GOODS_SCENE)
public class GoodsSceneManagerController {
	
	private static final Logger logger = Logger.getLogger(GoodsSceneManagerController.class);
	
	@Autowired
	private GoodsSceneService goodsSceneService;
	
	/**
	 * 
	 * @author peiboli 2015年8月6日 上午10:46:51
	 * @Method: selectGoodsSceneList 
	 * @Description: TODO根据搜索条件获取商品场景管理列表   modify  by liyongran 20150810
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Message searchGoodsScene(GoodsSceneSearchParam goodsSceneSearchParam){
		logger.info("searchGoodsScene in....goodsSceneSearchParam :"+goodsSceneSearchParam);
		Message message =Message.success();
		List<GoodsSceneResultPO> list= goodsSceneService.searchGoodsNameOrGoodsCode(goodsSceneSearchParam);	
		message.addData("list", list);
		message.addData("page", goodsSceneSearchParam);
		return message;
		
	}
	/**
	 * @author Rylan 2015年8月10日 上午1:10:44
	 * @Method: addGoodsScene 
	 * @Description: TODO 
	 * @param bopsGoodsScene
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public Message addGoodsScene(@RequestBody GoodsSceneParam goodsSceneParam,HttpServletRequest request){
		logger.info(String.format("addGoodsScene in....goodsSceneParam: %s", JSON.toJSONString(goodsSceneParam)));
		Message message = Message.success();
		BopsUser user =(BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		if(goodsSceneParam.getSenceIds()==null||goodsSceneParam.getGoodsIds()==null){
			Message errorMessage=Message.error(3009);
			return errorMessage;
			
		}
		List<String> scenesIdList=Arrays.asList(goodsSceneParam.getSenceIds());//场景ids
		List<String> goodsIdList=Arrays.asList(goodsSceneParam.getGoodsIds());//商品ids
		List<String> goodSceneIdList=new ArrayList<String>();//记录ids
		List<String> sceneIdList=new ArrayList<String>();//记录ids
		goodsSceneService.deleteGoodsSceneBygoodsId(goodsIdList.get(0));
		for (String sceneId : scenesIdList) { //大循环在内，小循环在外。商品集合会大一些
			Integer intSceneId=Integer.parseInt(sceneId);
			BopsGoodsScene bopsGoodsScene=new BopsGoodsScene();
			bopsGoodsScene.setPublisherId(user.getUserId());//提交者
			
			for (int i = 0; i < goodsIdList.size(); i++) {
				bopsGoodsScene.setId(null);//每次清空一次，避免下次有id了
				bopsGoodsScene.setGoodsId(goodsIdList.get(i));//设置商品id
				//检测该商品是否有该场景记录
				/*BopsGoodsScene bopsGoodsSceneExisted=goodsSceneService.checkIsAdd(goodsIdList.get(i), intSceneId);
				if(bopsGoodsSceneExisted!=null){//检测商品是否存在
					Message errorMessage=Message.error(5001);
					errorMessage.addData("object", bopsGoodsSceneExisted);
					return errorMessage;
					continue;
				}*/
				bopsGoodsScene.setSceneId(intSceneId);
				//bopsGoodsScene.setScenePicKey(goodsSceneParam.getScenePicKeys()[i]);
				goodsSceneService.addGoodsScene(bopsGoodsScene);
				goodSceneIdList.add(bopsGoodsScene.getGoodsId());
				
			}
			sceneIdList.add(intSceneId.toString());
		}	
		String goodsIds=StringUtil.arrayToFormatString(goodSceneIdList.toArray(), ",");	
		String sceneIds=StringUtil.arrayToFormatString(sceneIdList.toArray(), ",");
		if(goodSceneIdList!=null&&goodSceneIdList.size()>0){
		message.addData("list", goodsSceneService.getGoodsSceneDetail(goodsIds,sceneIds));
		}else{
		message.addData("list", goodsSceneService.getGoodsSceneByGoodsId(goodsIdList.get(0)));
			
		}
		return message;
		
	}
	/**
	 * 
	 * @author peiboli 2015年8月5日 下午10:06:25
	 * @Method: deleteGoodsScene 
	 * @Description: TODO删除
	 * @param goodsId
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public Message deleteGoodsScene(@PathVariable(value="id") String id){
		logger.info("deleteBopsKolCategory in....");
		Message message = Message.success();
		goodsSceneService.deleteGoodsScene(id);
		goodsSceneService.deleteApiGoodsScene(id);
		return message;		
	}
	/**
	 * 
	 * @author peiboli 2015年8月5日 下午10:06:37
	 * @Method: updateGoodsScene 
	 * @Description: TODO 更新数据
	 * @param bopsGoodsScene
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value = "/{id}")
	public Message updateGoodsScene(@RequestBody BopsGoodsScene bopsGoodsScene){
		logger.info("updateGoodsScene in....");
		Message message = Message.success();
		goodsSceneService.updateGoodsScene(bopsGoodsScene);
		goodsSceneService.updateApiGoodsScene(bopsGoodsScene);
		return message;
		
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT,value = "/audit/{id}")
	public Message auditGoodsScene(@RequestBody BopsGoodsScene bopsGoodsScene,HttpServletRequest request){
		logger.info("updateGoodsScene in....");
		Message message = Message.success();
		BopsUser user= (BopsUser)request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		bopsGoodsScene.setAuditorId(user.getUserId());
		if("SUCCESS".equals(bopsGoodsScene.getAuditStatus())){
			goodsSceneService.addApiGoodsScene(bopsGoodsScene);
		}
		goodsSceneService.updateGoodsScene(bopsGoodsScene);

		return message;
		
	}
	
	
	/**
	 * @author Rylan 2015年8月10日 下午5:26:42
	 * @Method: getGoodsScene 
	 * @Description: TODO
	 * @param goodsSceneSearchParam
	 * @return 
	 * @throws
	 */
	/*@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/{id}")
	public Message getGoodsScene(@PathVariable(value = "id")Integer id){
		logger.info("getGoodsScene in....id :"+id);
		Message message = new Message();
		message.addData("object", goodsSceneService.getGoodsScene(id));
		return message;
		
	}*/
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="/{goodsId}")
	public Message viewGoodsScene(@PathVariable(value = "goodsId")String  goodsId){
		logger.info("getGoodsScene in....goodsId :"+goodsId);
		Message message = Message.success();
		message.addData("list", goodsSceneService.getGoodsSceneByGoodsId(goodsId));
		return message;
		
	}
	
	
	
	
	
	

}
