package cn.xilio.leopard.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.xilio.leopard.adapter.portal.dto.request.LoginRequest;
import cn.xilio.leopard.adapter.portal.dto.request.RegisterRequest;


public interface UserService {
    /**
     *  Login
     * @param request Login Request info
     * @return Token info
     */
    SaTokenInfo login(LoginRequest request);

    /**
     *  Logout
     */
    void logout();

    /**
     *  Register. Successfully registered and automatically logged in
     * @param request Register Request info
     * @return Token info
     */
    SaTokenInfo registerAndLogin(RegisterRequest request);
}
