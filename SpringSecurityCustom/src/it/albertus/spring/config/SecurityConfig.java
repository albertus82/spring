package it.albertus.spring.config;

import it.albertus.spring.service.UtenteService;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Among other things, the @EnableWebSecurity annotation configures a Spring MVC
 * argument resolver so that handler methods can receive the authenticated
 * user's principal (or username) via @AuthenticationPrincipal-annotated
 * parameters. It also configures a bean that automatically adds a hidden
 * cross-site request forgery (CSRF) token field on forms using Spring's
 * form-binding tag library.
 * 
 * Although it's not strictly required, you'll probably want to specify the
 * finer points of web security by overriding one or more of the methods from
 * WebSecurityConfigurerAdapter. You can configure web security by overriding
 * WebSecurityConfigurerAdapter's three configure() methods and setting behavior
 * on the parameter passed in.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * Per permettere a Spring Security di accedere al database e recuperare le
	 * credenziali.
	 */
	@Autowired
	private DataSource dataSource;
	
	/**
	 * Per caricare tutti i dati dell'utente, non solo quelli previsti da Spring
	 * Security.
	 */
	@Autowired
	private UtenteService utenteService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Caricamento personalizzato dei dati dell'utente...
		auth.userDetailsService(utenteService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated();
		
		// Configurazione della pagina di login personalizzata...
		http.formLogin().loginPage("/customlogin").permitAll();
		
		http.httpBasic();
	}

}