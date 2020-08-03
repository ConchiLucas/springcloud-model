package com.ccbc.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 此类描述的是： 业务组件系统对外提供的服务请求标头信息
 * 
 * @author: wangshifa
 * @version: 2016年7月4日 下午3:57:41
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CcbcRequest implements Serializable {
    /** 
     * 
     */
    private static final long serialVersionUID = 1L;
    /**交易码*/
    private String serviceId;// 交易码
    /**流水号*/
    private String flowNo;// 流水号
    /**交易时间*/
    private String tradeTime;// 交易时间
    /**渠道标识*/
    private String sysFlag;// 渠道标识
    /**资产标识*/
    private String assetSide;// 资产标识 
    /**资金标识*/
    private String capitalSide;// 资金标识
    /**读写分离标识-true表示读写分离，false表示不分离（默认false查询走写库）*/
    private boolean RWFlag;//读写分离标识-true表示读写分离，false表示不分离（读走写库）

    
}
