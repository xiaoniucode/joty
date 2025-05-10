package cn.xilio.leopard.adapter.admin.controller;

import cn.xilio.leopard.adapter.admin.dto.request.AddUserRequest;
import cn.xilio.leopard.adapter.admin.dto.request.UpdateUserRequest;
import cn.xilio.leopard.adapter.admin.dto.request.UserPageQueryRequest;
import cn.xilio.leopard.service.UserService;
import cn.xilio.leopard.core.common.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AdminUserController {
    @Autowired
    private UserService userService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "list", name = "All user list")
    public Result list(@Validated @RequestBody UserPageQueryRequest request, HttpServletRequest request2) {
        return Result.success(userService.list(request));
    }

    @GetMapping(value = "get", name = "Get user info")
    public Result get(@RequestParam String userId) {
        return Result.success();
    }
    @PostMapping(value = "add", name = "Add a user  ")
    public Result add(@RequestBody AddUserRequest request) {
        userService.addUser(request);
        return Result.success();
    }
    @PutMapping(value = "add", name = "Add a user  ")
    public Result update(@RequestBody UpdateUserRequest request) {
        userService.updateUser(request);
        return Result.success();
    }

    @DeleteMapping(value = "del", name = "Batch delete user")
    public Result del(@RequestBody List<String> ids) {
        return Result.success();
    }

}
