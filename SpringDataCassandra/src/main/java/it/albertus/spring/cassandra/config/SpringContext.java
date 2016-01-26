package it.albertus.spring.cassandra.config;

import it.albertus.spring.cassandra.dao.TestSpringDataCassandraDao;
import it.albertus.spring.cassandra.model.TestSpringDataCassandra;

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

	public static void main(String... args) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);
		log.info("Inizio esecuzione.");

		Session session = (Session) context.getBean("cassandraSession");
		session.execute("CREATE KEYSPACE IF NOT EXISTS alb_space WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }");
		session.execute("CREATE TABLE IF NOT EXISTS d_test_spring_data_cassandra (sequ_test int primary key, text_stringa text)");

		/* INSERT... */
		TestSpringDataCassandra test = new TestSpringDataCassandra();
		test.setTesto("Stringa di prova");
		test.setId(12345);
		TestSpringDataCassandraDao repo = context.getBean(TestSpringDataCassandraDao.class);
		repo.save(test);

		/* SELECT... */
		Iterable<TestSpringDataCassandra> righe = repo.findAll();
		System.out.println("Elenco degli oggetti:");
		for (TestSpringDataCassandra riga : righe) {
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
