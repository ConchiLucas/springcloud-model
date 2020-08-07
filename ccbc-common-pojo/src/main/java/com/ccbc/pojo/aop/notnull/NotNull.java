package com.ccbc.pojo.aop.notnull;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {
    String value() default "";

    String message() default "";
}

