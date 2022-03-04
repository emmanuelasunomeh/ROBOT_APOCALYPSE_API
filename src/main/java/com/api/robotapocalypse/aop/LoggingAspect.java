package com.api.robotapocalypse.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("@annotation(com.api.robotapocalypse.aop.annotation.LogExecutionTime)")
    public Object checkAllMethodPerformanceByExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        final StopWatch sw = new StopWatch();
        sw.start();
        Object output = pjp.proceed();
        sw.stop();
        log.info("Execution Time of "+ className+ "."+methodName+" :: " + sw.getTotalTimeMillis()+ " ms" +"("+  sw.getTotalTimeMillis()/1000.00+ " s)");
        return output;

    }






}


