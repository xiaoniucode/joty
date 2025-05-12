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
        return Result.success(statsService.getByUrl(id, userId));
    }

    @GetMapping("get-top-access-ip")
    public Result getTopAccessIps(@RequestParam("shortCode") String shortCode) {
        return Result.success(statsService.getTopAccessIps(shortCode));
    }

    @GetMapping("get-os-access-count")
    public Result getOsAccess(@RequestParam("shortCode") String shortCode) {
        return Result.success(statsService.getOsAccess(shortCode));
    }

    @GetMapping("get-browser-access-count")
    public Result getBrowserAccess(@RequestParam("shortCode") String shortCode) {
        return Result.success(statsService.getBrowserAccess(shortCode));
    }

    @GetMapping("get-device-access-count")
    public Result getDeviceAccess(@RequestParam("shortCode") String shortCode) {
        return Result.success(statsService.getDeviceAccess(shortCode));
    }

    @GetMapping("dashboard")
    public Result dashboard() {
        return Result.success();
    }
}
