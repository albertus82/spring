package it.albertus.spring;

import it.albertus.spring.service.AccountService;
import it.albertus.spring.service.CurrencyService;
import it.albertus.spring.service.ExchangeService;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring25ComponentScanExample {

	public static void main(String args[]) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "services.xml", "repositories.xml" });
		System.out.println("Context inizializzato.");

		System.out.println(getBeanNames(context));

		System.out.println(context.getBean("acctSvc", AccountService.class));
		System.out.println(context.getBean("currencyService", CurrencyService.class));
		System.out.println(context.getBean("exchangeServiceImpl", ExchangeService.class));

		/* Distruzione del context di Spring */
		context.close();
		System.out.println("Context distrutto.");
	}

	public static String getBeanNames(ConfigurableApplicationContext context) {
		StringBuilder beanNames = new StringBuilder(System.getProperty("line.separator"));
		for (String beanName : context.getBeanDefinitionNames()) {
			beanNames.append(beanName).append(System.getProperty("line.separator"));
		}
		return ">>> Elenco bean <<<" + beanNames.toString() + ">>> Fine elenco bean <<<";
	}

}
