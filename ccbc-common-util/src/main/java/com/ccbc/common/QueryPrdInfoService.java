package com.ccbc.common;

import com.ccbc.pojo.resp.CcbcVoResp;

public interface QueryPrdInfoService {

    public CcbcVoResp isPrdInfoExists(String assetSide, String capitalSide, String prdCode,String flowNo);
}
