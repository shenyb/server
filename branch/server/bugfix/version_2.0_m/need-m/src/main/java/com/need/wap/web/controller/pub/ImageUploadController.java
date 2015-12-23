package com.need.wap.web.controller.pub;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.need.biz.utils.BizCodeUtil;
import com.need.common.core.constant.Constants;
import com.need.common.domain.po.api.user.UserBasePO;
import com.need.common.domain.pub.Message;
import com.need.common.domain.vo.pub.ImageVO;
import com.need.framework.utils.PropertiesUtil;
import com.need.gateway.fileupload.ImageUploadService;
import com.need.utils.UUIDUtils;
import com.need.wap.constant.ControllerMappings;

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
	
	
	
	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
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
	public Message imageUpload(@RequestBody ImageVO image, HttpServletRequest request) throws IOException{
		logger.info("imageUpload in ..");
		Message success = Message.success();
//		if (imageBase64 == null) // 图像数据为空
//			return false;
//		BASE64Decoder decoder = new BASE64Decoder();
		
		List<String> image_list = new ArrayList<String>();
		Date d= new Date();
		SimpleDateFormat sf= new SimpleDateFormat("yyyy/MM/dd");
		String date= sf.format(d);
		String userId= (String)request.getAttribute(Constants.APP_REQUEST_USER_INFO);
		String fileDir=PropertiesUtil.getProperty(Constants.CONSTANTS_PROPERTIES, "fileUploadPath")+date;//设置保存路径
		String fileMaxSize=PropertiesUtil.getProperty(Constants.CONSTANTS_PROPERTIES, "uploadFileMaxSize");//设置保存文件的大小;
		int maxSize=Integer.parseInt(fileMaxSize);
		
		byte[] b = Base64.decodeBase64(image.getImageBase64().split(",")[1]);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		InputStream input = new ByteArrayInputStream(b);
//			String imgFilePath = imgFile;// 新生成的图片
//		    FileUtils.writeByteArrayToFile(new File(fileDir,"/123.jpg"), b);//保存本地
//		    OutputStream out = new FileOutputStream(fileDir+"/123.jpg");
//			out.write(b);
//			out.flush();
//			out.close();
//			MultipartFile file =(MultipartFile) new File(fileDir);
            String fileName= UUIDUtils.getUUID();
//			if (!file.isEmpty()) {
//				//上传图片大小判断
//				if(file.getSize() >maxSize){
//					return Message.error(7);
//				};					
//				try {
//					String fileName = file.getOriginalFilename();
//					String imageType=null;
//					int location =fileName.lastIndexOf(".");
//					if(location!=-1){
//						imageType=fileName.substring(location);
//					}else{
//						 logger.info("imageType is null,set default jpg.");
						String imageType=".jpg";//设置默认后缀
//					}
					String uploadFileName=BizCodeUtil.generateImgKey(userId, fileName);//选取管理员id
					FileUtils.writeByteArrayToFile(new File(fileDir,uploadFileName+imageType), b);//保存本地
					imageUploadService.uploadFileByStream(input, uploadFileName);
					image_list.add(uploadFileName);

//				} catch (IOException e) {
//					return Message.error(1);
//			    }	
//				
//			} else {
//				return Message.error(1);
//			}
		
		logger.info("imageUpload out ..");
		success.addData("fileNames",image_list);
		return success;
	}
	
	
	
	public static void main(String[] args) {
	
	}
}
