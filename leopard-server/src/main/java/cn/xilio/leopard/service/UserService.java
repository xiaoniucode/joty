package cn.xilio.leopard.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.xilio.leopard.adapter.admin.dto.request.UserPageQueryRequest;
import cn.xilio.leopard.adapter.portal.dto.request.LoginRequest;
import cn.xilio.leopard.adapter.portal.dto.request.RegisterRequest;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.domain.dataobject.User;
import cn.xilio.leopard.domain.model.LoginUser;


public interface UserService {
    /**
     * Login
     *
     * @param request Login Request info
     * @return Token info
     */
    SaTokenInfo login(LoginRequest request);

    /**
     * Logout
     */
    void logout();

    /**
     * Register. Successfully registered and automatically logged in
     *
     * @param request Register Request info
     * @return Token info
     */
    SaTokenInfo registerAndLogin(RegisterRequest request);

    /**
     * query user
     *
     * @param request page query request
     * @return user list
     */
    PageResponse<User> list(UserPageQueryRequest request);

    /**
     * Obtain login user information
     *
     * @ param Current Login user
     * @ return  user info
     */
    LoginUser getLoginUser(String userId);
}
