package it.albertus.spring.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Questa classe viene caricata all'avvio dell'applicazione. Configura la
 * DispatcherServlet e crea due Application Context di Spring: uno per i bean
 * legati alla parte web, e un altro per tutti gli altri bean (Service, DAO,
 * ecc.).
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		servletContext.addFilter("HttpMethodFilter", HiddenHttpMethodFilter.class).addMappingForUrlPatterns(null, false, "/*");
//	}

}
