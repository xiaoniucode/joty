package cn.xilio.leopard.api.open.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("open")
@RequiredArgsConstructor
public class OpenController {
    @Autowired
    private MessageSource messageSource;

}
