package com.ccbc.pojo.resp;

import com.ccbc.pojo.abspojo.ICcbcVo;
import com.ccbc.pojo.common.CcbcResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CcbcVoResp extends CcbcResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 服务返回结果
     */
    private boolean result;

    /**
     * 服务返回信息
     */
    private String message;
    private List<ICcbcVo> ccbcVoList = new ArrayList<ICcbcVo>();

    public void addCcbcVo(ICcbcVo ccbcVo) {
        this.ccbcVoList.add(ccbcVo);
    }
}
