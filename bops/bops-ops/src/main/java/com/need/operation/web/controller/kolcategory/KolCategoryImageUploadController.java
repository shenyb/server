package com.need.operation.web.controller.kolcategory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.need.biz.utils.BizCodeUtil;
import com.need.domain.po.bops.user.BopsUser;
import com.need.domain.pub.Message;
import com.need.gateway.fileupload.ImageUploadService;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.constant.Constants;


/**
 * <p>商品图片上传</p>
 * @author Rylan 2015年8月19日 下午3:07:58
 * @ClassName KolCategoryImageUploadController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月19日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.KOL_CATEGORY_IMAGE_UPLOAD)
public class KolCategoryImageUploadController {
	
	private static final Logger logger = Logger.getLogger(KolCategoryImageUploadController.class);
	
	@Autowired
	private ImageUploadService imageUploadService;
	@Autowired
	private ConstantsProConfig constantsProConfig;
			
	/**
	 * @author Rylan 2015年8月11日 上午10:24:18
	 * @Method: imageUpload 
	 * @Description: TODO
	 * @param files
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Message imageUpload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request){
		logger.info("in /kolCategoryImageUpload ");
		
		Message success = Message.success();
		List<String> image_list = new ArrayList<String>();
		String fileDir=request.getSession().getServletContext().getRealPath(Constants.KOL_CATEGORY_UPLOAD_PATH);//设置保存路径
		BopsUser user =(BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				try {
					String fileName = file.getOriginalFilename();
					String imageType=fileName.substring(fileName.lastIndexOf("."));
					String uploadFileName=BizCodeUtil.generateImgKey(user.getUserId().toString(), fileName);//选取管理员id
					FileUtils.writeByteArrayToFile(new File(fileDir,uploadFileName+imageType), file.getBytes());//保存本地
					imageUploadService.uploadFileByStream(file.getInputStream(), uploadFileName);
					image_list.add(uploadFileName);

				} catch (IOException e) {
					return Message.error(1);
			    }		
			} else {
				return Message.error(1);
			}
		}		
		success.addData("fileNames",image_list);
		success.addData("imgurl", constantsProConfig.getPic_domain());
		logger.info("in /kolCategoryImageUpload ");
		return success;
	}
	
	
}
