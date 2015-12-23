/**
 * @ProjectName: need-api
 * @Copyright: 2015 by Beijin Weplanter Technology co.,ltd.
 * @address: http://www.weplanter.com
 * @Description: 本内容仅限于稻田(北京)科技有限公司内部使用，禁止转发.
 * @author Rylan
 * @date: 2015年7月25日 上午11:32:08
 * @Title: UserInfoVo.java
 * @Package com.need.api.web.vo
 * @Description: TODO
 */
package com.need.share.web.controller.kol.vo;

import java.io.Serializable;

/**
 * <p></p>
 * @author Rylan 2015年7月25日 上午11:32:08
 * @ClassName UserInfoVo
 * @Description TODO 用户信息返回层VO,包含用户部分信息和聚合信息
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: Rylan 2015年7月25日
 * @modify by reason:{方法名}:{原因}
 */
public class KolInfoVo implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO 增加可序列化类的一个版本标识
	*/ 
	private static final long serialVersionUID = 5160237382654801796L;

	private String userId;

    private String mobile;

    private String nickName;

    private String userType;
    private String kolCategories;

    private String userStatus;
    
    private String kolBrief;
    private String profilePicKey;
    
    private String kolId;
    
    
	public String getKolCategories() {
		return kolCategories;
	}

	public void setKolCategories(String kolCategories) {
		this.kolCategories = kolCategories;
	}

	public String getProfilePicKey() {
		return profilePicKey;
	}

	public void setProfilePicKey(String profilePicKey) {
		this.profilePicKey = profilePicKey;
	}

	public String getKolBrief() {
		return kolBrief;
	}

	public void setKolBrief(String kolBrief) {
		this.kolBrief = kolBrief;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getKolId() {
		return kolId;
	}

	public void setKolId(String kolId) {
		this.kolId = kolId;
	}

	@Override
	public String toString() {
		return "KolInfoVo [userId=" + userId + ", mobile=" + mobile + ", nickName=" + nickName + ", userType="
				+ userType + ", kolCategories=" + kolCategories + ", userStatus=" + userStatus + ", kolBrief="
				+ kolBrief + ", profilePicKey=" + profilePicKey + ", kolId=" + kolId + "]";
	}

    
    
}
