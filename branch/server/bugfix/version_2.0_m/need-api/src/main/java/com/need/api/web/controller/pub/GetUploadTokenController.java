package com.need.api.web.controller.pub;

import com.need.api.constant.ControllerMappings;
import com.need.common.domain.pub.Message;
import com.need.gateway.fileupload.ImageUploadService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
/**
 * 
 * <p></p>
 * @author peiboli 2015年7月29日 上午11:59:08
 * @ClassName GetUploadTokenController
 * @Description TODO获取用户上传头像的Token
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: peiboli 2015年7月29日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(value=ControllerMappings.USER_GET_UPLOAD_TOKEN)
public class GetUploadTokenController {
	private static final Logger logger = Logger.getLogger(GetUploadTokenController.class);

	@Autowired
    private ImageUploadService imageUploadService;
	
	
	@ResponseBody
	@RequestMapping(params = "apiVersion=1.0", method = RequestMethod.GET)
	public Message getUpLoadToken() throws Exception {
		logger.info("in common/getUploadToken :");
		Message message = Message.success();
		String uploadToken= imageUploadService.generateToken();
		if(!StringUtils.isBlank(uploadToken)){
        message.addData("uploadToken", uploadToken); 
		}else{
		message.setCode(8);
		message.setMsg("请求头像Token失败");
		}
		logger.info("out common/getUploadToken :");
		return message;
	}
}
