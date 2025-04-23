package cn.xilio.leopard.api.portal.controller;

import cn.xilio.leopard.common.util.Result;
import cn.xilio.leopard.domain.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/group")
public class PortalGroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping(value = "save", name = "Save Group")
    public Result saveGroup() {
        return Result.success();
    }

    @DeleteMapping(value = "del", name = "Delete Group")
    public Result deleteGroup() {
        return Result.success();
    }

    @PostMapping(value = "list", name = "Get group list")
    public Result getGroups() {
        return Result.success();
    }

    @GetMapping(value = "get", name = "Get group details")
    public Result getGroup() {
        return Result.success();
    }
}
