package cn.xilio.leopard;

import cn.xilio.leopard.core.security.SecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 @Component
 @AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final DemoModeInterceptor demoModeInterceptor;
    private final SecurityProperties securityProperties;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoModeInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(securityProperties.getIgnoreUrls());
    }
}

