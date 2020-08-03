package com.ccbc.pojo.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * usp返回消息的基类
 *
 * @author mick1
 *
 */
@Setter
@Getter
public class RespBaseMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String serviceId;// 交易码

    private String returnCode = "0";// 返回码

    private String returnMessage;// 返回信息
    //错误信息
    private String returnMsg;
    //消息参考号
    private String refNo;
    //银行流水号
    private String bankSerialNo;
    //附加信息1
    private String resv1;
    //附加信息2
    private String resv2;

    private String token;

    private String data;

    private String sign;

    private String result;//  检查结果，拒绝放款返回F，允许放款返回S

    private String resultCode;// 返回码

    private String resultInfo;//  返回信息

    private String cusId;//合作商编码

    private String tradeCode;//交易码
    
    private String errorCode;//滴滴使用错误码
    
    private String errorMsg;//滴滴使用错误返回消息


    
}
