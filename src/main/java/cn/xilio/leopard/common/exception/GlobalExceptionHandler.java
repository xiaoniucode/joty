package cn.xilio.leopard.common.exception;

import cn.xilio.leopard.common.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BizException.class)
    public Result handleBizException(BizException e) {
        String message = e.getMessage(); // 优先使用构造时解析的消息
        if (e.getMessage().startsWith("Unknown error")) { // 资源缺失时重新解析
            try {
                message = messageSource.getMessage(String.valueOf(e.getCode()), null, LocaleContextHolder.getLocale());
            } catch (Exception ex) {
                message = "Unknown error: code=" + e.getCode();
            }
        }
        return Result.error(e.getCode(), message);
    }

    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e) {
        String defaultMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        logger.error("Bind error: {}", defaultMessage);
        String message = messageSource.getMessage("400", new Object[]{defaultMessage}, LocaleContextHolder.getLocale());
        return Result.error(HttpStatus.BAD_REQUEST.value(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String defaultMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        logger.error("Validation error: {}", defaultMessage);
        String message = messageSource.getMessage("400", new Object[]{defaultMessage}, LocaleContextHolder.getLocale());
        return Result.error(HttpStatus.BAD_REQUEST.value(), message);
    }

    @ExceptionHandler(Exception.class)
    public Result handleGenericException(Exception e) {
        logger.error("Unexpected error", e);
        String message = messageSource.getMessage("5000", null, LocaleContextHolder.getLocale());
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}
