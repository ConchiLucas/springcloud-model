package com.ccbc.redis;

import com.ccbc.zookeeper.CuratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.FastDateFormat;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Slf4j
public class NumberUtils {

    private static String host = "";

    /**
     * 生成京东申请编号
     *
     * @return 京东申请编号中间12位，用0补齐
     * @throws Exception
     */
    public static String getJDCreditApplyNo() {
        String tranLogNo = "";
        try {
            tranLogNo = getSeqByKey("JD_Credit_Apply_No", "6");
        } catch (Exception e) {
            log.error("生成回购批次号异常", e);
            return null;
        }
        return tranLogNo;
    }


    public static String getSeqByKey(String key, String lenNum) throws Exception {
        return host + FastDateFormat.getInstance("yyyyMMdd").format(new Date()) + String.format("%0" + lenNum + "d", RedisUtil.incr(key));
    }



    public static Set<HostAndPort> getHosts(){
        Set hosts = new HashSet();
        try {
            Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");
            byte[] bytes = CuratorUtils.getData("system/redis/message");
            String address = new String(bytes);
            String[] providers = address.split("@");
            for (String addr : providers) {
                if (!p.matcher(addr).matches()) {
                } else {
                    String[] ipAndPort = addr.split(":");
                    hosts.add(new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1])));
                }
            }
        }catch(Exception e){
            log.error("获取host异常",e);
        }
        return hosts;
    }

}
