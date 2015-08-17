package it.albertus.spring.ioc;

import it.albertus.spring.ioc.model.Geometra;
import it.albertus.util.ThreadUtils;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@ComponentScan(basePackages = "it.albertus")
public class IocExample {

	public static void main(String... args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-ctx.xml");

		Geometra geometra = context.getBean(Geometra.class);

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
