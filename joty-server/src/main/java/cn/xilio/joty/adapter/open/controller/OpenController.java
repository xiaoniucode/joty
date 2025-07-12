package cn.xilio.joty.adapter.open.controller;

import cn.xilio.joty.adapter.open.request.CreateShortUrlRequest;
import cn.xilio.joty.core.common.util.Result;
import cn.xilio.joty.core.limit.RateLimit;
import cn.xilio.joty.service.ShortUrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1", name = "Open Interface")
@RequiredArgsConstructor
@Tag(name = "短链接平台开放接口")
public class OpenController {
    @Autowired
    private ShortUrlService shortUrlService;

    @Operation(summary = "创建短链接", parameters = {
            @Parameter(name = "urls",
                    description = "需要缩短的链接，每行一个",
                    required = true,
                    example = "abc123")
    })
    @RateLimit(rate = 20)
    @PostMapping(value = "short-url/create", name = "Create shorturl")
    public Result createShortUrl(@Validated @RequestBody CreateShortUrlRequest request) {
        return Result.success();
    }

    @Operation(summary = "获取短链接详情")
    @RateLimit(rate = 20)
    @GetMapping(value = "short-url/get", name = "Get shorturl info")
    public Result getShortUrl(@RequestParam("id") String id) {
        return Result.success();
    }

    @Operation(summary = "删除短链接")
    @RateLimit(rate = 20)
    @DeleteMapping(value = "short-url/del", name = "Delete shorturl")
    public Result deleteShortUrl(@RequestParam("id") String id) {
        return Result.success();
    }

}
