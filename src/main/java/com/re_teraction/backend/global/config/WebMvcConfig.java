package com.re_teraction.backend.global.config;

import com.re_teraction.backend.global.security.resolver.AuthenticatedUserIdResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private static final List<String> ALLOWED_ORIGINS = List.of(
            "http://localhost:3000",
            "https://localhost:8080"
    );
    private final AuthenticatedUserIdResolver authenticatedUserIdResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticatedUserIdResolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowedOrigins(ALLOWED_ORIGINS.toArray(new String[0]))
                .allowCredentials(true)
                .maxAge(3600);
    }
}
