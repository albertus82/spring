package it.albertus.spring.dao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionTemplateDAOImpl extends BaseDAO implements TransactionTemplateDAO {

	private static final Log log = LogFactory.getLog(TransactionTemplateDAOImpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public boolean test(Long param) throws IllegalAccessException {
		Map<String, String> params = new HashMap<String, String>();
		Calendar sysdate = Calendar.getInstance();
		for (int i = 1; i <= 5; i++) {
			params.put("un", i + "-1-" + sysdate.getTimeInMillis());
			params.put("pw", i + "-2-" + sysdate.getTimeInMillis());
			params.put("nm", i + "-3-" + sysdate.getTimeInMillis());
			params.put("cg", i + "-4-" + sysdate.getTimeInMillis());
			jdbcOperations.update("INSERT INTO utenti (username, cognome, nome, password) VALUES (:un, :cg, :nm, :pw)", params);
			log.info("Insert eseguita con " + jdbcOperations.getClass().getSimpleName() + '.');
		}
//		throw new IllegalArgumentException("Test rollback!");
		throw new IllegalAccessException("Test rollback!");
//		return true;
	}

}
