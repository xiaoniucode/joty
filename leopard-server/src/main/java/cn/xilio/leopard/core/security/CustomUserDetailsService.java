package cn.xilio.leopard.core.security;

import cn.xilio.leopard.core.config.CacheManager;
import cn.xilio.leopard.domain.CacheKey;
import cn.xilio.leopard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Locale;
import java.util.function.Function;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private CacheManager cacheManager;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        cn.xilio.leopard.domain.dataobject.User user = userService.getUserByUserId(id);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        String role = user.getRole();
        return User.withUsername(user.getId())
                .password(user.getPassword())
                .roles(role.toUpperCase(Locale.ROOT))
                .build();

    }
}
