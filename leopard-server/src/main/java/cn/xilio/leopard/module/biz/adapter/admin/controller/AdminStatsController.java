package cn.xilio.leopard.module.biz.adapter.admin.controller;


import cn.xilio.leopard.module.biz.service.AccessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stats")
public class AdminStatsController {
    @Autowired
    private AccessRecordService accessRecordService;
}
