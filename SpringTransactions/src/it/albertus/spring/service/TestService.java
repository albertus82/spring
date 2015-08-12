package it.albertus.spring.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TestService {

	@Transactional(propagation = Propagation.SUPPORTS)
	void insertJdbcOperations();

	void rollbackJdbcOperations() throws Exception;

	void rollbackJdbc();

	void insertJdbc();

}
