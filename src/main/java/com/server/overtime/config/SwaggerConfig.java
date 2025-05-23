package com.server.overtime.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi(){
        return new OpenAPI()
                .info(swaggerInfo());
    }

    private Info swaggerInfo() {
        return new Info()
                .version("v0.0.1")
                .title("overtime API 문서")
                .description("overtime API 문서입니다.");
    }

}
