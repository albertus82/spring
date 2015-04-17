package it.albertus.spring;

import it.albertus.spring.model.Cavaliere;
import it.albertus.spring.model.Missione;
import it.albertus.spring.model.Salvataggio;
import it.albertus.spring.model.UccidiIlDragone;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Component (e @Service e @Repository) sono usate per rilevare e configurare
 *            automaticamente i bean utilizzando la scansione del classpath.
 *            Esiste un mapping implicito uno a uno tra la classe annotata e il
 *            bean (cioe' esiste un bean per classe). Il controllo del wiring
 *            con questo approccio e' abbastanza limitato, in quanto e'
 *            puramente dichiarativo.
 *
 * @Bean viene utilizzata per dichiarare esplicitamente un singolo bean,
 *       piuttosto che lasciare che Spring lo faccia automaticamente come sopra.
 *       Cio' disaccoppia la dichiarazione del bean dalla definizione della
 *       classe, e permette di creare e configurare il bean a propria discrezione.
 */
@Configuration
@ComponentScan(basePackages = "it.albertus.spring")
public class Main {

	@SuppressWarnings("resource")
	public static void main(String args[]) {
		// ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		Cavaliere artu = (Cavaliere) context.getBean("cavaliere");
		System.out.println(artu.toString());
		artu.imbarcati();
		Missione idontknow = (Missione) context.getBean("uccidiIldragone");
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
	@Scope(BeanDefinition.SCOPE_SINGLETON) // "singleton" e' lo scope di default!
	public Missione uccidiIldragone() {
		return new UccidiIlDragone();
	}

}