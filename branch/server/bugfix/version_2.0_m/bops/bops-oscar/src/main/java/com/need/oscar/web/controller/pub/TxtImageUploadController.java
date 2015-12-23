package com.need.oscar.web.controller.pub;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.need.biz.utils.BizCodeUtil;
import com.need.domain.po.bops.user.BopsUser;
import com.need.framework.utils.PropertiesUtil;
import com.need.gateway.fileupload.ImageUploadService;
import com.need.oscar.constant.ControllerMappings;
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
@RequestMapping(ControllerMappings.TXT_IMAGE_UPLOAD)
public class TxtImageUploadController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ImageUploadService imageUploadService;
	
	
	
	/**
	 * @throws IOException 
	 * 
	 * @author LXD 2015年8月15日 上午11:40:17
	 * @Method: textImageUpload 
	 * @Description: TODO 富文本图片上传 需整理
	 * @param files
	 * @param request
	 * @return 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public void textImageUpload(HttpServletRequest request,HttpServletResponse respone,HttpSession session) throws IOException{
			respone.setHeader("Content-type","text/html;charset=UTF-8");
			String path = session.getServletContext().getRealPath("/")+"/app/script/lib/ueditor/jsp/config.json";
			logger.info("config的路径"+path);
			PrintWriter writer = null;
			try {
				writer =respone.getWriter(); 
				String resultString= FileUtils.readFileToString(new File(path));
				System.out.println(resultString);
				writer.write(resultString);
				
			} catch (IOException e) {
				logger.error(e);
				e.printStackTrace();
			}finally{
				if(writer!=null){
			     writer.close();
				}
			}
		}
		
	
	/**
	 * @throws IOException 
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
	public void imageUpload(@RequestParam("file") MultipartFile file,HttpServletResponse respone, HttpServletRequest request) throws IOException{
		respone.setHeader("Content-type","text/html;charset=UTF-8");
		String fileDir=PropertiesUtil.getProperty(Constants.CONSTANTS_PROPERTIES, "fileUploadPath");//设置保存路径
		String picDomain=PropertiesUtil.getProperty(Constants.CONSTANTS_PROPERTIES, "pic_domain");//图片域名 modify by liyongran 201050828
		PrintWriter writer = null;
		BopsUser user =(BopsUser) request.getAttribute(Constants.WEB_REQUEST_USER_INFO);
			if (!file.isEmpty()) {
				try {
					String fileName = file.getOriginalFilename();
					String imageType=fileName.substring(fileName.lastIndexOf("."));
					String uploadFileName=BizCodeUtil.generateImgKey(user.getUserId().toString(), fileName);//选取管理员id
					FileUtils.writeByteArrayToFile(new File(fileDir,uploadFileName+imageType), file.getBytes());//保存本地
					imageUploadService.uploadFileByStream(file.getInputStream(), uploadFileName);
					JSONObject jsonObject = new JSONObject();
					
					jsonObject.put("url", picDomain+uploadFileName);//modify by liyongran 201050828					
					jsonObject.put("title", uploadFileName);
					jsonObject.put("original", file.getOriginalFilename());
					jsonObject.put("state", "SUCCESS");
					writer=respone.getWriter();
					writer.write(jsonObject.toJSONString());	
				} catch (IOException e) {
					logger.error(e);
			    }finally{
			    	if(writer!=null){
			    	writer.close();
			    	}
			    	
			    }		
			} else {
				
			}
		
		
		
		
	}
}
