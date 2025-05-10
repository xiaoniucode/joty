package cn.xilio.leopard.adapter.admin.controller;

import cn.xilio.leopard.core.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class AdminStatsController {

    @GetMapping(value = "stats-count",name = "Get stats count")
    public Result getStatsCount() {
        return Result.success();
    }
}
