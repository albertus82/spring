package it.albertus.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = { "it.albertus.spring" }, 
	excludeFilters = { 
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) 
	})
public class RootConfig {
	
}