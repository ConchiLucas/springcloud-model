package com.ccbc.util;

import com.ccbc.init.AdmittanceInfoCache;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class WwjrUtil {

    /**
     * 获取是否使用挡板开关
     * @Description
     * @param capitalSide
     * @return
     */
    public static String getBusiType(String capitalSide) {
        String type = "";
        if (!StringUtils.isEmpty(String.valueOf(AdmittanceInfoCache.getPartnerMap().get(capitalSide)))) {
            Map<String, Object> pmap = AdmittanceInfoCache.getPartnerMap().get(capitalSide);
            if (!StringUtils.isEmpty(String.valueOf(pmap.get("BUSS_TYPE")))) { //报文发送开关
                //1：返回成功虚拟报文，2：返回失败虚拟报文，3：返回真实报文
                type = pmap.get("BUSS_TYPE").toString();
            }
        }
        return type;
    }
}
