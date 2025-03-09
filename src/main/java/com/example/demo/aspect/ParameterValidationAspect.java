package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Aspect for validating method parameters.
 */
@Aspect
@Component
public class ParameterValidationAspect {

    private static final Logger logger = LoggerFactory.getLogger(ParameterValidationAspect.class);

    /**
     * Pointcut that matches the timeConsumingMethod in our service.
     */
    @Pointcut("execution(* com.example.demo.service.ExampleService.timeConsumingMethod(..))")
    public void timeConsumingMethod() {}

    /**
     * Validate parameters for timeConsumingMethod.
     */
    @Before("timeConsumingMethod()")
    public void validateTimeConsumingMethodParameters(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        
        // Validate that delay is positive
        if (args.length >= 2 && args[1] instanceof Integer) {
            int delay = (Integer) args[1];
            if (delay <= 0) {
                logger.warn("Parameter validation: delay should be positive, but got {}", delay);
            } else if (delay > 10000) {
                logger.warn("Parameter validation: delay is very high ({}ms), which may cause performance issues", delay);
            }
        }
        
        // Log parameter names and values for demonstration
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        
        for (int i = 0; i < parameters.length && i < args.length; i++) {
            logger.debug("Parameter '{}' has value: {}", parameters[i].getName(), args[i]);
        }
    }
} 