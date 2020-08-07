package com.ccbc.pojo.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 签约同步请求类
 * @Description
 * @author liwg2@yusys.com.cn
 * @date 2019年1月24日 上午10:01:58
 */
@Setter
@Getter
public class WwjrSignSynchronizedReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String requestNo;//请求唯一标识
    //商户秘钥
    private String secret;
    //功能代码 必填
    //固定值300002
    private String fn;
    private String trustCode;//信托产品代码
    private int transType;//交易类型 0协议支付 1四要素扣款
    private int gateWay;//网关 广银联(0)宝付(1)中金(2)通联(3)易宝(4)联动优势(5)
    private String merId;//商户号
    private int cardType;//卡类型
    private String accountNo;//银行卡号
    private String accountName;//持卡人姓名
    private int certType;//证件类型 身份证(0)
    private String certNo;//证件号
    private String mobile;//手机号
    private String bankSecureCode;//银行卡安全码  卡类型为信用卡必填
    private String bankCardIndate;//银行卡有效期  卡类型为信用卡必填格式：YYYY-MM-DD


}
