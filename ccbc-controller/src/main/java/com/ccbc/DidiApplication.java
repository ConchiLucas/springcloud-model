package com.ccbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class DidiApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DidiApplication.class, args);
	}



	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.out.println("client:9001----running");
		//传入SpringBoot应用的主程序
		return application.sources(DidiApplication.class);
	}



}
