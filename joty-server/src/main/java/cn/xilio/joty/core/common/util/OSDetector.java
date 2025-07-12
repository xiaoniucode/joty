package cn.xilio.joty.core.common.util;


import org.apache.commons.lang3.StringUtils;

/**
 * 操作系统检测工具类（支持移动端/桌面端/社交软件）
 */
public class OSDetector {

    /**
     * 操作系统类型枚举
     */
    public enum OSType {
        WINDOWS("Windows"),
        MAC_OS("Mac OS"),
        LINUX("Linux"),
        ANDROID("Android"),
        IOS("iOS"),
        WINDOWS_PHONE("Windows Phone"),
        CHROME_OS("Chrome OS"),
        WECHAT("WeChat"),
        QQ("QQ"),
        DINGTALK("DingTalk"),
        WEIBO("Weibo"),
        UNKNOWN("Unknown");

        private final String name;

        OSType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 通过User-Agent检测操作系统
     */
    public static OSType detectFromUserAgent(String userAgent) {
        if (StringUtils.isBlank(userAgent)) {
            return OSType.UNKNOWN;
        }
        String ua = userAgent.toLowerCase();

        // 优先检测社交软件（可能覆盖真实OS）
        if (ua.contains("micromessenger")) {
            return OSType.WECHAT;
        } else if (ua.contains("qq/")) {
            return OSType.QQ;
        } else if (ua.contains("dingtalk")) {
            return OSType.DINGTALK;
        } else if (ua.contains("weibo")) {
            return OSType.WEIBO;
        }

        // 检测移动操作系统
        if (ua.contains("android")) {
            return OSType.ANDROID;
        } else if (ua.contains("iphone") || ua.contains("ipad")) {
            return OSType.IOS;
        } else if (ua.contains("windows phone")) {
            return OSType.WINDOWS_PHONE;
        }

        // 检测桌面操作系统
        if (ua.contains("windows nt")) {
            return OSType.WINDOWS;
        } else if (ua.contains("mac os x") || ua.contains("macintosh")) {
            return OSType.MAC_OS;
        } else if (ua.contains("linux")) {
            return ua.contains("cros") ? OSType.CHROME_OS : OSType.LINUX;
        }

        return OSType.UNKNOWN;
    }

    /**
     * 获取操作系统版本（需配合detectFromUserAgent使用）
     */
    public static String getOSVersion(String userAgent) {
        if (StringUtils.isBlank(userAgent)) return "";

        String ua = userAgent.toLowerCase();

        // Android版本提取（如Android 8.0.0）
        if (ua.contains("android")) {
            int start = ua.indexOf("android");
            int end = ua.indexOf(";", start);
            return end > start ?
                    userAgent.substring(start, end).trim() : "Android";
        }

        // iOS版本提取（如iPhone OS 14_4）
        if (ua.contains("iphone os")) {
            int start = ua.indexOf("iphone os");
            int end = ua.indexOf(" ", start);
            return end > start ?
                    userAgent.substring(start, end).replace("_", ".") : "iOS";
        }

        // Windows版本提取（如Windows NT 10.0）
        if (ua.contains("windows nt")) {
            return "Windows " +
                    userAgent.substring(
                            ua.indexOf("windows nt") + 11,
                            ua.indexOf(";", ua.indexOf("windows nt"))
                    ).trim();
        }

        return "";
    }

    /**
     * 判断是否为移动设备
     */
    public static boolean isMobile(String userAgent) {
        OSType os = detectFromUserAgent(userAgent);
        return os == OSType.ANDROID ||
                os == OSType.IOS ||
                os == OSType.WINDOWS_PHONE;
    }
}
