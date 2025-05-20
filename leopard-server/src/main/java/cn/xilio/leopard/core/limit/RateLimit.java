package cn.xilio.leopard.core.limit;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {
    /**
     * 限流key（默认使用方法签名）
     */
    String key() default "";

    /**
     * 每秒允许的请求数（QPS）
     */
    double rate() default 10.0;

    /**
     * 预热时间（秒）
     */
    int warmupPeriod() default 1;

    /**
     * 限流提示信息
     */
    String message() default "1100";
}
