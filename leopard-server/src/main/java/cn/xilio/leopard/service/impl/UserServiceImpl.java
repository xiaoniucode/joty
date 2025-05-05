package cn.xilio.leopard.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;

import cn.xilio.leopard.adapter.admin.dto.request.UserPageQueryRequest;
import cn.xilio.leopard.adapter.portal.dto.request.LoginRequest;
import cn.xilio.leopard.adapter.portal.dto.request.RegisterRequest;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.service.UserService;
import cn.xilio.leopard.domain.event.LoginEvent;
import cn.xilio.leopard.domain.event.LogoutEvent;
import cn.xilio.leopard.domain.dataobject.User;
import cn.xilio.leopard.domain.enums.UserStatus;
import cn.xilio.leopard.repository.UserRepository;
import cn.xilio.leopard.core.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * Login
     *
     * @param request Login Request info
     * @return Token info
     */
    @Override
    public SaTokenInfo login(LoginRequest request) {
        User user = userRepository.getByName(request.username());
        BizException.checkNull("6001", user);
        BizException.checkExpr("6003", UserStatus.DISABLED.getCode() == user.getStatus());
        StpUtil.login(user.getId(), new SaLoginParameter()
                .setIsLastingCookie(true)
                .setIsWriteHeader(true));
        eventPublisher.publishEvent(new LoginEvent(this));
        return StpUtil.getTokenInfo();
    }

    /**
     * Logout
     */
    @Override
    public void logout() {
        String uid = StpUtil.getLoginIdAsString();
        StpUtil.logout(uid);
        eventPublisher.publishEvent(new LogoutEvent(this));
    }

    /**
     * Register. Successfully registered and automatically logged in
     *
     * @param request Register Request info
     * @return Token info
     */
    @Override
    public SaTokenInfo registerAndLogin(RegisterRequest request) {
        //check username
        User dbUser = userRepository.getByName(request.username());
        BizException.checkExpr("6004", !ObjectUtils.isEmpty(dbUser));
        User newUser = request.toUser();
        newUser.setIsAdmin(false);
        User regResult = userRepository.saveUser(newUser);
        //Auto login
        StpUtil.login(regResult.getId(), new SaLoginParameter()
                .setIsLastingCookie(true)
                .setIsWriteHeader(true));
        eventPublisher.publishEvent(new LoginEvent(this));
        return StpUtil.getTokenInfo();
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
}
