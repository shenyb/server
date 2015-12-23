package com.need.integration.common.haimeiSdk;

/**
 * 
 * @author 徐纯
 *
 *  2015-6-15 下午03:56:14
 */
public class IscsException extends Exception{
	private static final long serialVersionUID = 1L;
	private String errCode;
    private String errMsg;
    public IscsException(String errCode, String errMsg) {
        super((new StringBuilder()).append(errCode).append(": ").append(errMsg).toString());
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
    public IscsException() {
    }

    public IscsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IscsException(String message) {
        super(message);
    }

    public IscsException(Throwable cause) {
        super(cause);
    }
	public String getErrCode() {
		return errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
}
