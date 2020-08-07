package com.ccbc.pojo.resp;

import com.ccbc.pojo.common.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 五维金融签约同步返回处理类
 * @Description
 * @author liwg2@yusys.com.cn
 * @date 2019年1月24日 上午10:05:58
 */
@Setter
@Getter
public class WwjrSignSynchronizedResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求返回码    成功：0（仅表示为受理成功）失败：参考错误代码表
     */
    private String code;

    /**
     * 请求返回信息
     */
    private String message;

    /**
     * 签约返回信息
     */
    private Data data;




}
