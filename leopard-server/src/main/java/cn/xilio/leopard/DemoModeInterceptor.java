package cn.xilio.leopard;

import cn.xilio.leopard.core.common.exception.BizException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class DemoModeInterceptor implements HandlerInterceptor {
    @Value("${leopard.demo-model}")
    private boolean isDemoMode;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (isDemoMode && isWriteOperation(request)) {
            throw new BizException("2004");
        }
        return true;
    }

    private boolean isWriteOperation(HttpServletRequest request) {
        return !"GET".equalsIgnoreCase(request.getMethod());
    }
}
