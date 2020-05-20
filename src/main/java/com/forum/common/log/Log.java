package com.forum.common.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Log {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(* com.forum.modules..*.*(..))")
    public void pointCutController(){}


    //定位于所有类下，返回值任意、方法入参类型、数量任意，public类型的方法
    @Pointcut("execution(public * *Service(..))")
    public void pointCutService(){}


    @Before("pointCutController()")
    public void beforeController(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("method before:{}",name);
    }

}
