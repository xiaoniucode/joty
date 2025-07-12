package cn.xilio.joty.core.common.util;

public class NetworkTypeDetector {

    /**
     * 通过User-Agent判断网络类型
     * @param userAgent 浏览器User-Agent字符串
     * @return wifi/4g/5g/unknown
     */
    public static String detectNetworkType(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            return "unknown";
        }

        String lowerAgent = userAgent.toLowerCase();

        // 1. 优先检测WiFi特征
        if (lowerAgent.contains("wifi") ||
            lowerAgent.contains("wireless") ||
            lowerAgent.matches(".*(win|mac|linux).*")) {
            return "wifi";
        }

        // 2. 检测5G特征
        if (lowerAgent.contains("5g") ||
            lowerAgent.contains("nr_")) {
            return "5g";
        }

        // 3. 移动网络特征
        if (lowerAgent.contains("mobile") ||
            lowerAgent.contains("android") ||
            lowerAgent.contains("iphone")) {
            return "4g";
        }

        return "unknown";
    }
}
