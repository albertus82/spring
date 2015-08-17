package it.albertus.spring.ioc;

import it.albertus.spring.ioc.model.Geometra;
import it.albertus.util.ThreadUtils;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EsempioIoc {

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
		ThreadUtils.sleep(500);
		context.close();
	}

}
