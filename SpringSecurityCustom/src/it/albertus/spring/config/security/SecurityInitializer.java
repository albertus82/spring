package it.albertus.spring.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * AbstractSecurityWebApplicationInitializer implements
 * WebApplicationInitializer, so it will be discovered by Spring and be used to
 * register DelegatingFilterProxy with the web container. Although you can
 * override its appendFilters() or insertFilters() methods to register filters
 * of your own choosing, you need not override anything to register
 * DelegatingFilterProxy.
 * 
 * Whether you configure DelegatingFilterProxy in web.xml or by subclassing
 * AbstractSecurityWebApplicationInitializer, it will intercept requests coming
 * into the application and delegate them to a bean whose ID is
 * springSecurityFilterChain.
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {}