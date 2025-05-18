package cn.xilio.leopard.core.common.util;


import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 工具类：获取客户端真实 IP 地址
 */
public class IpUtils {

    /**
     * 获取客户端真实 IP 地址
     *
     * @return 客户端真实 IP 地址
     */
    public static String getClientIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 定义可能包含真实 IP 的请求头字段，按优先级排序
        String[] ipHeaders = {
                "X-Forwarded-For",
                "X-Real-IP",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"
        };

        String ip = null;

        // 遍历请求头，查找真实 IP
        for (String header : ipHeaders) {
            String headerValue = request.getHeader(header);
            if (isValidIp(headerValue)) {
                ip = extractIp(headerValue);
                break;
            }
        }

        // 如果请求头中没有有效 IP，则回退到 getRemoteAddr
        if (!isValidIp(ip)) {
            ip = request.getRemoteAddr();
        }

        // 处理 IPv6 本地地址（如 0:0:0:0:0:0:0:1）转换为 127.0.0.1
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }

        return ip;
    }

    /**
     * 验证 IP 地址是否有效
     *
     * @param ip IP 地址字符串
     * @return 是否有效
     */
    private static boolean isValidIp(String ip) {
        return StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip);
    }

    /**
     * 从 X-Forwarded-For 等多 IP 列表中提取第一个有效 IP
     *
     * @param ipHeader 请求头中的 IP 字符串
     * @return 提取的第一个有效 IP
     */
    private static String extractIp(String ipHeader) {
        // X-Forwarded-For 可能包含多个 IP，格式如：client_ip, proxy1, proxy2
        if (ipHeader.contains(",")) {
            String[] ips = ipHeader.split(",");
            for (String ip : ips) {
                ip = ip.trim();
                if (isValidIp(ip)) {
                    return ip;
                }
            }
        }
        return ipHeader.trim();
    }

    public static boolean isLocal(String ip) {
        return ("localhost".equalsIgnoreCase(ip) || "127.0.0.1".equals(ip));
    }
}
