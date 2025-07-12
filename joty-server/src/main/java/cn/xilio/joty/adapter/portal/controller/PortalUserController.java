package cn.xilio.joty.adapter.portal.controller;


import cn.xilio.joty.adapter.portal.dto.request.LoginRequest;
import cn.xilio.joty.adapter.portal.dto.request.RegisterRequest;
import cn.xilio.joty.core.limit.RateLimit;
import cn.xilio.joty.core.security.SecurityUtils;
import cn.xilio.joty.domain.model.LoginUser;
import cn.xilio.joty.service.UserService;
import cn.xilio.joty.core.common.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@Tag(name = "用户接口")
public class PortalUserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "用户登录")
    @PostMapping("login")
    public Result login(@Validated @RequestBody LoginRequest request) {
        return Result.success(userService.login(request));
    }

    @Operation(summary = "获取登陆用户信息")
    @GetMapping("get")
    public Result get() {
        String userId = SecurityUtils.getLoginIdAsString();
        LoginUser loginUser = userService.getLoginUser(userId);
        return Result.success(loginUser);
    }

    @Operation(summary = "用户注册")
    @PostMapping("register")
    public Result register(@Validated @RequestBody RegisterRequest request) {
        return Result.success(userService.registerAndLogin(request));
    }

    @Operation(summary = "登出登陆")
    @PostMapping("logout")
    public Result logout() {
        userService.logout();
        return Result.success();
    }

    @Operation(summary = "获取开放接口api-key")
    @GetMapping("get-api-key")
    public Result getApiKey() {
        String apiKey = userService.getOpenApiKey();
        return Result.success(apiKey);
    }

    @Operation(summary = "重置开放接口api-key")
    @PutMapping("reset-api-key")
    public Result resetApiKey() {
        String apiKey = userService.resetOpenApiKey();
        return Result.success(apiKey);
    }
}
