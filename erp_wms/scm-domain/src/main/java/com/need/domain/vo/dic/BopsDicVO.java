package com.need.domain.vo.dic;

import java.io.Serializable;

public class BopsDicVO implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 8188111223581019755L;
	
	   private String codeValue;

	    private String codeName;

		public String getCodeValue() {
			return codeValue;
		}

		public void setCodeValue(String codeValue) {
			this.codeValue = codeValue;
		}

		public String getCodeName() {
			return codeName;
		}

		public void setCodeName(String codeName) {
			this.codeName = codeName;
		}
	    
}
