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
     * 限流提示信息 支持国际化 国际化需要填写key 也可以直接填写文字 但是不支持国际化
     */
    String messageKey() default "访问速度太快了";
}
