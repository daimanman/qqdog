package com.man.qqdog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport  {

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/html/**").addResourceLocations("classpath:/html/");
		super.addResourceHandlers(registry);
	}

	
}
