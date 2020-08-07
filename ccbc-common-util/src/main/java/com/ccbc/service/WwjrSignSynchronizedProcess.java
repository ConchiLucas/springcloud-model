package com.ccbc.service;

import com.alibaba.fastjson.JSONObject;
import com.ccbc.pojo.common.Data;
import com.ccbc.pojo.req.SignSynchronizedReq;
import com.ccbc.pojo.req.WwjrSignSynchronizedReq;
import com.ccbc.pojo.resp.SignSynchronizedResp;
import com.ccbc.pojo.resp.WwjrSignSynchronizedResp;
import com.ccbc.util.PostUtil;
import com.ccbc.util.WwjrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WwjrSignSynchronizedProcess {

    public static SignSynchronizedResp signSynchronized(SignSynchronizedReq reqData) {
        log.info("五维金融签约同步请求报文:" + JSONObject.toJSONString(reqData));
        SignSynchronizedResp respData = new SignSynchronizedResp();
        String type = WwjrUtil.getBusiType(reqData.getCapitalSide());
        try {
            WwjrSignSynchronizedReq req = initReq(reqData);
            String reqStr = WwjrUtil.getWwjrReq(req);
            log.info("五维金融签约同步发送报文：" + reqStr);
            String respStr = "";
            if ("1".equals(type)) {//模拟成功的
                respStr = "{\"code\": 0,\"data\": {\"requestNo\": \"" + req.getRequestNo() + "\",\"trustCode\": \""
                        + req.getTrustCode() + "\",\"merId\": \"" + req.getMerId() + "\",\"transType\": \""
                        + req.getTransType() + "\",\"gateWay\": \"" + req.getGateWay()
                        + "\",\"proNo\":\"101010\",\"status\":\"3\",\"accountNo\":\"" + req.getAccountNo()
                        + "\"},\"message\": \"签约成功!\"}";
            } else if ("2".equals(type)) {//模拟失败的
                respStr = "{\"code\":10001,\"message\": \"系统错误\",\"data\" :{}}";
            } else {//真实返回的
                //String url = ConfigManagerUtil.getConfigInfo("app/usp/wwjr", "signSyncUrl");
                //respStr = PostUtil.sendPost(url, reqStr, requestHeader(req));
            }
            log.info("五维金融签约同步返回报文：" + respStr);
            WwjrSignSynchronizedResp resp = new WwjrSignSynchronizedResp();
            try {
                resp = JSONObject.parseObject(respStr, WwjrSignSynchronizedResp.class);
                log.info("转换后得返回结果为:" + JSONObject.toJSONString(resp));
                //请求返回码 0表示成功受理，其他为失败
                if ("0".equals(resp.getCode())) {
                    if (0 == resp.getData().getStatus()) {//签约结果，处理中(0)签约成功(3)签约失败(4)
                        respData.setReturnCode("USPS0001");
                        respData.setReturnMessage("签约处理中");
                        respData.setData(resp.getData());
                    } else if (3 == resp.getData().getStatus()) {
                        respData.setReturnCode("USPS0000");
                        if (StringUtils.isNotBlank(resp.getData().getMsg())) {
                            respData.setReturnMessage(resp.getData().getMsg());
                        } else {
                            respData.setReturnMessage("签约成功");
                        }
                        respData.setData(resp.getData());
                    } else {
                        respData.setReturnCode("USPS0101");
                        if (StringUtils.isNotBlank(resp.getData().getMsg())) {
                            respData.setReturnMessage(resp.getData().getMsg());
                        } else {
                            respData.setReturnMessage("签约失败");
                        }
                        respData.setData(resp.getData());
                    }
                } else if ("50004".equals(resp.getCode())) {//该用户已签约成功
                    respData.setReturnCode("0");
                    respData.setReturnMessage("已签约成功");
                    Data data = new Data();
                    data.setRequestNo(req.getRequestNo());
                    data.setTrustCode(req.getTrustCode());
                    data.setMerId(req.getMerId());
                    data.setTransType(req.getTransType());
                    data.setStatus(1);
                    respData.setData(data);
                } else {
                    respData.setReturnCode("USPS0101");
                    respData.setReturnMessage(resp.getMessage());
                }
                respData.setCode(resp.getCode());
                respData.setMessage("接口响应结果：" + resp.getMessage());
                log.info("五维金融签约同步返回给组件的报文：" + JSONObject.toJSONString(respData));
            } catch (Exception e) {
                log.error("五维金融签约同步返回结果异常" + e.getMessage(), e);
                respData.setReturnCode("USPS0101");
                respData.setReturnMessage(e.getMessage());
            }
        } catch (Exception e) {
            respData.setReturnCode("USPS0101");
            respData.setReturnMessage("五维金融签约同步异常" + e.getMessage());
            log.error("五维金融签约同步异常" + e.getMessage(), e);
        }
        return respData;
    }

    /**
     * 签约同步信息转换
     * @Description
     * @param reqData
     * @return
     * @throws Exception
     */
    public static WwjrSignSynchronizedReq initReq(SignSynchronizedReq reqData) throws Exception {
        //String merId = ConfigManagerUtil.getConfigInfo("app/usp/wwjr", "signSyncMerId");
        //String secret = ConfigManagerUtil.getConfigInfo("app/usp/wwjr", "secret");
        String merId = "yx-001";
        String secret = "063cabbf-8d35-41fe-8c1e-c33c36564f38";
        WwjrSignSynchronizedReq req = new WwjrSignSynchronizedReq();
        req.setMerId(merId);//[Y][商户代码][支付系统分配]
        req.setSecret(secret);
        req.setFn("300002");//[功能代码][必填][固定值300002]
        req.setTransType(reqData.getTransType());//[Y][交易类型]默认四要素
        req.setRequestNo(reqData.getFlowNo());
        req.setTrustCode(reqData.getAffianceNo());//信托计划编号
        req.setGateWay(reqData.getGateWay());//交易网关
        req.setCardType(0);//默认0借记卡 1信用卡
        req.setAccountNo(reqData.getAcctNo());//银行卡号
        req.setAccountName(reqData.getCustName());//客户姓名
        req.setCertType(0);//默认身份证
        req.setCertNo(reqData.getCertNo());//证件号码
        req.setMobile(reqData.getMobile());//手机号码
        req.setBankSecureCode("");//银行卡安全码 卡类型为信用卡必填
        req.setBankCardIndate("");//银行卡有效期 卡类型为信用卡必填格式：YYYY-MM-DD
        log.info("五维金融签约同步发送实体：" + JSONObject.toJSONString(req));
        return req;
    }

    private static Map<String, String> requestHeader(WwjrSignSynchronizedReq req) throws Exception {
        //String privateKey = ConfigManagerUtil.getConfigInfo("app/usp/wwjr", "privateKey");
        //String merId = ConfigManagerUtil.getConfigInfo("app/usp/wwjr", "merId");
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOXUGOKdEkssOI1zqNw4pb6emH1o1JYxooyTQ7FN1IBNqTJLuvA3GsswXIkuQj0imce6ywG/XOCwc9R1l5FwcORtwx2FihGCl7eBkhUwnT0EwGOEARPh96ey+TfvsvRaHOn672v1TEhajAftgm4l7fJDtHdGBjHOs+5Mlir9Z65RAgMBAAECgYEArtAiUZR5yrYLGgTEhyWLZK+Le7CWKtv8MQL+tUlm/mST8s7JlVfEyJKzgCCwf4HnCJXbPiwJgFqW8B61uAmXw6cEoPftEnzvKBTyISt/iEf7DTWKGkDBnlYM9sFU6pU61jw17XEDQRtSBG6cfrlGSelqf25+c8onxu4YwTeLH/ECQQD/H69tPy0FYRvCJ5yXdXEVCKshNN01P+UdDzGtyysE/gmpalbewT+uznApa0qFntcYb8eSpUJzrUlItSCBGUpdAkEA5p4r3qF+4g5V7MBHm3+v1l9JKxYK76990AQJa122rfkY2EEVuvU+8KIAQpVflu/HpDe8QH4mQZTsZj24Skt8hQJAL5j2vrgRqzZB2ohPY8aKcXUrkEdvmdaw5SoHh7gm74iBvvTS/j4ppnBnZqLYxXMsCCgaoNZqPnCvAnygctWIFQJAHm2KLkKyohLwJV+tUwgC5E8IMWYkJUHLYNHXiFICE2xFaesUeel313oYfLCGvzx9493yubOrSoXitw63rR3OnQJBALwGSnGYodmJB5k7un0X6LlO4nSu/+SX9lweloZ1AUg15IuCIYxHAFKwOtOJmx/eMcITaLq8l1qzZ907UXY+Mfs=";
        String merId = "yx-001";
        String signStr = WwjrUtil.wwjrSign(req, privateKey);
        Map<String, String> map = new HashMap<String, String>();
        map.put("merId", merId);
        map.put("secret", req.getSecret());
        map.put("sign", signStr);
        return map;
    }

}
