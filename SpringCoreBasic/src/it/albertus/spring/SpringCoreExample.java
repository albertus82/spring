package it.albertus.spring;

import it.albertus.spring.config.AppConfig;
import it.albertus.spring.service.AccountService;
import it.albertus.spring.service.CurrencyService;
import it.albertus.spring.service.ExchangeService;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCoreExample {

	public static void main(String args[]) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "repositories.xml");
//		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println("Context inizializzato.");

		System.out.println(AppConfig.getBeanNames(context));

		System.out.println(context.getBean("acctSvc", AccountService.class));
		System.out.println(context.getBean(CurrencyService.class));
		System.out.println(context.getBean(ExchangeService.class));

		/* Distruzione del context di Spring */
		context.close();
		System.out.println("Context distrutto.");
	}

}
