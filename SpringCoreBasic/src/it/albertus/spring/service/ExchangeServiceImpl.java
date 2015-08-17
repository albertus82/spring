package it.albertus.spring.service;

import it.albertus.spring.dao.ExchangeDao;

public class ExchangeServiceImpl implements ExchangeService {

	private ExchangeDao exchangeDao;

	public void setExchangeDao(ExchangeDao exchangeDao) {
		this.exchangeDao = exchangeDao;
	}

	@Override
	public String toString() {
		return "ExchangeServiceImpl [exchangeDao=" + exchangeDao + "]";
	}

}
