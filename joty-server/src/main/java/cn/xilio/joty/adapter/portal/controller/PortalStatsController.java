package cn.xilio.joty.adapter.portal.controller;

import cn.xilio.joty.adapter.portal.dto.request.DailyAccessStatsRequest;
import cn.xilio.joty.adapter.portal.dto.request.StatsAccessCountRequest;
import cn.xilio.joty.core.common.page.PageQuery;
import cn.xilio.joty.core.common.util.Result;
import cn.xilio.joty.core.security.SecurityUtils;
import cn.xilio.joty.service.StatsService;
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

    @PostMapping("get-daily-access-stats")
    public Result getDailyAccessStats(@RequestBody DailyAccessStatsRequest request) {
        return Result.success(statsService.getDailyAccessStats(request.startDate(),request.endDate(), request.shortCode()));
    }

}
