package com.example.demo.aspect;

import com.example.demo.aspect.annotation.LogExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Aspect for monitoring method execution performance.
 */
@Aspect
@Component
public class PerformanceMonitoringAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);

    /**
     * Pointcut that matches all methods in our service package.
     */
    @Pointcut("execution(* com.example.demo.service.*.*(..))")
    public void serviceMethodExecution() {}

    /**
     * Pointcut that matches all methods annotated with @LogExecutionTime.
     */
    @Pointcut("@annotation(com.example.demo.aspect.annotation.LogExecutionTime)")
    public void logExecutionTimeAnnotation() {}

    /**
     * Monitor execution time for all service methods.
     */
    @Around("serviceMethodExecution()")
    public Object monitorServiceMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return monitorExecutionTime(joinPoint);
    }

    /**
     * Monitor execution time for methods annotated with @LogExecutionTime.
     */
    @Around("logExecutionTimeAnnotation()")
    public Object monitorAnnotatedMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return monitorExecutionTime(joinPoint);
    }

    /**
     * Helper method to monitor execution time.
     */
    private Object monitorExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        try {
            // Execute the method
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            
            // Log execution time
            String methodName = joinPoint.getSignature().toShortString();
            long executionTimeMs = stopWatch.getTotalTimeMillis();
            
            if (executionTimeMs > 1000) {
                logger.warn("PERFORMANCE ALERT - Method {} took {}ms to execute", methodName, executionTimeMs);
            } else if (executionTimeMs > 500) {
                logger.info("PERFORMANCE NOTICE - Method {} took {}ms to execute", methodName, executionTimeMs);
            } else {
                logger.debug("Method {} took {}ms to execute", methodName, executionTimeMs);
            }
        }
    }
} 