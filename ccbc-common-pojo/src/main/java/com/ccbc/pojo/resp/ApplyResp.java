package com.ccbc.pojo.resp;

import com.ccbc.pojo.common.CcbcResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ApplyResp extends CcbcResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 服务返回结果
     */
    private boolean result;

    /**
     * 服务返回信息
     */
    private String message;


}
