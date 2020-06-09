package com.efisher.flatrent.config;

import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, WebMvcConfigurer {

    public WebMvcConfig() {
        super();
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setContextPath("/api/flat-rent-app");
    }
}
