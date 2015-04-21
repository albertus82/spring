package it.albertus.spring;

import it.albertus.spring.concerto.Interprete;
import it.albertus.spring.concerto.StonaturaException;

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

		// Ci si deve riferire all'interfaccia, perche' l'implementazione in realta' e' un proxy!
		Interprete s = context.getBean(Interprete.class);
		
		try {
			s.esegui("Quinta di Beethoven");
		}
		catch (StonaturaException se) {}
	}

}