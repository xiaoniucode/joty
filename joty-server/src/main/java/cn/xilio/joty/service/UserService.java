package cn.xilio.joty.service;

import cn.xilio.joty.adapter.admin.dto.request.AddUserRequest;
import cn.xilio.joty.adapter.admin.dto.request.UpdateUserRequest;
import cn.xilio.joty.adapter.admin.dto.request.UserPageQueryRequest;
import cn.xilio.joty.adapter.portal.dto.request.LoginRequest;
import cn.xilio.joty.adapter.portal.dto.request.RegisterRequest;
import cn.xilio.joty.core.common.page.PageResponse;
import cn.xilio.joty.core.security.TokenInfo;
import cn.xilio.joty.domain.dataobject.User;
import cn.xilio.joty.domain.model.LoginUser;

import java.util.List;


public interface UserService {
    /**
     * Login
     *
     * @param request Login Request info
     * @return Token info
     */
    TokenInfo login(LoginRequest request);

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
    TokenInfo registerAndLogin(RegisterRequest request);

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

    User getUserByUsername(String username);
    User getUserByUserId(String id);

    /**
     * Add a User
     * @param request User info
     */
    void addUser(AddUserRequest request);

    /**
     * Update user
     * @param request User info
     */
    void updateUser(UpdateUserRequest request);

    /**
     * Delete user by id
     * @param ids id list
     */
    void deleteUser(List<String> ids);

    /**
     * Reset open api key
     * @return open api key after reset
     */
    String resetOpenApiKey();

    /**
     *  Get open api key
     * @return open api key
     */
    String getOpenApiKey();

    /**
     *
     * @param apiKey
     * @return
     */
    boolean isValidApiKey(String apiKey);
}
