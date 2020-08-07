package com.ccbc.pojo.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 五维金融签约同步返回信息
 * @Description
 * @author liwg2@yusys.com.cn
 * @date 2019年1月24日 下午1:20:28
 */
@Setter
@Getter
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求唯一编号
     */
    private String requestNo;

    /**
     * 信托产品代码
     */
    private String trustCode;
    /**
     * 商户号
     */
    private String merId;
    /**
     * 交易类型
     */
    private int transType;
    /**
     * 网关
     */
    private int gateWay;
    /**
     * 协议号
     */
    private String proNo;
    /**
     * 签约状态 处理中(0)签约成功(3)签约失败(4)
     */
    private int status;

    /**
     * 银行卡号
     */
    private String accountNo;


    /**
     * 签约同步返回信息
     */
    private String msg;




}
