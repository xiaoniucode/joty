package cn.xilio.leopard.biz.infrastructure.config.jpa;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        if (StpUtil.isLogin()) {
            String uid = StpUtil.getLoginIdAsString();
            if (StringUtils.hasText(uid)) {
                return Optional.of(uid);
            }
        }
        // If there are no users, return anonymous
        return Optional.of("ANONYMOUS");
        //todo test
       // return Optional.of("1");
    }
}
