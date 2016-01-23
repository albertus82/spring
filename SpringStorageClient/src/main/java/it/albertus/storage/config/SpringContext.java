package it.albertus.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ImportResource(locations = "classpath:applicationContext.xml")
@ComponentScan(basePackages = "it.albertus.storage")
@PropertySource(value = { "classpath:configuration.properties", "${storage.properties}" })
public class SpringContext {

	/* Abilita la risoluzione dei segnaposti ${...} nelle annotation @Value */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
