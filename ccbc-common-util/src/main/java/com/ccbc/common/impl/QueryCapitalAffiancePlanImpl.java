package com.ccbc.common.impl;

import com.ccbc.common.QueryCapitalAffiancePlanService;
import com.ccbc.pojo.exception.ErrorCodeConstant;
import com.ccbc.pojo.pojo.ApplyInfoVo;
import com.ccbc.pojo.pojo.CapitalAffiancePlanVo;
import com.ccbc.pojo.req.LoanApplyReq;
import com.ccbc.pojo.resp.CcbcVoResp;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class QueryCapitalAffiancePlanImpl implements QueryCapitalAffiancePlanService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public CcbcVoResp isCapitalAffiancePlanExists(LoanApplyReq loanApplyReq,String assetSide,String capitalSide) {
        CcbcVoResp ccbcVoResp = new CcbcVoResp();
        String sql = "select count(1) from capital_affiance_plan where asset_side = '#assetSide' and capital_side = '#capitalSide' and affiance_plan_no = '#affiancePlanNo'";
        sql.replaceAll("#assetSide",assetSide);
        sql.replaceAll("#capitalSide",capitalSide);
        sql.replaceAll("#affiancePlanNo",loanApplyReq.getPreAbsId());
        List<Map<String,Object>> list =  jdbcTemplate.queryForList(sql);
        if (list.size() == 0) {
            ccbcVoResp.setErrorCode(ErrorCodeConstant.CCBC00000);
            ccbcVoResp.setResult(true);
            ccbcVoResp.setErrorMsg("查无数据");
            return ccbcVoResp;
        }
        //查询信托计划返回
        String querySql = "select * from capital_affiance_plan where asset_side = '#assetSide' and capital_side = '#capitalSide' and affiance_plan_no = '#affiancePlanNo'";
        CapitalAffiancePlanVo capitalAffiancePlanVo = jdbcTemplate.queryForObject(querySql, CapitalAffiancePlanVo.class);
        ccbcVoResp.addCcbcVo(capitalAffiancePlanVo);
        //将applyinfo入库
        ApplyInfoVo applyInfoVo = new ApplyInfoVo();

        applyInfoVo.setAffiancePlanNo(capitalAffiancePlanVo.getAffiancePlanNo());
        applyInfoVo.setAffiancePlanTermNum(capitalAffiancePlanVo.getTermNum());

        applyInfoVo.setAssetSide(assetSide);
        applyInfoVo.setCapitalSide(capitalSide);
        applyInfoVo.setApplyNo(loanApplyReq.getLoanOrderId());// 申请编号
        applyInfoVo.setCustName(loanApplyReq.getName());// 贷款人姓名
        applyInfoVo.setIdType("01");// 证件类型
        applyInfoVo.setIdNo(loanApplyReq.getIdNo());// 证件号码
        applyInfoVo.setMobile(loanApplyReq.getMobile());// 贷款人手机号码
        // applyInfoVo.setApplyDate(req.getCustApplyDate());//客户申请日期
        applyInfoVo.setApplyDate(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()));// 客户申请日期
        applyInfoVo.setPrdCode(loanApplyReq.getProductNo());// 产品编号
        applyInfoVo.setCardNo(loanApplyReq.getBankCardNo());// 放款账号
        applyInfoVo.setCurStep("1");
        applyInfoVo.setStatus("04");// 状态：申请中
        //insert todo
        return ccbcVoResp;
    }
}
