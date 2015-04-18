package it.albertus.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
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
	
	private static final String QUERY_AUTENTICAZIONE = "SELECT username, password, 1 FROM utenti WHERE username = ?";
	private static final String QUERY_RUOLI = "SELECT username, 'ROLE_USER' FROM utenti WHERE username = ?";
	private static final String QUERY_GRUPPI = "SELECT NULL, NULL, NULL FROM utenti WHERE username = ?";

	@Autowired
	DataSource dataSource; // Per accedere al database e recuperare le credenziali.
	
	/**
	 * Query di default di Spring Security (da sovrascrivere se necessario):
	 * 
	 * public static final String DEF_USERS_BY_USERNAME_QUERY =
	 * "select username,password,enabled from users where username = ?";
	 * 
	 * public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY =
	 * "select username,authority from authorities where username = ?";
	 * 
	 * public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY =
	 * "select g.id, g.group_name, ga.authority from groups g, group_members gm, group_authorities ga where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id";
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> securityConfigurer = auth.jdbcAuthentication();
		securityConfigurer.dataSource(dataSource);
		securityConfigurer.usersByUsernameQuery(QUERY_AUTENTICAZIONE);
		securityConfigurer.authoritiesByUsernameQuery(QUERY_RUOLI);
		securityConfigurer.groupAuthoritiesByUsername(QUERY_GRUPPI);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin().loginPage("/customlogin").permitAll();
		http.httpBasic();
	}

}