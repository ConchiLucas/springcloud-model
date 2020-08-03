package com.ccbc.init;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@Order(value=1)
public class AdmittanceInfoCache implements ApplicationRunner {

    // 交易缓存
    private static Map<String, Map<String, Object>> tradeCodeMap = null;
    // 合作商缓存
    private static Map<String, Map<String, Object>> partnerMap = null;
    // 合作商交易权限缓存
    private static Map<String, Map<String, Object>> partnerTradeAuth = null;
    // 渠道控制缓存
    private static Map<String, Map<String, Object>> channelMap = null;
    // 渠道控制缓存
    private static Map<String, Map<String, Object>> merChantInfoMap = null;
    // 查询权限控制缓存
    private static Map<String, Map<String, Object>> queryTradeMap = null;
    // 查询资产方
    private static List<Map<String, Object>> queryPartner = null;

    private static String jdbcTemplateName = "jdbcTemplate";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }

    public void init(){
        tradeCodeMap = new HashMap<String, Map<String, Object>>();
        String sql1 = "select * from ssp_tran_define";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql1);
        for (Map<String, Object> queryTradeMap : list) {
            tradeCodeMap.put(queryTradeMap.get("TRAN_CODE").toString(), queryTradeMap);
        }
        partnerMap = new HashMap<String, Map<String, Object>>();
        String sql2 = "select *  from ecp_partner_info";
        List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql2);
        for (Map<String, Object> queryTradeMap : list1) {
            partnerMap.put(queryTradeMap.get("PARTNER_CODE").toString(), queryTradeMap);
        }
        partnerTradeAuth = new HashMap<String, Map<String, Object>>();
        String sql3 = "select *  from ecp_partner_tran_auth";
        List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql3);
        for (Map<String, Object> queryTradeMap : list2) {
            partnerTradeAuth.put(
                    queryTradeMap.get("PARTNER_CODE").toString() + queryTradeMap.get("TRAN_CODE").toString(),
                    queryTradeMap);
            // log.info(queryTradeMap.get("PARTNER_CODE").toString() +
            // queryTradeMap.get("TRAN_CODE").toString());
        }
        channelMap = new HashMap<String, Map<String, Object>>();
        String sql4 = "select *  from ssp_tran_channel";
        List<Map<String, Object>> list3 = jdbcTemplate.queryForList(sql4);
        for (Map<String, Object> queryTradeMap : list3) {
            channelMap.put(queryTradeMap.get("CAPITAL_SIDE").toString() + "_"
                            + queryTradeMap.get("ASSERT_SIDE").toString() + "_" + queryTradeMap.get("CHANNEL_ID").toString(),
                    queryTradeMap);
        }
        merChantInfoMap = new HashMap<String, Map<String, Object>>();
        String sql5 = "select *  from ecp_merchant_info";
        List<Map<String, Object>> list4 = jdbcTemplate.queryForList(sql5);
        for (Map<String, Object> queryTradeMap : list4) {
            merChantInfoMap.put(queryTradeMap.get("TRUST_PLAN").toString(), queryTradeMap);
        }

        queryTradeMap = new HashMap<String, Map<String, Object>>();
        String sql6 = "select *  from ssp_business_control";
        List<Map<String, Object>> list5 = jdbcTemplate.queryForList(sql6);
        for (Map<String, Object> queryTradeMap01 : list5) {
            queryTradeMap.put(
                    queryTradeMap01.get("ASSET_SIDE").toString() + "_" + queryTradeMap01.get("TRAN_CODE").toString(),
                    queryTradeMap01);
        }
        // 查询资产方
        queryPartner = new ArrayList<Map<String, Object>>();
        String sql7 = "select a.PARTNER_CODE,a.PARTNER_NAME from ecp_partner_info a where a.partner_code like 'A%' order by a.PARTNER_CODE";
        queryPartner = jdbcTemplate.queryForList(sql7);

        log.info("加载信息ssp初始化信息完成 tradeCodeMap:" + tradeCodeMap.size() + " partnerMap:" + partnerMap.size()
                + " merChantInfoMap:" + merChantInfoMap.size() + " queryTradeMap:" + queryTradeMap.size()
                + " queryPartner:" + queryPartner.size());
        log.info("queryPartner:" + JSONObject.toJSONString(queryPartner));
    }


    public static String getJdbcTemplateName() {
        return jdbcTemplateName;
    }

    public static void setJdbcTemplateName(String jdbcTemplateName) {
        AdmittanceInfoCache.jdbcTemplateName = jdbcTemplateName;
    }


    public static List<Map<String, Object>> getQueryPartner() {
        return queryPartner;
    }

    public static void setQueryPartner(List<Map<String, Object>> queryPartner) {
        AdmittanceInfoCache.queryPartner = queryPartner;
    }

    public static Map<String, Map<String, Object>> getMerChantInfoMap() {
        if (ObjectUtils.isEmpty(merChantInfoMap)) {
        }
        return merChantInfoMap;
    }

    public static void setMerChantInfoMap(Map<String, Map<String, Object>> merChantInfoMap) {
        AdmittanceInfoCache.merChantInfoMap = merChantInfoMap;
    }

    public static Map<String, Map<String, Object>> getTradeCodeMap() {
        if (ObjectUtils.isEmpty(tradeCodeMap)) {
        }
        return tradeCodeMap;
    }

    public static void setTradeCodeMap(Map<String, Map<String, Object>> tradeCodeMap) {
        AdmittanceInfoCache.tradeCodeMap = tradeCodeMap;
    }

    public static Map<String, Map<String, Object>> getPartnerMap() {
        return partnerMap;
    }

    public static void setPartnerMap(Map<String, Map<String, Object>> partnerMap) {
        AdmittanceInfoCache.partnerMap = partnerMap;
    }

    public static Map<String, Map<String, Object>> getPartnerTradeAuth() {
        if (ObjectUtils.isEmpty(partnerTradeAuth)) {
        }
        return partnerTradeAuth;
    }

    public static void setPartnerTradeAuth(Map<String, Map<String, Object>> partnerTradeAuth) {
        AdmittanceInfoCache.partnerTradeAuth = partnerTradeAuth;
    }

    public static Map<String, Map<String, Object>> getChannelMap() {
        if (ObjectUtils.isEmpty(channelMap)) {
        }
        return channelMap;
    }

    public static void setChannelMap(Map<String, Map<String, Object>> channelMap) {
        AdmittanceInfoCache.channelMap = channelMap;
    }

    public static Map<String, Map<String, Object>> getQueryTradeMap() {
        if (ObjectUtils.isEmpty(queryTradeMap)) {
        }
        return queryTradeMap;
    }

    public static void setQueryTradeMap(Map<String, Map<String, Object>> queryTradeMap) {
        AdmittanceInfoCache.queryTradeMap = queryTradeMap;
    }

}
