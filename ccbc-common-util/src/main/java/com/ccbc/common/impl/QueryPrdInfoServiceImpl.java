package com.ccbc.common.impl;

import com.ccbc.common.QueryPrdInfoService;
import com.ccbc.pojo.common.SspConstants;
import com.ccbc.pojo.exception.ErrorCodeConstant;
import com.ccbc.pojo.pojo.PrdInfoVo;
import com.ccbc.pojo.resp.CcbcVoResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@Service
public class QueryPrdInfoServiceImpl implements QueryPrdInfoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public CcbcVoResp isPrdInfoExists(String assetSide, String capitalSide, String prdCode,String flowNo) {
        CcbcVoResp ccbcVoResp = new CcbcVoResp();
        String sql = "select count(1) from prd_info where asset_side = '#assetSide' and capital_side = '#capitalSide' and prd_code = '#prdCode'";
        sql.replaceAll("#assetSide",assetSide);
        sql.replaceAll("#capitalSide",capitalSide);
        sql.replaceAll("#prdCode",prdCode);
        List<Map<String,Object>> list =  jdbcTemplate.queryForList(sql);
        if (list.size() == 0) {
            ccbcVoResp.setErrorCode(ErrorCodeConstant.CCBC00000);
            ccbcVoResp.setResult(true);
            ccbcVoResp.setErrorMsg("查无数据");
            return ccbcVoResp;
        }
        //申请信息主表实体
        PrdInfoVo prdInfoVo = new PrdInfoVo();
        prdInfoVo.setAssetSide(assetSide);
        prdInfoVo.setCapitalSide(capitalSide);
        prdInfoVo.setPrdCode(prdCode);
        ccbcVoResp.addCcbcVo(prdInfoVo);
        return ccbcVoResp;
    }
}
