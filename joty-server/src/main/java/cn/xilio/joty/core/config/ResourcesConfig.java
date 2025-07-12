package cn.xilio.joty.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@RequiredArgsConstructor
public class ResourcesConfig implements WebMvcConfigurer {
    @Value("${joty.file.local-path}")
    private String localPath;
    @Value("${joty.file.public-path}")
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
