package cn.xilio.joty.core.config.jpa;


import cn.xilio.joty.core.security.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        if (SecurityUtils.isLogin()) {
            String uid = SecurityUtils.getLoginIdAsString();
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
