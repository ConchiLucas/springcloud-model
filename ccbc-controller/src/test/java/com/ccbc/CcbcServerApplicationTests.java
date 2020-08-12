package com.ccbc;

import com.ccbc.redis.NumberUtils;
import com.ccbc.redis.RedisUtil;
import com.ccbc.zookeeper.CuratorUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.Set;

@SpringBootTest
class CcbcServerApplicationTests {


	@Value("${server.port}")
	private String host;



	@Test
	void contextLoads() {
		for(int i = 0;i<10000;i++){
			System.out.println(NumberUtils.getJDCreditApplyNo());

		}
	}


	@Test
	void test2(){
	}

}
