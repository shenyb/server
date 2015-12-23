package com.need.common.domain.vo.need;

import java.io.Serializable;
/**
 * 
 * <p></p>
 * @author LXD 2015年8月12日 下午5:14:36
 * @ClassName FeedKolVO
 * @Description TODO 首页feed行家VO
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: LXD 2015年8月12日
 * @modify by reason:{方法名}:{原因}
 */
public class FeedKolVO  implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 6240521229945367462L;
	
	private String nickName;
	private String profilePicKey;
	private String kolId;
	
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProfilePicKey() {
		return profilePicKey;
	}
	public void setProfilePicKey(String profilePicKey) {
		this.profilePicKey = profilePicKey;
	}
	public String getKolId() {
		return kolId;
	}
	public void setKolId(String kolId) {
		this.kolId = kolId;
	}
	
	
	
}
