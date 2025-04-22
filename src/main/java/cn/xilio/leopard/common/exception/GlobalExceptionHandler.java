package cn.xilio.leopard.common.exception;


import cn.xilio.leopard.common.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    public ResponseUtil BizException(BizException e) {
        return ResponseUtil.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(BindException.class)
    public ResponseUtil bindExceptionHandler(BindException e) {
        logger.error(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseUtil methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        logger.error(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }
}
