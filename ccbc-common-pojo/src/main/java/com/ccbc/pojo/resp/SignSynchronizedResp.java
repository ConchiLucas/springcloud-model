package com.ccbc.pojo.resp;

import com.ccbc.pojo.common.Data;
import com.ccbc.pojo.common.RespBaseMsg;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;



/**
 * 五维金融签约同步返回报文
 * @Description
 * @author liwg2@yusys.com.cn
 * @date 2019年1月24日 下午1:40:19
 */

@Setter
@Getter
public class SignSynchronizedResp{

    private static final long serialVersionUID = 1L;

    /**
     * 请求返回码    成功：0（仅表示为受理成功）失败：参考错误代码表
     */
    private String code;

    /**
     * 请求返回信息
     */
    private String message;

    private Data data;


    private String serviceId;// 交易码

    private String returnCode = "0";// 返回码

    private String returnMessage;// 返回信息

}
