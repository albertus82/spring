package it.albertus.spring;

import it.albertus.spring.concerto.Esecuzione;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "it.albertus.spring")
@EnableAspectJAutoProxy
public class Main {

	public static final void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

		// Si deve utilizzare l'interfaccia, perche' l'implementazione in realta' e' un proxy!
		Esecuzione s = (Esecuzione) context.getBean("sinfonia");
		s.esegui();
	}

}