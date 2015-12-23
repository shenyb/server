/**
 * @ProjectName: need-framework
 * @Copyright: 2015 by Beijin Weplanter Technology co.,ltd.
 * @address: http://www.weplanter.com
 * @Description: 本内容仅限于稻田(北京)科技有限公司内部使用，禁止转发.
 * @author Rylan
 * @date: 2015年7月25日 下午2:50:01
 * @Title: ImageUploadService.java
 * @Package com.need.framework.fileupload
 * @Description: TODO
 */
package com.need.gateway.fileupload;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * <p></p>
 * @author Rylan 2015年7月25日 下午2:50:01
 * @ClassName ImageUploadService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public interface ImageUploadService {

    /**
     * 将文件上传到云存储
     * 
     * @param filePath
     *            文件路径
     * @return
     */
    public String uploadCloudFileByPath(String filePath);
    
    /**
     * 通过文件上传
     * @param file
     * @return
     */
    public String uploadCloudFile(File file);

    /**
     * 将目录下的文件上传到云存储
     * 
     * @param dirPath
     *            路径地址
     * @return key|md5
     */
    public ArrayList<String> uploadCloudDirectory(String dirPath);
    
    /**
     * 通过流上传文件
     * @param stream
     * @param fileKey
     * @return
     */
    public String uploadFileByStream(InputStream stream,String fileKey);
    
    /**
     * 获取上传token
     * @return
     */
    public String generateToken();
	
	
}
