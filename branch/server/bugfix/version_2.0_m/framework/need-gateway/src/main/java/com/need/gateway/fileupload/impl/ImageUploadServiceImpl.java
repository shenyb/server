package com.need.gateway.fileupload.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.need.framework.utils.MD5Util;
import com.need.framework.utils.PropertiesUtil;
import com.need.gateway.fileupload.ImageUploadService;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;


/**
 * @author Rylan 2015年7月25日 下午2:51:36
 * @ClassName ImageUploadServiceImpl
 * @Description TODO
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
@Service
public class ImageUploadServiceImpl implements ImageUploadService {

	private static final Logger logger = Logger.getLogger(ImageUploadServiceImpl.class);
	
	@Autowired
	private com.need.gateway.pub.ConstantsProConfig constantsProConfig;
	
	@Override
	public String uploadCloudFileByPath(String filePath) {
		try {
			String uptoken = generateUptoken();
			PutExtra extra = new PutExtra();
			String key = MD5Util.generateRandomKey();
			PutRet ret = IoApi.putFile(uptoken, key, filePath, extra);
			if (ret.ok()) {
				return key;
			} else {
				logger.error(String.format("opps, error : %s", ret));
				return null;
			}
		} catch (AuthException | JSONException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 先按照循环上传 后续改为多线程批量上传
	 */
	@Override
	public ArrayList<String> uploadCloudDirectory(String dirPath) {
		ArrayList<String> keys = new ArrayList<String>();
		File parent = new File(dirPath);
		if (parent.isDirectory()) {
			File[] files = parent.listFiles();
			/** 按文件名称排序 返回顺序的key /1.jpg/ */
			Arrays.sort(files, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					String o1Name = o1.getName().split("\\.")[0];
					String o2Name = o2.getName().split("\\.")[0];
					if (Integer.parseInt(o1Name) > Integer.parseInt(o2Name))
						return 1;
					else
						return -1;
				}
			});

			/** 循环 单个上传 */
			for (int i = 0; i < files.length; i++) {
				String key = uploadCloudFileByPath(files[i].getPath());
				String md5 = MD5Util.generateFileMd5(files[i].getPath());
				keys.add(key + "|" + md5);
			}
			return keys;
		} else {
			logger.info(String.format("%s is not a directory", dirPath));
			return keys;
		}
	}

	@Override
	public String uploadCloudFile(File file) {
		try {
			String uptoken = generateUptoken();
			PutExtra extra = new PutExtra();
			String key = MD5Util.generateRandomKey();
			PutRet ret = IoApi.putFile(uptoken, key, file, extra);
			if (ret.ok()) {
				return key;
			} else {
				logger.error(String.format("opps, error : %s", ret));
				return null;
			}
		} catch (AuthException | JSONException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public String uploadFileByStream(InputStream stream, String fileKey) {
		try {
			String uptoken = generateUptoken();
			PutExtra extra = new PutExtra();
			PutRet ret = IoApi.Put(uptoken, fileKey, stream, extra);
			if (ret.ok()) {
				return fileKey;
			} else {
				logger.error(String.format("opps, error : %s", ret));
				return null;
			}
		} catch (AuthException | JSONException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	private String generateUptoken() throws AuthException, JSONException {
		Config.ACCESS_KEY =constantsProConfig.getQiNiuAccessKey();
		Config.SECRET_KEY = constantsProConfig.getQiNiuSecretKey();
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		PutPolicy putPolicy = new PutPolicy(constantsProConfig.getQiNiubucket());
		// 失效期先定位一天
		putPolicy.expires = 86400;
		putPolicy.returnBody = "{\"key\": $(key), \"hash\": $(etag), \"w\": $(imageInfo.width), \"h\": $(imageInfo.height)}";
		String uptoken = putPolicy.token(mac);
		return uptoken;
	}

	@Override
	public String generateToken() {
		try {
			String uptoken = generateUptoken();
			return uptoken;
		} catch (Exception e) {
			logger.debug(String.format("when get the upload token the exception is %s", e.getMessage()));
			return null;
		}

	}

}
