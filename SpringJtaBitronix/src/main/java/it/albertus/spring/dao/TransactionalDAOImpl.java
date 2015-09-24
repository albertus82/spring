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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
/*
 * Non occorre specificare il nome del bean PlatformTransactionManager
 * ("transactionManager") se ne esiste solo uno!
 *
 * Bisogna arrivare qui con una transazione gia' attiva!
 */
@Transactional(value = "transactionManager", propagation = Propagation.MANDATORY)
public class TransactionalDAOImpl extends BaseDAO implements TransactionalDAO {

	private static final Log log = LogFactory.getLog(TransactionalDAOImpl.class);

	@Autowired
	@Qualifier("albAdminDataSource")
	private DataSource albAdminDataSource;

	@Autowired
	@Qualifier("hrDataSource")
	private DataSource hrDataSource;

	@Override
	// @Transactional(propagation = Propagation.MANDATORY)
	public void insertJdbcOperations() {
		Map<String, String> params = new HashMap<String, String>();
		Calendar sysdate = Calendar.getInstance();
		for (int i = 1; i <= 5; i++) {
			params.clear();

			// INSERT sullo schema ALB_ADMIN...
			params.put("un", i + "-1-JO-" + sysdate.getTimeInMillis());
			params.put("pw", i + "-2-JO-" + sysdate.getTimeInMillis());
			params.put("nm", i + "-3-JO-" + sysdate.getTimeInMillis());
			params.put("cg", i + "-4-JO-" + sysdate.getTimeInMillis());
			albAdminJdbcOperations.update("INSERT INTO utenti (username, cognome, nome, password) VALUES (:un, :cg, :nm, :pw)", params);
			log.info("Insert eseguita con " + albAdminJdbcOperations + '.');

			// INSERT sullo schema HR...
			params.put("txt", i + "-JO-" + sysdate.getTimeInMillis());
			hrJdbcOperations.update("INSERT INTO jta_bitronix_test (id, testo) VALUES (seq_jta_bitronix_test.NEXTVAL, :txt)", params);
			log.info("Insert eseguita con " + hrJdbcOperations + '.');

		}
		// throw new IllegalArgumentException();
	}

	@Override
	public void insertJdbc() {
		Map<String, String> params = new HashMap<String, String>();
		Calendar sysdate = Calendar.getInstance();
		Connection c = null;
		PreparedStatement s = null;
		try {
			/* Si deve passare per DataSourceUtils, altrimenti la gestione delle transazioni di Spring non funziona! */
			c = DataSourceUtils.getConnection(albAdminDataSource);
			for (int i = 1; i <= 5; i++) {
				params.put("un", i + "-1-JDBC-" + sysdate.getTimeInMillis());
				params.put("pw", i + "-2-JDBC-" + sysdate.getTimeInMillis());
				params.put("nm", i + "-3-JDBC-" + sysdate.getTimeInMillis());
				params.put("cg", i + "-4-JDBC-" + sysdate.getTimeInMillis());
				s = c.prepareStatement("INSERT INTO utenti (username, cognome, nome, password) " + "VALUES (?, ?, ?, ?)");
				s.setString(1, params.get("un"));
				s.setString(2, params.get("cg"));
				s.setString(3, params.get("nm"));
				s.setString(4, params.get("pw"));
				s.executeUpdate();
				log.info("Insert eseguita su " + albAdminDataSource + " con JDBC tradizionale.");
			}

			c = DataSourceUtils.getConnection(hrDataSource);
			for (int i = 1; i <= 5; i++) {
				params.put("txt", i + "-JDBC-" + sysdate.getTimeInMillis());
				s = c.prepareStatement("INSERT INTO jta_bitronix_test (id, testo) " + "VALUES (seq_jta_bitronix_test.NEXTVAL, ?)");
				s.setString(1, params.get("txt"));
				s.executeUpdate();
				log.info("Insert eseguita su " + hrDataSource + " con JDBC tradizionale.");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
