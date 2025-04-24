package cn.xilio.leopard.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@RequiredArgsConstructor
public class ResourcesConfig implements WebMvcConfigurer {
    @Value("${leopard.file.localPath}")
    private String localPath;
    @Value("${leopard.file.publicPath}")
    private String publicPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Normalize the local path to an absolute path
        Path normalizedPath = Paths.get(localPath).toAbsolutePath().normalize();
        String resourceLocation = "file:" + normalizedPath.toString().replace("\\", "/") + "/";
        registry.addResourceHandler(publicPath + "/**")
                .addResourceLocations(resourceLocation);
    }
}
