package cn.xilio.leopard.core.spring.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   // private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//        // 从数据库加载角色和权限
//        List<String> authorities = user.getRoles().stream()
//                .flatMap(role -> role.getPermissions().stream())
//                .map(PermissionEntity::getName)
//                .collect(Collectors.toList());
        UserDetails user = User.withUsername("admin")
                .password("123456")
                .authorities("user:list")
                .roles("ADMIN2")
                .build();
 return user;
    }
}
