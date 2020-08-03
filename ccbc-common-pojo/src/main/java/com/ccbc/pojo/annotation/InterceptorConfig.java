package com.ccbc.pojo.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
	public void addInterceptors(InterceptorRegistry registry){       
        registry.addInterceptor(new NoNullInterceptor())
        		.addPathPatterns("/**"); 
/*        registry.addInterceptor(new DigitNoNullInterceptor())
		.addPathPatterns("/**"); */
        //非空字段拦截
        super.addInterceptors(registry);
    }
}
