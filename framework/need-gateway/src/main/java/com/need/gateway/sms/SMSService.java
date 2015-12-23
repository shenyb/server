package com.need.gateway.sms;

import com.need.gateway.param.SMSParam;

/**
 * @author Rylan 2015年7月25日 下午11:42:06
 * @ClassName SMSService
 * @Description TODO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public interface SMSService {

	/**
	 * @author Rylan 2015年7月26日 上午12:58:19
	 * @Method: sendSMSByChuangLan 
	 * @Description: mobiles 支持多个  , 分割。此方法不建议被使用，没有经过分发。建议采用distributeSMSService
	 * @param parma
	 * @return 0:成功  !0 失败 具体见返回错误码
	 * @throws Exception 
	 * @throws
	 */
	@Deprecated
    public int sendSMSByChuangLan(String content,String mobiles) throws Exception ;
	
    /**
     * @author Rylan 2015年9月17日 下午8:57:47
     * @Method: checkChuangLanBalance 
     * @Description: 创蓝检查余额
     * @return 
     * @throws
     */
    public boolean checkChuangLanBalance();
	/**
	 * @author Rylan 2015年9月14日 下午11:40:24
	 * @Method: sendSMSByYunXin 
	 * @Description: 云信短信发送(国内) 此方法不建议被使用，没有经过分发。建议采用distributeSMSService
	 * @param parma
	 * @return
	 * @throws Exception 
	 * @throws
	 */
    @Deprecated
    public int sendSMSByYunXin(String content,String mobile) throws Exception ;
    
    /**
     * @author Rylan 2015年9月14日 下午11:40:48
     * @Method: sendInternationSMSByYunXin 
     * @Description: 云信短信发送(国际)
     * @param parma
     * @return
     * @throws Exception 
     * @throws
     */
    public int sendInternationSMSByYunXin(String content,String mobile) throws Exception ;
    
    /**
     * @author Rylan 2015年9月17日 下午8:58:25
     * @Method: checkYunXinBalance 
     * @Description: 云信余额检查
     * @return 
     * @throws
     */
    public boolean checkYunXinBalance();
    
    /**
     * @author Rylan 2015年10月24日 下午1:14:45
     * @Method: distributeSMSService 
     * @Description: 分发短信服务商
     * @param content
     * @param mobile
     * @return 
     * @throws
     */
    public int distributeSMSService(String content,String mobile);
    
}
