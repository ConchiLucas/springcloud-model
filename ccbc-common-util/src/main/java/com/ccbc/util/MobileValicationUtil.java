package com.ccbc.util;

import com.ccbc.pojo.resp.ApplyResp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MobileValicationUtil {

    public static ApplyResp doExecute(String mobile){
        ApplyResp resp = new ApplyResp();
        // 不是以1开头的，就不是手机号
        if (!mobile.startsWith("1")) {
            log.info("该手机号验证失败。不是以1开头");
            resp.setResult(false);
            return resp;
        }

        // 不是11位
        if (mobile.length() != 11) {
            log.info("该手机号验证失败。不是11位");
            resp.setResult(false);
            resp.setErrorCode(ErrorCodeConstant.CCBC00000);
            return resp;
        } else {
            resp.setResult(true);
            resp.setErrorCode(ErrorCodeConstant.CCBC00000);
            return resp;
        }
    }
}
