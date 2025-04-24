package cn.xilio.leopard.domain.user.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.xilio.leopard.api.portal.dto.request.LoginRequest;
import cn.xilio.leopard.common.exception.BizException;
import cn.xilio.leopard.domain.user.model.User;
import cn.xilio.leopard.domain.user.repository.UserRepository;
import cn.xilio.leopard.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Login
     *
     * @param request Login Request info
     * @return Token info
     */
    @Override
    public SaTokenInfo login(LoginRequest request) {
        User user = userRepository.getByName(request.username());
        if (ObjectUtils.isEmpty(user)) {
            throw new BizException(400, "Incorrect username or password");
        }
        StpUtil.login(user.getId(), new SaLoginParameter()
                .setIsLastingCookie(true)
                .setIsWriteHeader(true));
        return StpUtil.getTokenInfo();
    }

    /**
     * Logout
     */
    @Override
    public void logout() {
        String uid = StpUtil.getLoginIdAsString();
        StpUtil.logout(uid);
    }
}
