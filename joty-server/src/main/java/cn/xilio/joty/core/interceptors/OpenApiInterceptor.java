package cn.xilio.joty.core.interceptors;

import cn.xilio.joty.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class OpenApiInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    private static final String API_KEY_HEADER = "joty-Api-Key";
    private static final long MAX_REQUEST_TIME_DIFF = 5 * 60 * 1000; // 5分钟时间差
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 验证API Key
        String apiKey = request.getHeader(API_KEY_HEADER);
        if (!StringUtils.hasText(apiKey)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing API Key");
            return false;
        }
        // 2. 验证用户有效性
        if (!userService.isValidApiKey(apiKey)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API Key");
            return false;
        }
        return true;
    }
}
