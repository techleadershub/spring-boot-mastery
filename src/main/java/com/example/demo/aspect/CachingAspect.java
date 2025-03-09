package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Aspect for caching method results.
 */
@Aspect
@Component
public class CachingAspect {

    private static final Logger logger = LoggerFactory.getLogger(CachingAspect.class);
    
    // Simple in-memory cache
    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    /**
     * Pointcut that matches the fibonacci method in our CachingExampleService.
     */
    @Pointcut("execution(* com.example.demo.service.CachingExampleService.fibonacci(..))")
    public void fibonacciMethod() {}

    /**
     * Cache the results of the fibonacci method.
     */
    @Around("fibonacciMethod()")
    public Object cacheFibonacciResult(ProceedingJoinPoint joinPoint) throws Throwable {
        // Create a cache key based on method name and arguments
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        String cacheKey = methodName + Arrays.toString(args);
        
        // Check if result is in cache
        if (cache.containsKey(cacheKey)) {
            logger.info("Cache hit for {}", cacheKey);
            return cache.get(cacheKey);
        }
        
        // If not in cache, execute the method
        logger.info("Cache miss for {}", cacheKey);
        Object result = joinPoint.proceed();
        
        // Store result in cache
        cache.put(cacheKey, result);
        logger.info("Cached result for {}", cacheKey);
        
        return result;
    }
} 