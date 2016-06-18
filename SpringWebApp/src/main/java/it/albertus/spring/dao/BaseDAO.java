package it.albertus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

public abstract class BaseDAO {

	@Autowired
	protected NamedParameterJdbcOperations jdbcOperations;
	
}