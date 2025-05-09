package cn.xilio.leopard.core.common.util;


import cn.xilio.leopard.core.security.SpringHelper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * International Chemical Tools
 */
public abstract class LocaleUtils {
    public static String getLocaleMessage(String messageKey) {
        return getLocaleMessage(messageKey, null);
    }

    public static String getLocaleMessage(String messageKey, Object[] args) {
        MessageSource messageSource = SpringHelper.getBean(MessageSource.class);
        return messageSource.getMessage(messageKey, args, messageKey, LocaleContextHolder.getLocale());
    }
}
