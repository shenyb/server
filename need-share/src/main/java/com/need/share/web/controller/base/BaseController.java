package com.need.share.web.controller.base;


import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.need.share.common.enums.ShareTypeEnum;
import com.need.share.constant.Constants;
import com.need.share.constant.ResponseCode;
import com.need.share.service.base.BaseService;
import com.need.share.util.DateUtil;
import com.need.share.util.FreemarkerUtil;
import com.need.share.util.PropertiesUtil;

import freemarker.template.SimpleHash;

/**
 * 
 * 类BaseController.java的实现描述：TODO 类实现描述 
 * @author sunwenjuan 2015年3月10日 下午2:53:37
 */
@Controller
public class BaseController implements ResponseCode{
	/**日志*/
	protected final Logger logger = Logger.getLogger(getClass());
    /**七牛图片域名*/
    public static final String imageHost = PropertiesUtil.getProperty(Constants.PIC_DOMAIN_KEY, "pic_domain");
    /**项目运行时目录*/
    public static final String SPRING_WEB_ROOT = System.getProperty("springmvc.root")+File.separator;

    
    /**
     * 根据指定类型 对应FreeMarker模板生成对应的html文件
     * @param baseService
     * @param refId 类型对应的对象id
     * @param dayUpdated 是否日更新
     * @param shareType 分享类型
     * @return
     * @throws IOException
     */
    public String makeHtmlByFtl(BaseService baseService,String refId,boolean dayUpdated,ShareTypeEnum shareType,String ftlName) throws IOException{
        //获取动态路径部分
        String pathDynamic = "";
        String dirName = "";
        if(dayUpdated){
            String[] yearMonthDay = DateUtil.getYearMonthDaysByNow();
            dirName=yearMonthDay[0]+File.separator+yearMonthDay[1]+File.separator+yearMonthDay[2];
            pathDynamic = dirName+File.separator+refId+Constants.HTML_SUFFIX;
        }else{
            dirName = shareType.getLowerValue();
            pathDynamic = dirName+File.separator+refId+Constants.HTML_SUFFIX;
        }
        String workPagePrePath = shareType.getSharePathPre();
        File shareDir = new File(SPRING_WEB_ROOT+workPagePrePath+dirName);
        if(!shareDir.exists())
            shareDir.mkdirs();
        File feedIdHtml = new File(SPRING_WEB_ROOT+workPagePrePath+pathDynamic);
        if(!feedIdHtml.exists()){
            SimpleHash sh = baseService.makeContent(refId);
            sh.put("imageHost", imageHost);
            FreemarkerUtil.createHtml(SPRING_WEB_ROOT,sh, ftlName,workPagePrePath+pathDynamic);
        }
        return pathDynamic;
    }
    
    
    
    
    /**
     * 根据指定类型 对应FreeMarker模板生成对应的html文件
     * @param baseService
     * @param refId 类型对应的对象id
     * @param dayUpdated 是否日更新
     * @param shareType 分享类型
     * @return
     * @throws IOException
     */
    public String makeHtmlByFtl(BaseService<?> baseService,String refId,boolean dayUpdated,ShareTypeEnum shareType,String ftlName,String ... otherStr) throws IOException{
        //获取动态路径部分
        String pathDynamic = "";
        String dirName = "";
        if(dayUpdated){
            String[] yearMonthDay = DateUtil.getYearMonthDaysByNow();
            dirName=yearMonthDay[0]+File.separator+yearMonthDay[1]+File.separator+yearMonthDay[2];
            pathDynamic = dirName+File.separator+refId+Constants.HTML_SUFFIX;
        }else{
            dirName = shareType.getLowerValue();
            pathDynamic = dirName+File.separator+refId+Constants.HTML_SUFFIX;
        }
        String workPagePrePath = shareType.getSharePathPre();
        File shareDir = new File(SPRING_WEB_ROOT+workPagePrePath+dirName);
        if(!shareDir.exists())
            shareDir.mkdirs();
        File feedIdHtml = new File(SPRING_WEB_ROOT+workPagePrePath+pathDynamic);
        if(!feedIdHtml.exists()){
            SimpleHash sh = baseService.makeContent(refId,otherStr);
            sh.put("imageHost", imageHost);
            FreemarkerUtil.createHtml(SPRING_WEB_ROOT,sh, ftlName,workPagePrePath+pathDynamic);
        }
        return pathDynamic;
    }
    
}
