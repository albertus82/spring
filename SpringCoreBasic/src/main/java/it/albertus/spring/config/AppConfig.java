package it.albertus.spring.config;

import it.albertus.spring.dao.ExchangeDao;
import it.albertus.spring.dao.ExchangeDaoImpl;
import it.albertus.spring.service.ExchangeService;
import it.albertus.spring.service.ExchangeServiceAlternativeImpl;
import it.albertus.spring.service.ExchangeServiceImpl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "it.albertus.spring.service", "it.albertus.spring.dao", "it.albertus.spring.prototype" })
public class AppConfig implements BeanFactoryPostProcessor, BeanPostProcessor {

	@Bean
	public ExchangeDao exchgDao() {
		return new ExchangeDaoImpl();
	}

	@Bean(initMethod = "customInit", destroyMethod = "customDestroy")
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

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Post-processing before initialization bean " + beanName + ": " + bean.toString());
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Post-processing after initialization bean " + beanName + ": " + bean.toString());
		if (bean instanceof ExchangeServiceImpl) {
			ExchangeServiceImpl es = (ExchangeServiceImpl) bean;
			ExchangeServiceAlternativeImpl test = new ExchangeServiceAlternativeImpl();
			test.setExchangeDao(es.getExchangeDao());
			bean = test;
		}
		return bean;
	}

}
