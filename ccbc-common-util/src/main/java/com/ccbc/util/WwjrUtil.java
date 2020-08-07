package com.ccbc.util;

import com.alibaba.fastjson.JSONObject;
import com.ccbc.init.AdmittanceInfoCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class WwjrUtil {

    public static String wwjrSign(Object obj, String privateKey) {
        Map<String, String> map = WwjrUtil.transferObjectToMap(obj);
        String content = ApiSignUtils.getSignCheckContent(map);
        System.out.println(JSONObject.toJSONString(map));
        log.info("签名字段：" + content);
        String signStr = ApiSignUtils.rsaSign(content, privateKey);
        return signStr;
    }

    /**
     * 数据签名 删除merId 和
     * @Description
     * @param obj
     * @param privateKey
     * @return
     */
    public static String wwjrSignNew(Object obj, String privateKey) {
        Map<String, String> map = WwjrUtil.transferObjectToMap(obj);
        String content = ApiSignUtils.getSignCheckContent(map);
        System.out.println(JSONObject.toJSONString(map));
        log.info("签名字段：" + content);
        String signStr = ApiSignUtils.rsaSign(content, privateKey);
        return signStr;
    }

    /**
     * 数据验签
     * @Description
     * @param obj
     * @param privateKey
     * @return
     */
    public static boolean wwjrCheckSign(String content, String sign, String publicKey) {
        log.info("内容验签: content［" + content + "］" + ",sign［" + sign + "］");
        return ApiSignUtils.rsaCheckContent(content, sign, publicKey);
    }

    /**
     * 将请求对象转换成五维需要的格式
     * @Description
     * @param obj
     * @return
     */
    public static String getWwjrReq(Object obj) {
        Map<String, String> map = WwjrUtil.transferObjectToMap(obj);
        return buildParams(map);
    }

    public static String wwjrSignMap(Map<String, String> map, String privateKey) {
        String content = ApiSignUtils.getSignCheckContent(map);
        log.info("签名字段：" + content);
        String signStr = ApiSignUtils.rsaSign(content, privateKey);
        return signStr;
    }

    public static String buildParams(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String value = entry.getValue();
            value = StringUtils.isEmpty(value) ? "" : value;
            sb.append(entry.getKey() + "=" + value + "&");
        }
        return sb.toString();
    }

    /**
     * 将对象转化为map
     * @Description
     * @param obj
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static Map<String, String> transferObjectToMap(Object obj) {
        Map<String, String> map = new HashMap<String, String>();

        try {
            Field fields[] = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                String key = field.getName();
                if (!"serialVersionUID".equals(key)) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    if (!ObjectUtils.isEmpty(value)) {
                        if (value instanceof String) {
                            map.put(key, (String) value);
                        } else {//如果为list直接将list转为jsonstr
                            map.put(key, JSONObject.toJSONString(value));
                        }
                    }

                }
            }
        } catch (Exception e) {
            log.error("将对象转化为map失败", e);
            e.printStackTrace();
        }
        return map;
    }



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
