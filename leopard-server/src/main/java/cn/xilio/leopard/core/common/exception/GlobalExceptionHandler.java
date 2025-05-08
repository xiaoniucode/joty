package cn.xilio.leopard.core.common.exception;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.xilio.leopard.core.common.util.LocaleUtils;
import cn.xilio.leopard.core.common.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    public Result handleBizException(BizException e) {
        return Result.error(e.getMessageKey(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e) {
        String defaultMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        logger.error("Bind error: {}", defaultMessage);
        String message = LocaleUtils.getLocaleMessage("400", new Object[]{defaultMessage});
        return Result.error(Integer.toString(HttpStatus.BAD_REQUEST.value()), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String defaultMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        logger.error("Validation error: {}", defaultMessage);
        String message = LocaleUtils.getLocaleMessage("400", new Object[]{defaultMessage});
        return Result.error(Integer.toString(HttpStatus.BAD_REQUEST.value()), message);
    }
    @ExceptionHandler(NotLoginException.class)
    public Result handleNotLoginException(NotLoginException e) {
        String message = LocaleUtils.getLocaleMessage("401");
        return Result.error(Integer.toString(HttpStatus.UNAUTHORIZED.value()), message);
    }


    @ExceptionHandler(NotRoleException.class)
    public Result handleNotRoleException(NotRoleException e) {
        String message = LocaleUtils.getLocaleMessage("403");
        return Result.error(Integer.toString(HttpStatus.FORBIDDEN.value()), message);
    }
    @ExceptionHandler(Exception.class)
    public Result handleGenericException(Exception e) {
        logger.error("Unexpected error", e);
        String message = LocaleUtils.getLocaleMessage("5000");
        return Result.error(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), message);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Result handleGenericException(NoResourceFoundException e) {
        String message = LocaleUtils.getLocaleMessage("404", null);
        return Result.error("404", message);
    }

}
