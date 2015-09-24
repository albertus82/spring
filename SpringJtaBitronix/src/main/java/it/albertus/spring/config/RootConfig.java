package it.albertus.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import bitronix.tm.resource.jdbc.PoolingDataSource;

@Configuration
@ComponentScan(basePackages = { "it.albertus.spring" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
		@Filter(type = FilterType.ANNOTATION, value = Controller.class) })
/*
 * Abilita l'AOP per la gestione delle transazioni. I parametri aggiuntivi sono
 * facoltativi.
 */
@EnableTransactionManagement(proxyTargetClass = false, mode = AdviceMode.PROXY)
public class RootConfig {

	/** Caricamento properties da file. */
	@Bean
	protected PropertyPlaceholderConfigurer ppc() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("database.properties"));
		return ppc;
	}

	/** Primo datasource... */
	@Bean(initMethod = "init", destroyMethod = "close")
	protected DataSource albAdminDataSource(@Value("${db1.className}") final String className, 
	                                        @Value("${db1.uniqueName}") final String uniqueName,
	                                        @Value("${db1.maxPoolSize}") final int maxPoolSize,
	                                        @Value("${db1.user}") final String user,
	                                        @Value("${db1.password}") final String password,
	                                        @Value("${db1.url}") final String url) {
		return createBitronixDataSource(className, uniqueName, maxPoolSize, user, password, url);
	}

	/** Secondo datasource... */
	@Bean(initMethod = "init", destroyMethod = "close")
	protected DataSource hrDataSource(@Value("${db2.className}") final String className, 
	                                  @Value("${db2.uniqueName}") final String uniqueName,
	                                  @Value("${db2.maxPoolSize}") final int maxPoolSize,
	                                  @Value("${db2.user}") final String user,
	                                  @Value("${db2.password}") final String password,
	                                  @Value("${db2.url}") final String url) {
		return createBitronixDataSource(className, uniqueName, maxPoolSize, user, password, url);
	}

	/** Permette di effettuare facilmente operazioni sul database usando JDBC. */
	@Bean
	protected NamedParameterJdbcOperations albAdminJdbcOperations(@Qualifier("albAdminDataSource") DataSource albAdminDataSource) {
		return new NamedParameterJdbcTemplate(albAdminDataSource);
	}

	@Bean
	protected NamedParameterJdbcOperations hrJdbcOperations(@Qualifier("hrDataSource") DataSource hrDataSource) {
		return new NamedParameterJdbcTemplate(hrDataSource);
	}

	/**
	 * Gestisce la transazionalit&agrave; dei metodi che accedono al database.
	 * In presenza di pi&ugrave; transaction manager, bisogna specificare in
	 * "@Transactional" quale si vuole usare, pena
	 * "NoUniqueBeanDefinitionException".
	 */
	@Bean
	@Qualifier("txManager")
	protected PlatformTransactionManager transactionManager(BitronixTransactionManager tm) {
		JtaTransactionManager jtm = new JtaTransactionManager();
		jtm.setTransactionManager(tm);
		jtm.setUserTransaction(tm);
		return jtm;
	}

	/** Il transaction manager Bitronix che pu&ograve; gestire molteplici risorse transazionali. */
	@Bean(destroyMethod = "shutdown")
	protected BitronixTransactionManager bitronixTransactionManager() {
		return TransactionManagerServices.getTransactionManager();
	}

	/** Metodo di utilit&agrave; per la creazione di un {@link bitronix.tm.resource.jdbc.PoolingDataSource PoolingDataSource}. */
	private DataSource createBitronixDataSource(final String className, final String uniqueName, final int maxPoolSize, final String user, final String password, final String url) {
		PoolingDataSource ds = new PoolingDataSource();
		ds.setClassName(className);
		ds.setUniqueName(uniqueName);
		ds.setMaxPoolSize(maxPoolSize);
		Properties driverProperties = new Properties();
		driverProperties.setProperty("user", user);
		driverProperties.setProperty("password", password);
		driverProperties.setProperty("URL", url);
		ds.setDriverProperties(driverProperties);
		return ds;
	}

}
