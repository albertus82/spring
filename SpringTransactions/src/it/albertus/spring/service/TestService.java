package it.albertus.spring.service;

public interface TestService {

	void insertJdbcOperations();

	void rollbackJdbcOperations();

	void rollbackJdbc();

	void insertJdbc();

}
