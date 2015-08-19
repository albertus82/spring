package it.albertus.spring.service;

import it.albertus.spring.dao.ExchangeDao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class ExchangeServiceImpl implements ExchangeService, InitializingBean, DisposableBean {

	private ExchangeDao exchangeDao;

	public ExchangeDao getExchangeDao() {
		return exchangeDao;
	}

	public void setExchangeDao(ExchangeDao exchangeDao) {
		this.exchangeDao = exchangeDao;
	}

	@Override
	public String toString() {
		return "ExchangeServiceImpl [exchangeDao=" + exchangeDao + "]";
	}

	/* Callback inizializzazione */
	@PostConstruct
	public void ilNomeDelMetodoNonContaNulla() {
		System.out.println(getClass().getSimpleName() + ": Callback inizializzazione 1");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(getClass().getSimpleName() + ": Callback inizializzazione 2");
	}

	public void customInit() {
		System.out.println(getClass().getSimpleName() + ": Callback inizializzazione 3");
	}

	/* Callback distruzione */
	@PreDestroy
	public void ancheQuestoNomeNonConta() {
		System.out.println(getClass().getSimpleName() + ": Callback distruzione 1");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println(getClass().getSimpleName() + ": Callback distruzione 2");
	}

	public void customDestroy() {
		System.out.println(getClass().getSimpleName() + ": Callback distruzione 3");
	}

}
