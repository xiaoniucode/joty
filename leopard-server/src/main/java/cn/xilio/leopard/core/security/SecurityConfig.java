package cn.xilio.leopard.core.security;


import cn.xilio.leopard.core.security.handler.CustomAccessDeniedHandler;
import cn.xilio.leopard.core.security.handler.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//开启权限
@EnableWebSecurity
// 启用方法级权限控制
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Value("${leopard.file.publicPath}")
    private String publicPath;

    private final String[] excludePaths = {
            "/api/user/login",
            "/{code:^[a-zA-Z0-9]{6}$}",
            "/favicon.ico",
            "/doc.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            publicPath + "/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF（前后端分离不需要）
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 无状态会话
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(excludePaths).permitAll()
                        .requestMatchers("/api/user/logout","/api/user/get").authenticated()
                        .requestMatchers("/api/**").hasAnyRole("USER")
                       .requestMatchers("/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated() // 其他请求需要认证
                ).exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthenticationEntryPoint) // 未登录处理
                        .accessDeniedHandler(customAccessDeniedHandler) // 无权限处理
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // 添加 JWT 过滤器

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
