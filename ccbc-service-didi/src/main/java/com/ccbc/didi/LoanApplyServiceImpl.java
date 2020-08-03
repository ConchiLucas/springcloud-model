package com.ccbc.didi;

import com.ccbc.pojo.req.LoanApplyReq;
import com.ccbc.pojo.resp.ApplyResp;
import com.ccbc.pojo.resp.DdResp;
import com.ccbc.pojo.resp.IdNoValidationResp;
import com.ccbc.util.IdNoValidationUtil;
import com.ccbc.util.MobileValicationUtil;
import org.springframework.stereotype.Service;

@Service
public class LoanApplyServiceImpl implements LoanApplyService{

    @Override
    public DdResp checkValidity(LoanApplyReq loanApplyReq) {
        //校验身份证是否合法
        ApplyResp applyResp1 = IdNoValidationUtil.doExecute(loanApplyReq.getIdNo());
        //校验手机是否合法
        ApplyResp applyResp2 = MobileValicationUtil.doExecute(loanApplyReq.getMobile());

        return null;
    }
}
