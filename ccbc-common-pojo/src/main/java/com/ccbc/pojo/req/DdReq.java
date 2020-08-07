package com.ccbc.pojo.req;


import com.ccbc.pojo.common.ReqBaseMsg;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DdReq extends ReqBaseMsg {

	private static final long serialVersionUID = 1L;
	
	private String appId; //滴滴分配的商户号
	
	private String signTyp;//签名类型
	
	private String bizContent;//请求参数


	
	
	
	

}
