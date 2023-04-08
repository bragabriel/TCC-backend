package br.com.spotted.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableWebMvc
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.securityContexts(Arrays.asList(securityContext()))
                //.securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("br.com.spotted.backend.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API - Spotted",
                "API para o aplicativo Spotted",
                "1.0.0",
                "Disponível para o uso do aplicativo Spotted",
                new Contact("Gabriel Braga & Hérica Cadoni", "https://github.com/bragabriel/TCC-SI", "asd@asd.com"),
                "", "", Collections.emptyList());
    }

//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }

//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
//    }

//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//    }
}
