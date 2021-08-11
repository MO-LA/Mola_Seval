package com.mo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class Aop {
    @Around("execution(* com.mo.controller..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info(joinPoint.toShortString());

        return joinPoint.proceed();
    }
}
