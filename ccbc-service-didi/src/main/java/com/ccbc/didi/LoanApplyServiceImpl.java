package com.ccbc.didi;

import com.ccbc.common.QueryCapitalAffiancePlanService;
import com.ccbc.common.QueryPrdInfoService;
import com.ccbc.didi.common.DidiCommonMethod;
import com.ccbc.pojo.exception.ErrorCodeConstant;
import com.ccbc.pojo.req.LoanApplyReq;
import com.ccbc.pojo.req.SignSynchronizedReq;
import com.ccbc.pojo.resp.*;
import com.ccbc.service.WwjrSignSynchronizedProcess;
import com.ccbc.util.IdNoValidationUtil;
import com.ccbc.util.MobileValicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoanApplyServiceImpl implements LoanApplyService{

    @Autowired
    private QueryPrdInfoService queryPrdInfoService;

    @Autowired
    private QueryCapitalAffiancePlanService queryCapitalAffiancePlanService;

    @Override
    public DdResp checkValidity(LoanApplyReq loanApplyReq) {
        DdResp dResp = new DdResp();
        String flowNo = UUID.randomUUID().toString().replaceAll("-","");
        //校验份证是否合法
        ApplyResp applyResp1 = IdNoValidationUtil.doExecute(loanApplyReq.getIdNo());
        //校验手机是否合法
        ApplyResp applyResp2 = MobileValicationUtil.doExecute(loanApplyReq.getMobile());
        //转换五维签约状态参数
        SignSynchronizedReq reqData = new SignSynchronizedReq();
        SignSynchronizedReq signSynchronizedReq = DidiCommonMethod.getSignSynchronizedReq(loanApplyReq);
        //查询五维签约状态
        SignSynchronizedResp signSynchronizedResp = WwjrSignSynchronizedProcess.signSynchronized(signSynchronizedReq);
        //滴滴-滴水贷-产品信息表查询接口
        CcbcVoResp ccbcVoResp1 = queryPrdInfoService.isPrdInfoExists("A060000032","C060000001",loanApplyReq.getProductNo(),flowNo);
        boolean flag = false;
        if(!flag){
            dResp.setErrorCode(ErrorCodeConstant.CCBCB0206);
            dResp.setErrorMsg("产品信息不存在");
        }
        //滴滴-滴水贷-贷款申请主表信息新增接口
        CcbcVoResp ccbcVoResp2 = queryCapitalAffiancePlanService.isCapitalAffiancePlanExists(loanApplyReq,"A060000032","C060000001");

        return dResp;
    }


}
