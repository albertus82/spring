package it.albertus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

public abstract class BaseDAO {

	@Autowired
	@Qualifier("albAdminJdbcOperations")
	protected NamedParameterJdbcOperations albAdminJdbcOperations;

	@Autowired
	@Qualifier("hrJdbcOperations")
	protected NamedParameterJdbcOperations hrJdbcOperations;

}
