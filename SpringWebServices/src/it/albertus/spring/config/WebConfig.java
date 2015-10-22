package it.albertus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("it.albertus.spring.web")
//@ImportResource({ "classpath:META-INF/cxf/cxf.xml", "classpath:META-INF/cxf/cxf-servlet.xml" })
public class WebConfig extends WebMvcConfigurerAdapter {

	/** Configurazione del resolver per le JSP */
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver resolver = new InternalResourceViewResolver();
		resolver.setExposeContextBeansAsAttributes(true);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/** Configurazione gestione dei contenuti statici */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
