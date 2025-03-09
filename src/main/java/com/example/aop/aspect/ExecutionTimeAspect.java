package com.example.aop.aspect;

import com.example.aop.annotation.LogExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for measuring method execution time
 */
@Aspect
@Component
public class ExecutionTimeAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Measures and logs the execution time of methods annotated with @LogExecutionTime
     */
    @Around("@annotation(com.example.aop.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        
        log.info("{}.{} executed in {} ms",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                executionTime);
        
        return proceed;
    }
    
    /**
     * Measures and logs the execution time of service methods
     */
    @Around("execution(* com.example.aop.service.*.*(..))")
    public Object logExecutionTimeForServices(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        
        log.debug("{}.{} service method executed in {} ms",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                executionTime);
        
        return proceed;
    }
}
