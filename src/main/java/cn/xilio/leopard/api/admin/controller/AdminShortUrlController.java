package cn.xilio.leopard.api.admin.controller;

import cn.xilio.leopard.domain.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("short-url")
public class AdminShortUrlController {
    @Autowired
    private ShortUrlService shortUrlService;
}
