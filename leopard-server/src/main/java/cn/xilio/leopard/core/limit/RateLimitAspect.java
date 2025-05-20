package cn.xilio.leopard.core.limit;

import cn.xilio.leopard.core.common.exception.BizException;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@SuppressWarnings("all")
public class RateLimitAspect {
    // 限流器缓存（key: 方法签名）
    private final ConcurrentHashMap<String, RateLimiter> limiters = new ConcurrentHashMap<>();

    @Around("@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = getLimitKey(joinPoint, rateLimit);
        RateLimiter limiter = limiters.computeIfAbsent(key,
            k -> rateLimit.warmupPeriod() > 0
                ? RateLimiter.create(rateLimit.rate(), rateLimit.warmupPeriod(), TimeUnit.SECONDS)
                : RateLimiter.create(rateLimit.rate()));

        if (!limiter.tryAcquire()) {
            throw new BizException(rateLimit.messageKey());
        }
        return joinPoint.proceed();
    }

    private String getLimitKey(ProceedingJoinPoint joinPoint, RateLimit rateLimit) {
        if (!rateLimit.key().isEmpty()) {
            return rateLimit.key();
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return String.format("%s.%s",
            signature.getDeclaringTypeName(),
            signature.getMethod().getName());
    }
}
