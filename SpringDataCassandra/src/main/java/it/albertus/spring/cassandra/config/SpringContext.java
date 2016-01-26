package it.albertus.spring.cassandra.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import it.albertus.spring.cassandra.dao.PersonaDao;
import it.albertus.spring.cassandra.model.Persona;

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

import com.datastax.driver.core.Session;

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

		Session session = (Session) context.getBean("cassandraSession");
		session.execute("CREATE KEYSPACE IF NOT EXISTS alb_space WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }");

		session.execute("CREATE TABLE IF NOT EXISTS d_persone (codi_persona uuid primary key, " +
		                                                      "text_cognome text, " +
		                                                      "text_nome text, " +
		                                                      "data_nascita timestamp, " +
		                                                      "nume_altezza int, " +
		                                                      "nume_peso int)");

		/* INSERT... */
		Persona persona = new Persona();
		persona.setCognome("Rossi");
		persona.setNome("Mario");
		persona.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse("01/07/1950"));
		persona.setAltezza(170);
		persona.setPeso(70);
		persona.setId(UUID.randomUUID());
		PersonaDao dao = context.getBean(PersonaDao.class);
		dao.save(persona);

		/* SELECT... */
		Iterable<Persona> righe = dao.findAll();
		System.out.println("Elenco persone:");
		for (Persona riga : righe) {
			System.out.println(riga.toString());
		}

		try {
			Thread.sleep(500);
		}
		catch (InterruptedException e) {
		}

		log.info("Esecuzione terminata.");
		context.close();
	}

}
