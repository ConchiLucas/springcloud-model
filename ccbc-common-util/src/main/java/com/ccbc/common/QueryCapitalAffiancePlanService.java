package com.ccbc.common;

import com.ccbc.pojo.req.LoanApplyReq;
import com.ccbc.pojo.resp.CcbcVoResp;

public interface QueryCapitalAffiancePlanService {

    public CcbcVoResp isCapitalAffiancePlanExists(LoanApplyReq loanApplyReq,String assetSide,String capitalSide);


}
