package it.albertus.spring.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

public class BaseDAO {

	@Autowired
	protected NamedParameterJdbcOperations jdbcOperations;
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	protected EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
}