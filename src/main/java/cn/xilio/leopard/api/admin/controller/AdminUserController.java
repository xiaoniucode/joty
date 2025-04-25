package cn.xilio.leopard.api.admin.controller;

import cn.xilio.leopard.common.page.PageQuery;
import cn.xilio.leopard.common.util.Result;
import cn.xilio.leopard.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "list", name = "All user list")
    public Result list(@Validated @RequestBody PageQuery request) {
        return Result.success();
    }

    @GetMapping(value = "get", name = "Get user info")
    public Result get(@RequestParam String userId) {
        return Result.success();
    }

    @DeleteMapping(value = "del", name = "Batch delete user")
    public Result del(@RequestBody List<String> ids) {
        return Result.success();
    }

}
