package cn.xilio.leopard.core.common.util;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 设备类型检测工具类（纯User-Agent解析）
 */
public class DeviceDetector {

    /**
     * 设备类型枚举
     */
    @Getter
    public enum DeviceType {
        COMPUTER("电脑"),
        MOBILE("手机"),
        TABLET("平板"),
        SMART_TV("智能电视"),
        WECHAT("微信内置浏览器"),
        QQ("QQ内置浏览器"),
        UNKNOWN("未知设备");

        private final String name;
        DeviceType(String name) {
            this.name = name;
        }
    }

    /**
     * 通过User-Agent字符串检测设备类型
     * @param userAgent 浏览器User-Agent字符串
     */
    public static DeviceType detectDevice(String userAgent) {
        if (StringUtils.isBlank(userAgent)) {
            return DeviceType.UNKNOWN;
        }

        String ua = userAgent.toLowerCase();

        // 优先检测社交软件
        if (ua.contains("micromessenger")) {
            return DeviceType.WECHAT;
        } else if (ua.contains("qq/")) {
            return DeviceType.QQ;
        }

        // 移动设备检测
        if (ua.contains("mobile") || ua.contains("android")) {
            return ua.contains("tablet") ? DeviceType.TABLET : DeviceType.MOBILE;
        } else if (ua.contains("ipad")) {
            return DeviceType.TABLET;
        } else if (ua.contains("iphone")) {
            return DeviceType.MOBILE;
        }

        // 智能电视检测
        if (ua.contains("smart-tv") || ua.contains("tv")) {
            return DeviceType.SMART_TV;
        }

        // 默认返回电脑类型
        return DeviceType.COMPUTER;
    }

    /**
     * 判断是否为移动设备（手机/平板）
     */
    public static boolean isMobileDevice(String userAgent) {
        DeviceType type = detectDevice(userAgent);
        return type == DeviceType.MOBILE || type == DeviceType.TABLET;
    }
}
