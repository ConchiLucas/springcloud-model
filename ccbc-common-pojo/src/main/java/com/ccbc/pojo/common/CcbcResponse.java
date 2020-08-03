package com.ccbc.pojo.common;

import java.io.Serializable;


/**
 * 此类描述的是：业务组件系统对外提供的服务响应标头信息
 * 
 * @author: wangshifa
 * @version: 2016年7月4日 下午4:01:20
 */
public abstract class CcbcResponse implements Serializable {
    /** 
     * 
     */
    private static final long serialVersionUID = 1L;
    private String errorCode;// 结果代码,返回CCBC00000表示正常，其他为错误代码
    private String errorMsg;// 错误信息

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setErrorCode(ErrorCode errorCode) {
        if (errorCode != null) {
            setErrorCode(errorCode.getErrorCode());
            setErrorMsg(errorCode.getErrorMsg());
        }
    }
}
