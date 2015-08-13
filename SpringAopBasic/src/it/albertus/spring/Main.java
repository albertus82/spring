package it.albertus.spring;

import it.albertus.spring.concerto.Orchestra;
import it.albertus.spring.concerto.Pianista;
import it.albertus.spring.concerto.StonaturaException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "it.albertus.spring")
@EnableAspectJAutoProxy(proxyTargetClass = true) // Forzo CGLIB!
public class Main {

	public static final void main(String args[]) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

		// Orchestra
		Orchestra chicagoSymphonyOrchestra = context.getBean(Orchestra.class);
		try {
			chicagoSymphonyOrchestra.esegui("Quinta di Beethoven");
		}
		catch (StonaturaException se) {}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		// Pianista
		Pianista maurizioPollini = context.getBean(Pianista.class);
		maurizioPollini.esegui("Sonata 'Chiaro di luna' di Beethoven");
		context.close();
	}

}
