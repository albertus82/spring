package it.albertus.spring.ioc;

import it.albertus.spring.ioc.geometra.Geometra;
import it.albertus.util.ThreadUtils;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = { "it.albertus" }, excludeFilters = { @Filter(type = FilterType.REGEX, pattern = { "it.albertus.spring.ioc.soffitta.*" }) })
public class TestIoc {

	public static void main(String... args) throws SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestIoc.class);

		Geometra geometra = (Geometra) context.getBean("geometra");

		int metriQuadrati;
		try {
			metriQuadrati = Integer.parseInt(args[0]);
		}
		catch (Exception e) {
			metriQuadrati = 100; // Default.
		}

		System.out.println("Calcolo parcella in corso per " + metriQuadrati + " mq usando la calcolatrice >> " + geometra.getCalcolatrice() + " <<...");
		long inizio = System.currentTimeMillis();
		System.out.println(String.format(">>> %.2f EUR <<<", geometra.calcolaParcella(metriQuadrati)));
		long fine = System.currentTimeMillis();
		System.out.println("Calcolo parcella concluso.");

		ThreadUtils.sleep(1500);
		System.out.println("Tempo impiegato per il calcolo: " + (fine - inizio) + " ms.");
		context.close();
	}

}
