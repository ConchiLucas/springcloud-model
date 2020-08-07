package com.ccbc.pojo.req;

import com.ccbc.pojo.common.ReqBaseMsg;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * 五维金融签约同步请求报文
 * @Description
 * @author liwg2@yusys.com.cn
 * @date 2019年1月24日 下午1:40:38
 */
@Setter
@Getter
public class SignSynchronizedReq extends ReqBaseMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String affianceNo;//信托计划编号

    private int transType;//交易类型 0协议支付 1四要素扣款

    private int gateWay;//网关 广银联(0)宝付(1)中金(2)通联(3)易宝(4)联动优势(5)

    private String acctNo;//账户

    private String custName;//客户名称

    private String certNo;//身份证号码

    private String mobile;//手机号码





}
