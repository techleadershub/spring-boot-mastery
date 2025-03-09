package com.example.aop.aspect;

import com.example.aop.annotation.LogMethod;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Aspect for detailed method logging based on @LogMethod annotation
 */
@Aspect
@Component
public class MethodLoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Logs method entry, parameters, return value, and execution time for methods
     * annotated with @LogMethod
     */
    @Around("@annotation(com.example.aop.annotation.LogMethod)")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        // Get method signature and target method
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        
        // Get custom message from annotation if present
        LogMethod logMethodAnnotation = method.getAnnotation(LogMethod.class);
        String customMessage = logMethodAnnotation.value();
        
        // Log method entry
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        String arguments = Arrays.toString(joinPoint.getArgs());
        log.info("ENTER: {} - Parameters: {}{}", 
                methodName, 
                arguments,
                customMessage.isEmpty() ? "" : " - " + customMessage);
        
        // Execute method and measure time
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;
            
            // Log method exit and result
            log.info("EXIT: {} - Return: {} - Execution Time: {} ms{}", 
                    methodName, 
                    result,
                    executionTime,
                    customMessage.isEmpty() ? "" : " - " + customMessage);
            
            return result;
        } catch (Throwable e) {
            log.error("EXCEPTION in {} - Exception: {}{}", 
                    methodName, 
                    e.getMessage(),
                    customMessage.isEmpty() ? "" : " - " + customMessage);
            throw e;
        }
    }
}
