package com.ccbc.pojo.aop.notnull;

import com.ccbc.pojo.annotation.NoNull;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class NoNullAspect {
        /**
         * 切入点
         */
        @Pointcut("@annotation(com.ccbc.pojo.aop.notnull.NotNull) ")
        public void entryPoint() {
            // 无需内容

        }

        @Before("entryPoint()")
        public void before(JoinPoint joinPoint) {

            log.info("=====================开始执行前置通知==================");
            try {
                String targetName = joinPoint.getTarget().getClass().getName();
                String methodName = joinPoint.getSignature().getName();
                Object[] arguments = joinPoint.getArgs();
                Class<?> targetClass = Class.forName(targetName);
                Method[] methods = targetClass.getMethods();
                Field[] fields = arguments[0].getClass().getDeclaredFields();


            } catch (Throwable e) {
                log.info("around " + joinPoint + " with exception : " + e.getMessage());
            }

        }

    }
