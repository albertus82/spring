package it.albertus.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
// Non occorre specificare il nome del bean PlatformTransactionManager ("transactionManager") se ne esiste solo uno!
@Transactional(value = "transactionManager", propagation = Propagation.MANDATORY) // Bisogna arrivare qui con una transazione gia' attiva!
public class TestDAOImpl extends BaseDAO implements TestDAO {

	private static final Log log = LogFactory.getLog(TestDAOImpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
//	@Transactional(propagation = Propagation.MANDATORY)
	public void insertJdbcOperations() {
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
//		throw new IllegalArgumentException();
	}

	@Override
	public void insertJdbc() {
		Map<String, String> params = new HashMap<String, String>();
		Calendar sysdate = Calendar.getInstance();
		Connection c = null;
		PreparedStatement s = null;
		try {
			// Si deve passare per DataSourceUtils, altrimenti la gestione delle transazioni di Spring non funziona!
			c = DataSourceUtils.getConnection(dataSource);
			for (int i = 1; i <= 5; i++) {
				params.put("un", i + "-1-" + sysdate.getTimeInMillis());
				params.put("pw", i + "-2-" + sysdate.getTimeInMillis());
				params.put("nm", i + "-3-" + sysdate.getTimeInMillis());
				params.put("cg", i + "-4-" + sysdate.getTimeInMillis());
				s = c.prepareStatement("INSERT INTO utenti (username, cognome, nome, password) " + "VALUES (?, ?, ?, ?)");
				s.setString(1, params.get("un"));
				s.setString(2, params.get("cg"));
				s.setString(3, params.get("nm"));
				s.setString(4, params.get("pw"));
				s.executeUpdate();
				log.info("Insert eseguita con JDBC tradizionale.");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
