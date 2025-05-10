package cn.xilio.leopard.service.impl;

import cn.xilio.leopard.adapter.admin.dto.request.AddUserRequest;
import cn.xilio.leopard.adapter.admin.dto.request.UpdateUserRequest;
import cn.xilio.leopard.adapter.admin.dto.request.UserPageQueryRequest;
import cn.xilio.leopard.adapter.portal.dto.request.LoginRequest;
import cn.xilio.leopard.adapter.portal.dto.request.RegisterRequest;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.core.config.CacheManager;
import cn.xilio.leopard.core.security.SecurityUtils;
import cn.xilio.leopard.core.security.TokenInfo;
import cn.xilio.leopard.domain.CacheKey;
import cn.xilio.leopard.domain.enums.UserRole;
import cn.xilio.leopard.domain.model.LoginUser;
import cn.xilio.leopard.service.UserService;
import cn.xilio.leopard.domain.event.LoginEvent;
import cn.xilio.leopard.domain.event.LogoutEvent;
import cn.xilio.leopard.domain.dataobject.User;
import cn.xilio.leopard.domain.enums.UserStatus;
import cn.xilio.leopard.repository.UserRepository;
import cn.xilio.leopard.core.common.exception.BizException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private CacheManager cacheManager;

    /**
     * Login
     *
     * @param request Login Request info
     * @return Token info
     */
    @Override
    public TokenInfo login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username());
        BizException.checkNull("6001", user);
        BizException.checkExpr("6003", UserStatus.DISABLED.getCode() == user.getStatus());
        TokenInfo tokenInfo = SecurityUtils.login(user.getId());
        eventPublisher.publishEvent(new LoginEvent(this));
        return tokenInfo;
    }

    /**
     * Logout
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void logout() {
        String uid = SecurityUtils.getLoginIdAsString();
        SecurityUtils.logout();
        cacheManager.delete(CacheKey.LOGIN_USER + uid);
        eventPublisher.publishEvent(new LogoutEvent(this));
    }

    /**
     * Register. Successfully registered and automatically logged in
     *
     * @param request Register Request info
     * @return Token info
     */
    @Override
    public TokenInfo registerAndLogin(RegisterRequest request) {
        //check username
        User dbUser = userRepository.findByUsername(request.username());
        BizException.checkExpr("6004", !ObjectUtils.isEmpty(dbUser));
        User newUser = request.toUser();
        newUser.setRole(UserRole.USER.name());
        newUser.setStatus(UserStatus.NORMAL.getCode());
        newUser.setNickname(request.username());
        //密码加密

        User regResult = userRepository.saveUser(newUser);
        //Auto login
        TokenInfo tokenInfo = SecurityUtils.login(regResult.getId());
        eventPublisher.publishEvent(new LoginEvent(this));
        return tokenInfo;
    }

    /**
     * query user
     *
     * @param request page query request
     * @return user list
     */
    @Override
    public PageResponse<User> list(UserPageQueryRequest request) {
        return userRepository.selectUsers(request);

    }

    /**
     * Obtain login user information
     *
     * @param userId
     * @ param Current Login user
     * @ return  user info
     */
    @Override
    public LoginUser getLoginUser(String userId) {
        User user = userRepository.findById(userId);
        LoginUser loginUser = new LoginUser();
        loginUser.setUid(user.getId());
        loginUser.setUsername(user.getUsername());
        loginUser.setNickname(user.getNickname());
        loginUser.setAvatar(user.getAvatar());
        loginUser.setEmail(user.getEmail());
        loginUser.setRole(user.getRole());
        return loginUser;
//        return cacheManager.get(CacheKey.LOGIN_USER + userId, s -> {
//
//        });

    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByUserId(String id) {
        return userRepository.findById(id);
    }

    /**
     * Save User
     *
     * @param request User info
     */
    @Override
    public void addUser(AddUserRequest request) {
        //check username
        User oldUser = userRepository.findByUsername(request.username());
        BizException.checkExpr("6004", !ObjectUtils.isEmpty(oldUser));
        User newUser = request.toUser();
        newUser.setRole(UserRole.USER.name());
        newUser.setStatus(UserStatus.NORMAL.getCode());
        //密码加密
        newUser.setPassword(request.password());
        userRepository.saveUser(newUser);
    }

    /**
     * Update user
     *
     * @param request User info
     */
    @Override
    public void updateUser(UpdateUserRequest request) {
        User oldUser = userRepository.findById(request.id());
        BizException.checkNull("6001", oldUser);
        BeanUtils.copyProperties(request, oldUser);
        //是否是超级管理员 改类型管理员只有一个
        if (UserRole.ADMIN.name().equalsIgnoreCase(oldUser.getRole())) {
            //超级管理员的状态不能够进行修改
            oldUser.setStatus(UserStatus.NORMAL.getCode());
        }
        userRepository.saveUser(oldUser);
    }

    /**
     * Delete user by id
     *
     * @param ids id list
     */
    @Override
    public void deleteUser(List<String> ids) {
        userRepository.logicDeleteUser(ids);
    }
}
