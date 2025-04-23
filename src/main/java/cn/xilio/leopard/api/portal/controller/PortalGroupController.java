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

    @PostMapping(value = "save", name = "保存分组")
    public Result saveGroup() {
        return Result.success();
    }

    @DeleteMapping(value = "del", name = "删除分组")
    public Result deleteGroup() {
        return Result.success();
    }

    @PostMapping(value = "list", name = "获取分组列表")
    public Result getGroups() {
        return Result.success();
    }

    @GetMapping(value = "get", name = "获取分组详情")
    public Result getGroup() {
        return Result.success();
    }
}
