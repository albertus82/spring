package it.test.esempio;

import it.test.esempio.geometra.Geometra;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("it.test.esempio.geometra")
public class Main {

	public static void main(String... args) throws SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		Geometra g = (Geometra) context.getBean("geometra");
		System.out.println( g.calcolaParcella(120) );
		DataSource ds = (DataSource) context.getBean("dataSource");
		System.out.println(ds.toString());
		ds.getConnection();
	}
	
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
}