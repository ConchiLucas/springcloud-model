package com.ccbc.pojo.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoNull {
	String value() default "";
		
	String message() default "";
}


