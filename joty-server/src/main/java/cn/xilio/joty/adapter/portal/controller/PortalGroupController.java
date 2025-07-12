package cn.xilio.joty.adapter.portal.controller;


import cn.xilio.joty.adapter.portal.dto.request.CreateGroupRequest;
import cn.xilio.joty.core.security.SecurityUtils;
import cn.xilio.joty.service.GroupService;
import cn.xilio.joty.core.common.page.PageQuery;
import cn.xilio.joty.core.common.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="分组接口")
@RestController
@RequestMapping("api/group")
public class PortalGroupController {
    @Autowired
    private GroupService groupService;

    @Operation(summary = "保存分组")
    @PostMapping(value = "save", name = "Save Group")
    public Result saveGroup(@Validated @RequestBody CreateGroupRequest request) {
        groupService.saveGroup(request);
        return Result.success();
    }

    @Operation(summary = "删除分组")
    @DeleteMapping(value = "del", name = "Delete Group")
    public Result deleteGroup(@RequestBody List<String> ids) {
        groupService.deleteGroup(ids);
        return Result.success();
    }

    @Operation(summary = "获取分组列表")
    @PostMapping(value = "list", name = "Get group list")
    public Result getGroups(@Validated @RequestBody PageQuery request) {
        String uid = SecurityUtils.getLoginIdAsString();
        return Result.success(groupService.page(request,uid));
    }

    @Operation(summary = "获取分组详情")
    @GetMapping(value = "get", name = "Get group details")
    public Result getGroup(@RequestParam String id) {
        return Result.success(groupService.getById(id));
    }
}
