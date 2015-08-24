package it.albertus.spring;

import it.albertus.spring.config.AppConfig;
import it.albertus.spring.prototype.TestPrototypeComponentService;
import it.albertus.spring.service.AccountService;
import it.albertus.spring.service.ExchangeService;
import it.albertus.spring.service.TestFactoryService;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCoreExample {

	public static void main(String args[]) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "repositories.xml");
//		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println("Context inizializzato.");

		System.out.println(AppConfig.getBeanNames(context));

		System.out.println(context.getBean("acctSvc", AccountService.class));
		System.out.println(context.getBean(ExchangeService.class));
		System.out.println(context.getBean(TestFactoryService.class));
		
		// Test iniezione bean prototype con proxy...
		System.out.println(context.getBean(TestPrototypeComponentService.class));
		System.out.println(context.getBean(TestPrototypeComponentService.class));
		System.out.println(context.getBean(TestPrototypeComponentService.class));

		/* Distruzione del context di Spring */
		context.close();
		System.out.println("Context distrutto.");
	}

}
