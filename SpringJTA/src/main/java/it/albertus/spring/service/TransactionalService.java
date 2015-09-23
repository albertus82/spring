package it.albertus.spring.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionalService {

	@Transactional(propagation = Propagation.SUPPORTS)
	void insertJdbcOperations();

	void rollbackJdbcOperations() throws Exception;

	void rollbackBasicJdbc();

	void insertBasicJdbc();

}
