package com.man.qqdog.config;

import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.undertow.Undertow.Builder;

@Configuration
public class SslConfig {
    @Bean
    public ServletWebServerFactory servletContainer() {

    	UndertowServletWebServerFactory undertowFactory = new UndertowServletWebServerFactory();
        undertowFactory.addBuilderCustomizers(new UndertowBuilderCustomizer() {
            @Override
            public void customize(Builder builder) {
                builder.addHttpListener(54321, "0.0.0.0");
            }
        });
        return undertowFactory;
    }
}
