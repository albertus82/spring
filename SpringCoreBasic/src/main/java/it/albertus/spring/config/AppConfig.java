package it.albertus.spring.config;

import it.albertus.spring.dao.ExchangeDao;
import it.albertus.spring.dao.ExchangeDaoImpl;
import it.albertus.spring.service.ExchangeService;
import it.albertus.spring.service.ExchangeServiceImpl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "it.albertus.spring.service", "it.albertus.spring.dao" })
public class AppConfig implements BeanFactoryPostProcessor {

	@Bean
	public ExchangeDao exchgDao() {
		return new ExchangeDaoImpl();
	}

	@Bean
	public ExchangeService exchgSvc(@Qualifier("exchgDao") ExchangeDao dao) {
		ExchangeServiceImpl svc = new ExchangeServiceImpl();
		svc.setExchangeDao(dao);
		return svc;
	}

	public static String getBeanNames(ConfigurableApplicationContext context) {
		StringBuilder beanNames = new StringBuilder(System.getProperty("line.separator"));
		for (String beanName : context.getBeanDefinitionNames()) {
			beanNames.append(beanName).append(System.getProperty("line.separator"));
		}
		return ">>> Elenco bean <<<" + beanNames.toString() + ">>> Fine elenco bean <<<";
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println(getClass().getSimpleName() + ".postProcessBeanFactory");
	}

}
