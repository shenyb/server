package com.need.operation.web.controller.pub;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.need.framework.utils.PropertiesUtil;
import com.need.gateway.fileupload.ImageUploadService;
import com.need.operation.constant.ControllerMappings;
import com.need.operation.pub.ConstantsProConfig;
import com.need.service.constant.Constants;

/**
 * <p></p>
 * @author Rylan 2015年8月11日 上午10:24:01
 * @ClassName ImageUploadController
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年8月11日
 * @modify by reason:{方法名}:{原因}
 */
@Controller
@RequestMapping(ControllerMappings.PUBLIC_IMAGE_UPLOAD)
public class ImageUploadController {

	Logger logger = Logger.getLogger(this.getClass());
	
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
		logger.info("imageUpload in ..");
		Message success = Message.success();
		Date d= new Date();
		SimpleDateFormat sf= new SimpleDateFormat("yyyy/MM/dd");
		String date= sf.format(d);
		List<String> image_list = new ArrayList<String>();	
		String fileDir=PropertiesUtil.getProperty(Constants.CONSTANTS_PROPERTIES, "fileUploadPath")+date;//设置保存路径
		BopsUser user =(BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
		String fileMaxSize=PropertiesUtil.getProperty(Constants.CONSTANTS_PROPERTIES, "uploadFileMaxSize");//设置保存文件的大小;
		int maxSize=Integer.parseInt(fileMaxSize);
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				//上传图片大小判断
				if(file.getSize() >maxSize){
					return Message.error(7);
				};					
				try {
					String fileName = file.getOriginalFilename();
					String imageType=null;
					int location =fileName.lastIndexOf(".");
					if(location!=-1){
						imageType=fileName.substring(location);
					}else{
						 logger.info("imageType is null,set default jpg.");
						imageType=".jpg";//设置默认后缀
					}
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
		logger.info("imageUpload out ..");
		success.addData("fileNames",image_list);
		success.addData("imgurl", constantsProConfig.getPic_domain());
		return success;
	}
	
	
	
	public static void main(String[] args) {
	
	}
}
