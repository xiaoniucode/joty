package cn.xilio.leopard.biz.adapter.portal.controller;


import cn.xilio.leopard.biz.adapter.portal.dto.request.CreateGroupRequest;
import cn.xilio.leopard.biz.domain.group.service.GroupService;
import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/group")
public class PortalGroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping(value = "save", name = "Save Group")
    public Result saveGroup(@Validated @RequestBody CreateGroupRequest request) {
        groupService.saveGroup(request);
        return Result.success();
    }

    @DeleteMapping(value = "del", name = "Delete Group")
    public Result deleteGroup(@RequestBody List<String> ids) {
        groupService.deleteGroup(ids);
        return Result.success();
    }

    @PostMapping(value = "list", name = "Get group list")
    public Result getGroups(@Validated @RequestBody PageQuery request) {
        return Result.success(groupService.page(request));
    }

    @GetMapping(value = "get", name = "Get group details")
    public Result getGroup(@RequestParam  String id) {
        return Result.success(groupService.getById(id));
    }
}
