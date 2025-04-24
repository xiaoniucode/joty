package cn.xilio.leopard.api.portal.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.xilio.leopard.api.portal.dto.request.LoginRequest;
import cn.xilio.leopard.common.util.Result;
import cn.xilio.leopard.domain.user.service.UserService;
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
    @PostMapping("logout")
    public Result logout() {
        userService.logout();
        return Result.success();
    }
}
