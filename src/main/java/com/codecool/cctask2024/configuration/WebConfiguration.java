package com.codecool.cctask2024.configuration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Profile("!test")
public class WebConfiguration implements WebMvcConfigurer {

    IPThrottlingService ipThrottlingService;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(getAllClientDomains())
                .allowedMethods("POST", "GET", "PUT", "PATCH", "HEAD", "OPTIONS");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipThrottlingService);
    }

    private String[] getAllClientDomains() {
        Set<String> allowedOrigins = new HashSet<>(Set.of(
                "http://localhost:8080",
                "http://localhost:3000",
                "http://localhost:6006"
        ));
        return allowedOrigins.toArray(String[]::new);
    }
}