package it.albertus.spring.dao;

import it.albertus.spring.model.Utente;

import java.sql.Types;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
// Non occorre specificare il nome del bean PlatformTransactionManager ("transactionManager") se ne esiste solo uno!
@Transactional(value = "transactionManager", propagation = Propagation.SUPPORTS)
public class UtenteDAOImpl extends BaseDAO implements UtenteDAO {

	@Override
	@Transactional(propagation = Propagation.MANDATORY)	// Bisogna arrivare qui con una transazione gia' attiva!
	public void save(Utente model) {
		String insert = "INSERT INTO utenti (username, password, nome, cognome, data_nascita) VALUES (:usn, :pwd, :nm, :cgm, :dn)";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("usn", model.getUsername(), Types.VARCHAR);
		paramMap.addValue("pwd", model.getPassword(), Types.VARCHAR);
		paramMap.addValue("nm", model.getNome(), Types.VARCHAR);
		paramMap.addValue("cgm", model.getCognome(), Types.VARCHAR);
		paramMap.addValue("dn", model.getDataNascita(), Types.DATE);
		jdbcOperations.update(insert, paramMap);
	}

}