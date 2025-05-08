package cn.xilio.leopard.core.security;


import cn.xilio.leopard.core.common.spring.SpringHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.swing.*;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        SecurityProperties properties = SpringHelper.getBean(SecurityProperties.class);
        String token = request.getHeader(properties.getTokenName());
        if (token == null || (StringUtils.hasText(properties.getTokenPrefix()) && !token.startsWith(properties.getTokenPrefix() + " "))) {
            filterChain.doFilter(request, response);
            return;
        }
        if (StringUtils.hasText(properties.getTokenPrefix())) {
            token = token.substring(7);
        }
        try {
            StringRedisTemplate redisTemplate = SpringHelper.getBean(StringRedisTemplate.class);
            String key = properties.getTokenName() + ":login:token:" + token;
            String loginId = redisTemplate.opsForValue().get(key);
            if (StringUtils.hasText(loginId) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
}
