package cn.xilio.leopard.core.common.util;

import org.apache.commons.lang3.StringUtils;

public class BrowserUtils {
    /**
     * 获取浏览器类型（增强版支持更多浏览器）
     * @param userAgent 浏览器User-Agent字符串
     * @return 浏览器类型标识
     */
    public static String detectBrowser(String userAgent) {
        if (StringUtils.isBlank(userAgent)) {
            return "Unknown";
        }

        String ua = userAgent.toLowerCase();

        // 社交软件检测（新增抖音/头条）
        if (ua.contains("micromessenger")) {
            return "WeChat";
        } else if (ua.contains("qq/")) {
            return "QQ";
        } else if (ua.contains("dingtalk")) {
            return "DingTalk";
        } else if (ua.contains("weibo")) {
            return "Weibo";
        } else if (ua.contains("aweme")) {
            return "Douyin";
        } else if (ua.contains("toutiao")) {
            return "Toutiao";
        }

        // 主流浏览器检测（优化匹配顺序）
        if ((ua.contains("edge") || ua.contains("edg/")) && !ua.contains("edgechromium")) {
            return "Edge";
        } else if (ua.contains("opr") || ua.contains("opera")) {
            return "Opera";
        } else if (ua.contains("chrome") && !ua.contains("chromium")) {
            return "Chrome";
        } else if (ua.contains("firefox") || ua.contains("fxios")) {
            return "Firefox";
        } else if (ua.contains("safari") && !ua.contains("chrome")) {
            return "Safari";
        } else if (ua.contains("trident") || ua.contains("msie")) {
            return "IE";
        } else if (ua.contains("vivaldi")) {
            return "Vivaldi";
        }

        // 移动端浏览器（新增夸克/360）
        if (ua.contains("android")) {
            if (ua.contains("linux; u;")) {
                return "UCBrowser";
            } else if (ua.contains("baidu")) {
                return "BaiduApp";
            } else if (ua.contains("quark")) {
                return "Quark";
            } else if (ua.contains("qihu") || ua.contains("360")) {
                return "360Browser";
            }
        }

        // 特殊设备浏览器（新增智能电视）
        if (ua.contains("smart-tv") || ua.contains("tv")) {
            return "SmartTV";
        }

        return "Unknown";
    }
}
