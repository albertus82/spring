package it.albertus.spring.service;

import it.albertus.spring.dao.CurrencyDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("currencyService")
public class CurrencyServiceImpl implements CurrencyService {

	private CurrencyDao currencyDao;

	@Autowired
	public CurrencyServiceImpl(CurrencyDao currencyDao) {
		this.currencyDao = currencyDao;
	}

	@Override
	public String toString() {
		return "CurrencyServiceImpl [currencyDao=" + currencyDao + "]";
	}

}
