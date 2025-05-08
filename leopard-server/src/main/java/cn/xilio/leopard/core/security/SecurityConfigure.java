package cn.xilio.leopard.core.security;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;

import cn.xilio.leopard.core.common.util.LocaleUtils;
import cn.xilio.leopard.core.common.util.Result;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfigure implements WebMvcConfigurer {
    @Value("${leopard.file.publicPath}")
    private String publicPath;

    /**
     * 注册 Sa-Token 拦截器打开注解鉴权功能
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns("/api/user/login");
    }

    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude(
                        "/api/user/login",
                        "/{code:^[a-zA-Z0-9]{6}$}",
                        "/favicon.ico",
                        "/doc.html",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/api-docs/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        publicPath + "/**"

                )
                .setAuth(obj -> {
                    System.out.println("---------- 进入Sa-Token全局认证 -----------");
                    String[] excludePaths = {"/api/user/get", "/api/user/logout"};
                    SaRouter.notMatch(excludePaths).match("/api/user/get", r -> StpUtil.checkRole("admin"));
                    SaRouter.notMatch(excludePaths).match("/api/**", r -> StpUtil.checkRole("user"));

                })
                .setError(e -> {
                    e.printStackTrace();
                    SaHolder.getResponse().setHeader("content-type", "application/json; charset=utf-8");
                    Result res = Result.error("401", LocaleUtils.getLocaleMessage("401"));
                    return new Gson().toJson(res);
                })

                // 前置函数：在每次认证函数之前执行（BeforeAuth 不受 includeList 与 excludeList 的限制，所有请求都会进入）
                .setBeforeAuth(r -> {

                    // ---------- 设置一些安全响应头 ----------
                    SaHolder.getResponse()
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", "*")
                            // 允许所有请求方式
                            .setHeader("Access-Control-Allow-Methods", "*")
                            // 允许的header参数
                            .setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type,accept,Origin,User-Agent,DNT,Cache-Control,Content-Type,Range,referer,connection,content-length,accept-language")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                            // 服务器名称
                            .setServer("sa-server")
                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                            .setHeader("X-Frame-Options", "SAMEORIGIN")
                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                            .setHeader("X-XSS-Protection", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff")
                    ;
                });
    }

}
