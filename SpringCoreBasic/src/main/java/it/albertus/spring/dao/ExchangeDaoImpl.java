package it.albertus.spring.dao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class ExchangeDaoImpl implements ExchangeDao {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println(getClass().getSimpleName() + ".postProcessBeanFactory");
	}

}
