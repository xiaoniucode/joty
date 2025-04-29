package cn.xilio.leopard.adapter.admin.controller;


import cn.xilio.leopard.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("short-url")
public class AdminShortUrlController {
    @Autowired
    private ShortUrlService shortUrlService;
}
