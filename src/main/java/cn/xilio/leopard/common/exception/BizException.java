package cn.xilio.leopard.common.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 业务异常类，支持国际化，code 作为资源文件键
 */
@Getter
@Setter
@ToString
public class BizException extends RuntimeException {
    private final int code;

    // 构造函数：指定 code，使用国际化消息
    public BizException(int code, MessageSource messageSource) {
        super(getMessage(code, null, messageSource));
        this.code = code;
    }

    // 构造函数：指定 code 和占位符参数
    public BizException(int code, Object[] args, MessageSource messageSource) {
        super(getMessage(code, args, messageSource));
        this.code = code;
    }

    // 构造函数：自定义消息
    public BizException(int code, String customMessage) {
        super(customMessage);
        this.code = code;
    }

    // 构造函数：默认 BAD_REQUEST 和自定义消息
    public BizException(String customMessage) {
        super(customMessage);
        this.code = HttpStatus.BAD_REQUEST.value();
    }

    // 构造函数：带 Throwable
    public BizException(int code, Throwable throwable, MessageSource messageSource) {
        super(getMessage(code, null, messageSource), throwable);
        this.code = code;
    }

    // 静态校验方法：非空字符串
    public static void noEmpty(String value, String fieldName, MessageSource messageSource) {
        if (!StringUtils.hasText(value)) {
            throw new BizException(HttpStatus.BAD_REQUEST.value(), new Object[]{fieldName}, messageSource);
        }
    }

    // 静态校验方法：非空对象
    public static void noNull(Object value, String fieldName, MessageSource messageSource) {
        if (ObjectUtils.isEmpty(value)) {
            throw new BizException(HttpStatus.BAD_REQUEST.value(), new Object[]{fieldName}, messageSource);
        }
    }

    // 静态校验方法：条件校验
    public static void exprNull(boolean expr, String condition, MessageSource messageSource) {
        if (expr) {
            throw new BizException(HttpStatus.BAD_REQUEST.value(), new Object[]{condition}, messageSource);
        }
    }

    private static String getMessage(int code, Object[] args, MessageSource messageSource) {
        try {
            return messageSource.getMessage(String.valueOf(code), args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return "Unknown error: code=" + code; // 资源缺失时的默认消息
        }
    }
}
