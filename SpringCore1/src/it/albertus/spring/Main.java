package it.albertus.spring;

import it.albertus.spring.model.Cavaliere;
import it.albertus.spring.model.Missione;
import it.albertus.spring.model.Salvataggio;
import it.albertus.spring.model.UccidiIlDragone;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="it.albertus.spring")
public class Main {
	
	public static void main(String args[]) {
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		Cavaliere artu = (Cavaliere)context.getBean("cavaliere");
		System.out.println(artu.toString());
		artu.imbarcati();
		Missione idontknow = (Missione)context.getBean("uccidiIldragone");
		System.out.println("Bean dragone preso separatamente: " + idontknow.toString());
		System.out.println(idontknow == artu.getMissione());
	}
	
	@Bean
	public Cavaliere cavaliere() {
		Cavaliere c = new Cavaliere();
		c.setMissione(uccidiIldragone());
		return c;
	}
	
	@Bean 
	public Missione salvataggio() {
		return new Salvataggio();
	}
	
	@Bean
	public Missione uccidiIldragone() {
		return new UccidiIlDragone();
	}
	
}
