package com.bonus.sys;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Aspect
@Component
public class RateLimiterAspect {

    private static final ConcurrentMap<String, Integer> rateLimiterCache = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.example.springbootdemo.annotation.RateLimiter)")
    public void rateLimit() {
    }

    @Around("rateLimit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //获取注解
        RateLimiter rateLimiter = AnnotationUtils.findAnnotation(method, RateLimiter.class);
        String now = LocalDate.now().toString();
        String timeKey = now + "_" + method.getName();
        if (rateLimiter != null && rateLimiter.limit() > 0) {
            int limit = rateLimiter.limit();
            if (rateLimiterCache.get(timeKey) == null) {
                rateLimiterCache.clear();
                rateLimiterCache.put(timeKey, --limit);
            } else {
                limit = rateLimiterCache.get(timeKey);
                if (limit > 0) {
                    rateLimiterCache.put(timeKey, --limit);
                } else {
                    return "访问受限,明日重试";
                }
            }
        }
        return point.proceed();
    }
}

