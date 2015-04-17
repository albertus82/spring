package it.albertus.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Questa classe viene caricata all'avvio dell'applicazione. Configura la DispatcherServlet
 * e crea due Application Context di Spring: uno per i bean legati alla parte web, e un
 * altro per tutti gli altri bean (Service, DAO, ecc.).
 */
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" }; // Mappa la DispatcherServlet col path "/"
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class }; // Application Context *Middle & Data Tier*
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class }; // Application Context *Web*
	}

}