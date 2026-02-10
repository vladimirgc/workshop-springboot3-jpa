package com.educandoweb.course.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // "/uploads/**" acessa arquivos na pasta "uploads" do projeto
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
