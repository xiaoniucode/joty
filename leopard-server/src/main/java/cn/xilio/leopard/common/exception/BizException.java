package cn.xilio.leopard.common.exception;

import cn.xilio.leopard.common.util.LocaleUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 业务异常类，支持国际化，code 作为资源文件键
 */
@Getter
@Setter
@ToString
public class BizException extends RuntimeException {
    private final String messageKey;

    public BizException(String messageKey) {
        super(LocaleUtils.getLocaleMessage(messageKey, null));
        this.messageKey = messageKey;
    }

    public BizException(String messageKey, Object[] args) {
        super(LocaleUtils.getLocaleMessage(messageKey, args));
        this.messageKey = messageKey;
    }

    public static void checkEmpty(String messageKey, String value) {
        if (!StringUtils.hasText(value)) {
            throw new BizException(messageKey, null);
        }
    }

    public static void checkNull(String messageKey, Object value) {
        if (ObjectUtils.isEmpty(value)) {
            throw new BizException(messageKey, null);
        }
    }

    public static void checkExpr(String messageKey, boolean expr) {
        if (expr) {
            throw new BizException(messageKey, null);
        }
    }
}
