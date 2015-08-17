package it.albertus.spring.service;

import it.albertus.spring.dao.AccountDao;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public String toString() {
		return "AccountServiceImpl [accountDao=" + accountDao + "]";
	}

}
