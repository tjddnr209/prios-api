package com.furyjoker.priosapi.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Prios API")
                        .description("AI 우선순위 정리 기반 투두리스트")
                        .version("v1.0"))
                .addSecurityItem(new SecurityRequirement().addList("member-id")) // 🔐 전역 보안 설정
                .components(new Components()
                        .addSecuritySchemes("member-id",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name("X-MEMBER-ID")
                        ));
    }
}
