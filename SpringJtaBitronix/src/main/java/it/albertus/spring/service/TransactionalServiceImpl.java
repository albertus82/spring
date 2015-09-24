package it.albertus.spring.service;

import it.albertus.spring.dao.TransactionalDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value="txManager", propagation = Propagation.REQUIRED)
public class TransactionalServiceImpl implements TransactionalService {

	private static final Log log = LogFactory.getLog(TransactionalServiceImpl.class);

	@Autowired
	private TransactionalDAO testDao;

	@Override
	public void insertJdbcOperations() {
		testDao.insertJdbcOperations();
		log.info("Fine del metodo insert del service.");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)//, rollbackFor = TestException.class) // Non eredita nulla dall'annotation sulla classe!
	public void rollbackJdbcOperations() throws Exception {
		insertJdbcOperations();
		log.info("Prima dell'eccezione nel service...");
		throw new IllegalStateException("Test rollback automatico!");
//		throw new TestException("Test rollback esplicito!");
	}

	@Override
	public void insertBasicJdbc() {
		testDao.insertJdbc();
		log.info("Fine del metodo insert del service.");
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
	public void rollbackBasicJdbc() {
		insertBasicJdbc();
		log.info("Prima dell'eccezione nel service...");
		throw new IllegalStateException("Test rollback!");
	}

}
