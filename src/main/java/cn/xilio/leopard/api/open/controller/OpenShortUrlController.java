package cn.xilio.leopard.api.open.controller;

import cn.xilio.leopard.common.exception.BizException;
import cn.xilio.leopard.common.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/open")
@RequiredArgsConstructor
public class OpenShortUrlController {
    @Autowired
    private MessageSource messageSource;


    @GetMapping("/test")
    public ResponseEntity<Object> createShortUrl() {
        throw new BizException(1004, messageSource);
    }
}
