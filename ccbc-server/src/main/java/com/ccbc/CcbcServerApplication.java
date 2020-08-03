package com.ccbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CcbcServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcbcServerApplication.class, args);
	}



}