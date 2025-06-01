package com.example.springbootstarter.Controllers.Configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenAPIConfiguration implements WebMvcConfigurer {
    @Bean
    public OpenAPI OpenAPIConfig() {
        return new OpenAPI()
                .info(new Info()
                        .version("1.0")
                        .title("Spring Boot User API")
                        .description("A Spring Boot REST API to get familiar with Spring Boot"));
    }
}