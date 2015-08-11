package it.albertus.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = { "it.albertus.spring" },
	excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
		@Filter(type = FilterType.ANNOTATION, value = Controller.class)
	})
public class RootConfig {

	@Bean
	protected PropertyPlaceholderConfigurer properties() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("database.properties"));
		return ppc;
	}

	@Bean
	protected DataSource dataSource(@Value("${db.class}") String clazz, @Value("${db.url}") String url, @Value("${db.username}") String username, @Value("${db.password}") String password) {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(clazz);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}

}
