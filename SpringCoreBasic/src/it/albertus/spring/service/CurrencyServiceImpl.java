package it.albertus.spring.service;

import it.albertus.spring.dao.CurrencyDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyDao currencyDao;

	@Override
	public String toString() {
		return "CurrencyServiceImpl [currencyDao=" + currencyDao + "]";
	}

}
