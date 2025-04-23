package cn.xilio.leopard.api.portal.controller;

import cn.xilio.leopard.api.portal.dto.request.CreateBatchShortUrlRequest;
import cn.xilio.leopard.api.portal.dto.request.CreateSingleShortUrlRequest;
import cn.xilio.leopard.common.page.PageQuery;
import cn.xilio.leopard.common.util.Result;
import cn.xilio.leopard.domain.shorturl.model.ShortUrl;
import cn.xilio.leopard.domain.shorturl.service.ShortUrlService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/short-url")
@Tag(name = "Short Link")
public class PortalShortUrlController {
    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping(value = "create", name = "Create a single")
    public Result createSingleShortUrl(@Validated @RequestBody CreateSingleShortUrlRequest request) {
        return Result.success(shortUrlService.createSingle(request));
    }

    @PostMapping(value = "create-batch", name = "Batch creation")
    public Result createBatchShortUrl(@Validated @RequestBody CreateBatchShortUrlRequest request) {
        return Result.success(shortUrlService.createBatchShortUrl(request));
    }

    @PostMapping(value = "list", name = "Short link pagination list")
    public Result getShortUrls(@Validated @RequestBody PageQuery request) {
        return Result.success(shortUrlService.getShortUrls(request));
    }

    @DeleteMapping(value = "del", name = "Batch delete short links")
    public Result deleteShortUrls(@RequestBody List<String> ids) {
        shortUrlService.deleteShortUrl(ids);
        return Result.success();
    }

    @GetMapping(value = "get", name = "Get short link information")
    public Result getShortUrlInfo(@RequestParam String id) {
        return Result.success(shortUrlService.getShortUrlInfo(id));
    }

}
