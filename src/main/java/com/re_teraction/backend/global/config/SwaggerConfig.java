package com.re_teraction.backend.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "BearerAuth";
    private final List<Server> servers = List.of(
            new Server().url("http://localhost:8080/be").description("로컬 개발 서버"),
            new Server().url("http://3.35.58.14/be").description("배포 서버")
    );

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(servers)
                .info(apiInfo())
                .components(securityComponents())
                .addSecurityItem(globalSecurityRequirement());
    }

    private Info apiInfo() {
        return new Info()
                .title("Re-Teraction Backend API")
                .version("v1.0.0")
                .description("Re-Teraction 백엔드 API 문서");
    }

    private Components securityComponents() {
        return new Components()
                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));
    }

    private SecurityRequirement globalSecurityRequirement() {
        return new SecurityRequirement().addList(SECURITY_SCHEME_NAME);
    }
}
