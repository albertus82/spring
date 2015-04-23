package it.albertus.spring.dao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
// Non occorre specificare il nome del bean PlatformTransactionManager ("transactionManager") se ne esiste solo uno!
@Transactional(value = "transactionManager", propagation = Propagation.MANDATORY) // Bisogna arrivare qui con una transazione gia' attiva!
public class TestDAOImpl extends BaseDAO implements TestDAO {

	private static final Log log = LogFactory.getLog(TestDAOImpl.class);

	@Override
//	@Transactional(propagation = Propagation.MANDATORY)
	public void insert() {
		Map<String, String> params = new HashMap<String, String>();
		Calendar sysdate = Calendar.getInstance();
		for (int i = 1; i <= 5; i++) {
			params.put("un", i+"-1-"+sysdate.getTimeInMillis());
			params.put("pw", i+"-2-"+sysdate.getTimeInMillis());
			params.put("nm", i+"-3-"+sysdate.getTimeInMillis());
			params.put("cg", i+"-4-"+sysdate.getTimeInMillis());
			jdbcOperations.update("INSERT INTO utenti (username, cognome, nome, password) VALUES (:un, :cg, :nm, :pw)", params);
		}
//		throw new IllegalArgumentException();
			
	}

}