package it.albertus.spring;

import it.albertus.spring.concerto.Orchestra;
import it.albertus.spring.concerto.Pianista;
import it.albertus.spring.concerto.StonaturaException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "it.albertus.spring")
@EnableAspectJAutoProxy(proxyTargetClass = true) // Forzo CGLIB!
public class Main {

	public static final void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

		// Ochestra
		Orchestra chicagoSymphonyOrchestra = context.getBean("orchestra", Orchestra.class);
		try {
			chicagoSymphonyOrchestra.esegui("Quinta di Beethoven");
		}
		catch (StonaturaException se) {}
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		// Pianista
		Pianista maurizioPollini = context.getBean("pianista", Pianista.class);
		maurizioPollini.esegui("Sonata 'Hammerklavier' di Beethoven");
	}

}