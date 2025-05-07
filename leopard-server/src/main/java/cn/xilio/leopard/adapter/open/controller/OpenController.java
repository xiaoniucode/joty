package cn.xilio.leopard.adapter.open.controller;

import cn.xilio.leopard.adapter.open.request.CreateShortUrlRequest;
import cn.xilio.leopard.core.common.util.Result;
import cn.xilio.leopard.service.ShortUrlService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1", name = "Open Interface")
@RequiredArgsConstructor
public class OpenController {
    @Autowired
    private ShortUrlService shortUrlService;

    @Operation(summary = "创建短链接")
    @PostMapping(value = "short-url/create", name = "Create shorturl")
    public Result createShortUrl(@Validated @RequestBody CreateShortUrlRequest request) {
        return Result.success();
    }

    @Operation(summary = "获取短链接详情")
    @GetMapping(value = "short-url/get", name = "Get shorturl info")
    public Result getShortUrl(@RequestParam("id") String id) {
        return Result.success();
    }

    @Operation(summary = "删除短链接")
    @DeleteMapping(value = "short-url/del", name = "Delete shorturl")
    public Result deleteShortUrl(@RequestParam("id") String id) {
        return Result.success();
    }

}
