package com.ccbc.pojo.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * usp发送消息的基类
 *
 * @author mick1
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@Setter
@Getter
public class ReqBaseMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String serviceId;// 交易码

    private String flowNo;// 交易流水号

    private String tradeTime;// 如：20150103123356

    private String sysFlag;// 系统标识 （核心：0001，也可使用系统简称）

    private String ecpNo;// ecp交易流水号(不用上送)
    private String assetSide;// 资产方

    private String capitalSide;// 资金方

    private String data;


}
