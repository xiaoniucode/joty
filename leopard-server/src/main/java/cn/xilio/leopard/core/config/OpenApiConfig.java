package cn.xilio.leopard.core.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("Leopard-Admin")
                .packagesToScan("cn.xilio.leopard.adapter.admin.controller")
                .build();
    }

    @Bean
    public GroupedOpenApi portalApi() {
        return GroupedOpenApi.builder()
                .group("Leopard-Portal")
                .packagesToScan("cn.xilio.leopard.adapter.portal.controller")
                .build();
    }
    @Bean
    public GroupedOpenApi openApiGroup() {
        return GroupedOpenApi.builder()
                .group("Leopard-openApi")
                .packagesToScan("cn.xilio.leopard.adapter.open.controller")
                .build();
    }
    @Bean
    public OpenApiCustomizer customOpenApiCustomizer() {
        return openApi -> openApi.info(
                new Info()
                        .title("API 文档")
                        .description("API 文档描述")
                        .version("1.0.0")
                        .contact(new Contact().name("API Team").email("xilio1024@gmail.com"))
        );
    }
}
