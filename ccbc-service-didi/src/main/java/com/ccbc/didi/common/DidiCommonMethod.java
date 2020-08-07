package com.ccbc.didi.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccbc.pojo.common.ReqBaseMsg;
import com.ccbc.pojo.common.SspConstants;
import com.ccbc.pojo.req.LoanApplyReq;
import com.ccbc.pojo.req.SignSynchronizedReq;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DidiCommonMethod {

    /**
     * 获取五维请求对象
     * @param loanApplyReq
     * @return
     */
    public static SignSynchronizedReq getSignSynchronizedReq(LoanApplyReq loanApplyReq){
        String flowNo = "DD"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        SignSynchronizedReq signSynchronizedReq = new SignSynchronizedReq();
        // 头部信息
        signSynchronizedReq.setFlowNo(flowNo);// 交易流水
        signSynchronizedReq.setTradeTime(SspConstants.getTradeTime());// 交易时间
        signSynchronizedReq.setSysFlag(SspConstants.SYS_FLAG);// 系统标识
        signSynchronizedReq.setAssetSide("A060000032");// 资产方
        signSynchronizedReq.setCapitalSide("C060000001");// 资金方
        signSynchronizedReq.setAffianceNo(loanApplyReq.getPreAbsId());//信托计划编号
        signSynchronizedReq.setTransType(1);//四要素签约
        signSynchronizedReq.setGateWay(1);//交易网关，滴滴，宝付
        signSynchronizedReq.setAcctNo(loanApplyReq.getBankCardNo());//还款账户
        signSynchronizedReq.setCustName(loanApplyReq.getName());//客户名称
        signSynchronizedReq.setCertNo(loanApplyReq.getIdNo());//证件号码
        signSynchronizedReq.setMobile(loanApplyReq.getMobile());//手机号码
        return signSynchronizedReq;
    }

    public static ReqBaseMsg getReqBaseMsg(String reqStr){
        Object reqObj = JSONObject.parseObject(reqStr, JSONObject.class);
        ReqBaseMsg reqBaseMsg = null;
        if (reqObj instanceof ReqBaseMsg) {
            reqBaseMsg = (ReqBaseMsg) reqObj;
        }
        return reqBaseMsg;
    }



}
