package com.ccbc.didi;

import com.ccbc.pojo.req.LoanApplyReq;
import com.ccbc.pojo.resp.DdResp;

public interface LoanApplyService {

    public DdResp checkValidity(LoanApplyReq loanApplyReq);


}