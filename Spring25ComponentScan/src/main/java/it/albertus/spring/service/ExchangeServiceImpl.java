package it.albertus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.albertus.spring.dao.ExchangeDao;

@Service
public class ExchangeServiceImpl implements ExchangeService {

	@Autowired
	private ExchangeDao exchangeDao;

	public void setExchangeDao(ExchangeDao exchangeDao) {
		this.exchangeDao = exchangeDao;
	}

	@Override
	public String toString() {
		return "ExchangeServiceImpl [exchangeDao=" + exchangeDao + "]";
	}

}
