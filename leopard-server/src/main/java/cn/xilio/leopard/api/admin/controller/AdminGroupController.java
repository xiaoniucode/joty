package cn.xilio.leopard.api.admin.controller;

import cn.xilio.leopard.domain.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("group")
public class AdminGroupController {
    @Autowired
    private GroupService groupService;
}
