package cn.xilio.leopard.adapter.portal.controller;

import cn.xilio.leopard.core.common.util.Result;
import cn.xilio.leopard.core.security.SecurityUtils;
import cn.xilio.leopard.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stats")
public class PortalStatsController {
    @Autowired
    private StatsService statsService;
    @GetMapping("get-by-url")
    public Result getByUrl(@RequestParam("id") String id) {
        String userId = SecurityUtils.getLoginIdAsString();
        return Result.success(statsService.getByUrl(id,userId));
    }

    @GetMapping("dashboard")
    public Result dashboard() {

        return Result.success();
    }
}
