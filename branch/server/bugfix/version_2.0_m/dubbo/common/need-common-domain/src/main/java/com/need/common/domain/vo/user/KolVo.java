package com.need.common.domain.vo.user;

import java.io.Serializable;

public class KolVo implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -4091869604688794706L;

	private String kolId;
	private String nickName;
	private String kolBrief;
	private String profilePicKey;
	private String kolCertification;
	
	public String getKolCertification() {
		return kolCertification;
	}
	public void setKolCertification(String kolCertification) {
		this.kolCertification = kolCertification;
	}
	public String getKolId() {
		return kolId;
	}
	public void setKolId(String kolId) {
		this.kolId = kolId;
	}

	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getKolBrief() {
		return kolBrief;
	}
	public void setKolBrief(String kolBrief) {
		this.kolBrief = kolBrief;
	}

	
	
}
