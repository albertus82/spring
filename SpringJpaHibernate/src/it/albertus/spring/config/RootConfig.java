package it.albertus.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = { "it.albertus.spring" },
	excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
	})
@EnableTransactionManagement // Abilita l'AOP per la gestione delle transazioni.
public class RootConfig {
	
	/** Gestisce la connessione al database. */
	@Bean
	protected DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("alb_admin");
		ds.setPassword("alb_admin");
		return ds;
	}
	
	/** Permette di effettuare facilmente operazioni sul database usando JDBC. */
	@Bean
	protected NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	/** 
	 * Gestisce la transazionalita' dei metodi che accedono al database.
	 * In presenza di piu' transaction manager, bisogna specificare in
	 * @Transactional quale si vuole usare, pena "NoUniqueBeanDefinitionException".
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // E' il valore di default.
	protected HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
	
	/** Integrazione con Hibernate */
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		sfb.setDataSource(dataSource);
		sfb.setPackagesToScan(new String[] { "it.albertus.spring.model" });
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		props.setProperty("hibernate.show_sql", Boolean.TRUE.toString());
		sfb.setHibernateProperties(props);
		return sfb;
	}
	
}