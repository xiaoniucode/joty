package cn.xilio.leopard.common.exception;

import cn.xilio.leopard.common.util.ResponseUtil;
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
    public ResponseUtil handleBizException(BizException e) {
        String message = e.getMessageKey() != null
                ? messageSource.getMessage(e.getMessageKey(), null, LocaleContextHolder.getLocale())
                : e.getMessage(); // 自定义消息直接使用
        return ResponseUtil.error(e.getCode(), message);
    }

    @ExceptionHandler(BindException.class)
    public ResponseUtil handleBindException(BindException e) {
        String defaultMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        logger.error("Bind error: {}", defaultMessage);
        String message = messageSource.getMessage("error.default_validation", null, LocaleContextHolder.getLocale());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseUtil handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String defaultMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        logger.error("Validation error: {}", defaultMessage);
        String message = messageSource.getMessage("error.default_validation", null, LocaleContextHolder.getLocale());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseUtil handleGenericException(Exception e) {
        logger.error("Unexpected error", e);
        String message = messageSource.getMessage("error.internal_server_error", null, LocaleContextHolder.getLocale());
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}
