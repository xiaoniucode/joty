package cn.xilio.leopard.biz.adapter.portal.controller;


import cn.xilio.leopard.biz.adapter.portal.dto.request.LoginRequest;
import cn.xilio.leopard.biz.adapter.portal.dto.request.RegisterRequest;
import cn.xilio.leopard.biz.domain.user.service.UserService;
import cn.xilio.leopard.core.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class PortalUserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@Validated @RequestBody LoginRequest request) {
        return Result.success(userService.login(request));
    }

    @PostMapping("register")
    public Result register(@Validated @RequestBody RegisterRequest request) {
        return Result.success(userService.registerAndLogin(request));
    }

    @PostMapping("logout")
    public Result logout() {
        userService.logout();
        return Result.success();
    }
}
