package cn.xilio.leopard.core.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    private String tokenName = "Authorization";  // HTTP头部/Cookie名称
    private String tokenPrefix;                 // Token前缀 如Bearer
    private long timeout = 2592000;               // Token有效期（秒），默认30天
    private long activeTimeout = -1;               // Token活跃期（秒），-1表示不限制
    private boolean Concurrent = true;          // 是否允许多地登录
    private Boolean isShare = false;
    /**
     * 忽略的URL 不认证和授权
     */
    private String[] ignoreUrls = {

    };
}
