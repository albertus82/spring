package it.albertus.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
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
@ComponentScan(basePackages = { "it.albertus.spring" }, excludeFilters = {
	@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
	@Filter(type = FilterType.ANNOTATION, value = Controller.class)
})
/* Abilita l'AOP per la gestione delle transazioni. I parametri aggiuntivi sono facoltativi. */
@EnableTransactionManagement(proxyTargetClass = false, mode = AdviceMode.PROXY)
public class RootConfig {

	/** Gestisce la connessione al database. */
	@Bean(initMethod = "init", destroyMethod = "close")
	protected DataSource dataSource() {
		PoolingDataSource ds = new PoolingDataSource();
		ds.setClassName("oracle.jdbc.xa.client.OracleXADataSource");
		ds.setUniqueName("bitronixTxManager");
		ds.setMaxPoolSize(3);
		Properties driverProperties = new Properties();
		driverProperties.setProperty("user", "alb_admin");
		driverProperties.setProperty("password", "alb_admin");
		driverProperties.setProperty("URL", "jdbc:oracle:thin:@localhost:1521:XE");
		ds.setDriverProperties(driverProperties);
		return ds;
	}

	/** Permette di effettuare facilmente operazioni sul database usando JDBC. */
	@Bean
	protected NamedParameterJdbcOperations jdbcOperations(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * Gestisce la transazionalita' dei metodi che accedono al database. In
	 * presenza di piu' transaction manager, bisogna specificare in
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

	@Bean(destroyMethod = "shutdown")
	protected BitronixTransactionManager bitronixTransactionManager() {
		return TransactionManagerServices.getTransactionManager();
	}

}
