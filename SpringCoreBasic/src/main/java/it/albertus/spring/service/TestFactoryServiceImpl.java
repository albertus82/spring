package it.albertus.spring.service;

import it.albertus.spring.dao.TestFactoryDao;

public class TestFactoryServiceImpl implements TestFactoryService {

	public static TestFactoryService getInstance(int number, String text, TestFactoryDao dao) {
		return new TestFactoryServiceImpl(number, text, dao);
	}

	private int number;
	private String text;
	private TestFactoryDao dao;

	private TestFactoryServiceImpl(int number, String text, TestFactoryDao dao) {
		this.number = number;
		this.text = text;
		this.dao = dao;
	}

	@Override
	public String toString() {
		return "TestFactoryServiceImpl [number=" + number + ", text=" + text + ", dao=" + dao + "]";
	}

}
