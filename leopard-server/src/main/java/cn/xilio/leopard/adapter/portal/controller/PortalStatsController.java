package cn.xilio.leopard.adapter.portal.controller;

import cn.xilio.leopard.adapter.portal.dto.request.StatsAccessCountRequest;
import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.util.Result;
import cn.xilio.leopard.core.security.SecurityUtils;
import cn.xilio.leopard.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/stats")
public class PortalStatsController {
    @Autowired
    private StatsService statsService;
    @PostMapping("get-access-count-by-type")
    public Result getAccessCountByType(@RequestBody StatsAccessCountRequest request) {
        return Result.success(statsService.getAccessCountByType( request));
    }
    @PostMapping("records")
    public Result records(@RequestParam("shortCode")String shortCode,@RequestBody PageQuery request) {
        return Result.success(statsService.records(shortCode, request));
    }
    @GetMapping("dashboard")
    public Result dashboard() {
        return Result.success();
    }
    @GetMapping("get-data-stats")
    public Result getDataStats() {
        return Result.success();
    }
}
