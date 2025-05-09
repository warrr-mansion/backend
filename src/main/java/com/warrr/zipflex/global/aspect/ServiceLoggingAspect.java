package com.warrr.zipflex.global.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {

    @Pointcut("execution(* com.warrr.zipflex.api..service.*.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBeforeServiceCall(JoinPoint joinPoint) {
        log.info("args: {} <- method: {}", joinPoint.getArgs(), joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterServiceCall(JoinPoint joinPoint, Object result) {
        log.info("result: {} <- method: {}", result, joinPoint.getSignature());
    }
}
