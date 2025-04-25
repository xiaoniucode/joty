package cn.xilio.leopard.api.admin.controller;

import cn.xilio.leopard.domain.stats.service.AccessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stats")
public class AdminStatsController {
    @Autowired
    private AccessRecordService accessRecordService;
}
