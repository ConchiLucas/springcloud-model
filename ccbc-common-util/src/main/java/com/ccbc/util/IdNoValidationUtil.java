package com.ccbc.util;

import com.ccbc.pojo.IdcardValidator;
import com.ccbc.pojo.exception.ErrorCodeConstant;
import com.ccbc.pojo.resp.ApplyResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

@Slf4j
public class IdNoValidationUtil {

    public static ApplyResp doExecute(String identityId){

        ApplyResp resp = new ApplyResp();// 审批规则返回结果
        boolean result = false; // false表示身份证号不合法
        String msg = "";
        log.info("该规则检查：证件号码是否不合法。" + identityId);
        //判断身份证号是否为空，为空规则不通过
        if (StringUtils.isBlank(identityId)) {
            msg = "没有填写身份证号！";
            log.info(result + " 规则结果：未通过。原因：" + msg);
            resp.setResult(result);
            return resp;
        }
        IdcardValidator idcardValidator = new IdcardValidator();
        //身份证校验，是否身份证
        if (idcardValidator.isIdcard(identityId)) {
            //身份证合规校验
            if (idcardValidator.isValidatedAllIdcard(identityId))
                result = true;
        }
        //根据校验结果设不同的值
        if (result) {
            log.info(result + " 规则结果：通过");
        } else {
            log.info(result + " 规则结果：未通过。原因：身份证号 " + identityId + " 有误。");
        }
        resp.setResult(result);
        resp.setErrorCode(ErrorCodeConstant.CCBC00000);
        return resp;

    }

}
