package it.albertus.spring.cassandra.config;

import it.albertus.spring.cassandra.EsempioPersona;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = "it.albertus.spring.cassandra")
@ImportResource("classpath:config-cassandra.xml")
@PropertySource("classpath:cassandra.properties")
public class SpringContext {

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	private static final Logger log = LoggerFactory.getLogger(SpringContext.class);

	public static void main(String... args) throws ParseException {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);
		log.info("Inizio esecuzione.");

		context.getBean(EsempioPersona.class).run();

		try {
			Thread.sleep(500);
		}
		catch (InterruptedException e) {
		}

		log.info("Esecuzione terminata.");
		context.close();
	}

}
