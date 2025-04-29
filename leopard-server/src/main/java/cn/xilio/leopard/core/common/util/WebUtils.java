package cn.xilio.leopard.core.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.util.Optional;

public abstract class WebUtils {

    /**
     * 获取域名或IP:PORT
     *
     * @return 域名或IP:PORT
     */
    public static String getDomain( ) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 1. 优先从反向代理头获取（生产环境）
        Optional<String> forwardedDomain = getForwardedDomain(request);
        if (forwardedDomain.isPresent()) {
            return forwardedDomain.get();
        }

        // 2. 从Host头或请求URI获取（直接访问场景）
        String host = Optional.ofNullable(request.getHeader("Host"))
                .orElseGet(() -> request.getServerName() + (request.getServerPort() != 80 && request.getServerPort() != 443 ? ":" + request.getServerPort() : ""));

        String scheme = Optional.ofNullable(request.getHeader("X-Forwarded-Proto"))
                .orElse(request.getScheme());
        return (scheme != null ? scheme : "http") + "://" + host;
    }

    private static Optional<String> getForwardedDomain(HttpServletRequest request) {
        // 处理标准反向代理头：X-Forwarded-Host + X-Forwarded-Proto
        String forwardedHost = request.getHeader("X-Forwarded-Host");
        if (forwardedHost != null) {
            String proto = Optional.ofNullable(request.getHeader("X-Forwarded-Proto"))
                    .orElse("https");
            return Optional.of(proto + "://" + forwardedHost.split(",")[0].trim());
        }
        return Optional.empty();
    }

    private static String getDevLocalDomain(HttpServletRequest request) {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = request.getServerPort();
            return "http://" + ip + (port != 80 && port != 443 ? ":" + port : "");
        } catch (Exception e) {
            return "http://localhost" + (request.getServerPort() != 80 && request.getServerPort() != 443 ? ":" + request.getServerPort() : "");
        }
    }
}
