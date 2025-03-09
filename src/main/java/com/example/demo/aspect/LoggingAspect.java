package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Aspect for logging method entry, exit, and exceptions.
 */
@Aspect
@Component
public class LoggingAspect {

    /**
     * Pointcut that matches all methods in our service package.
     */
    @Pointcut("execution(* com.example.demo.service.*.*(..))")
    public void serviceMethodExecution() {}

    /**
     * Pointcut that matches all methods in our controller package.
     */
    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void controllerMethodExecution() {}

    /**
     * Log before method execution.
     */
    @Before("serviceMethodExecution() || controllerMethodExecution()")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        Logger logger = getLogger(joinPoint);
        logger.info("Entering method: {} with arguments: {}", 
                joinPoint.getSignature().toShortString(), 
                Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * Log after method execution.
     */
    @AfterReturning(
            pointcut = "serviceMethodExecution() || controllerMethodExecution()",
            returning = "result")
    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
        Logger logger = getLogger(joinPoint);
        logger.info("Method {} completed with return value: {}", 
                joinPoint.getSignature().toShortString(), 
                result);
    }

    /**
     * Log exceptions thrown by methods.
     */
    @AfterThrowing(
            pointcut = "serviceMethodExecution() || controllerMethodExecution()",
            throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        Logger logger = getLogger(joinPoint);
        logger.error("Exception in {}.{}() with cause = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                exception.getCause() != null ? exception.getCause() : "NULL");
        logger.error("Exception details: ", exception);
    }

    /**
     * Get logger for the specific class where the join point is located.
     */
    private Logger getLogger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
    }
} 