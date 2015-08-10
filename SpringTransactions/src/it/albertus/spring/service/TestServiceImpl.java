package it.albertus.spring.service;

import it.albertus.spring.dao.TestDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TestServiceImpl implements TestService {

	private static final Log log = LogFactory.getLog(TestServiceImpl.class);

	@Autowired
	private TestDAO testDao;

	@Override
//	@Transactional(propagation = Propagation.SUPPORTS)
	public void insertJdbcOperations() {
		testDao.insertJdbcOperations();
		log.info("Fine del metodo insert del service.");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // Non eredita nulla dall'annotation sulla classe!
	public void rollbackJdbcOperations() {
		insertJdbcOperations();
		log.info("Prima dell'eccezione nel service...");
		throw new IllegalStateException();
	}

	@Override
	public void insertJdbc() {
		testDao.insertJdbc();
		log.info("Fine del metodo insert del service.");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void rollbackJdbc() {
		insertJdbc();
		log.info("Prima dell'eccezione nel service...");
		throw new IllegalStateException();
	}

}
