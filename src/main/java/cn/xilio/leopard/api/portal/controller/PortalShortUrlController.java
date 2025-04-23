package cn.xilio.leopard.api.portal.controller;

import cn.xilio.leopard.common.util.Result;
import cn.xilio.leopard.domain.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/short-url")
public class PortalShortUrlController {
    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping(value = "create", name = "创建单个")
    public Result createSingleShortUrl() {

        return Result.success();
    }

    @PostMapping(value = "create-batch", name = "批量创建")
    public Result createBatchShortUrl() {

        return Result.success();
    }

    @GetMapping(value = "list", name = "")
    public Result getShortUrls() {
        return Result.success();
    }

    @DeleteMapping(value = "del", name = "")
    public Result deleteShortUrls() {
        return Result.success();
    }

}
