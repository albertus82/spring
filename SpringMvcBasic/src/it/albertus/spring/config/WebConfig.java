package it.albertus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("it.albertus.spring.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * Configurazione del resolver per le JSP: it's configured to look for JSP
	 * files by wrapping view names with a specific prefix and suffix (for
	 * example, a view name of home will be resolved as /WEB-INF/views/home.jsp).
	 */
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	/**
	 * Configurazione gestione dei contenuti statici: by calling enable() on the
	 * given DefaultServletHandlerConfigurer, you're asking DispatcherServlet to
	 * forward requests for static resources to the servlet container's default
	 * servlet and not to try to handle them itself.
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
