package it.albertus.spring.dao;

import it.albertus.spring.model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(value = "transactionManager", propagation = Propagation.SUPPORTS)
public class UtenteDAOImpl extends BaseDAO implements UtenteDAO {

	private static final Log log = LogFactory.getLog(UtenteDAOImpl.class);

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
//		throw new IllegalArgumentException("ERRORE IMPREVISTO!");
	}

	@Override
	public Utente auth(final String username, final String password) {
		Utente utente;

		String query = "SELECT u.username, u.password, u.nome, u.cognome, u.data_nascita FROM utenti u WHERE u.username = :usn AND u.password = :pwd";
		try {
			MapSqlParameterSource paramMap = new MapSqlParameterSource();
			paramMap.addValue("usn", username, Types.VARCHAR);
			paramMap.addValue("pwd", password, Types.VARCHAR);
			utente = jdbcOperations.queryForObject(query, paramMap, new UtenteRowMapper());
		}
		catch (EmptyResultDataAccessException e) {
			log.info("Autenticazione fallita perche' l'utente non esiste oppure la password e' errata.");
			utente = null;
		}
		catch (Exception e) {
			log.error("Autenticazione fallita per altri motivi (database non disponibile, ecc.).\r\n" + ExceptionUtils.getStackTrace(e));
			utente = null;
		}

		return utente;
	}

	private class UtenteRowMapper implements RowMapper<Utente> {

		@Override
		public Utente mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utente utente = new Utente();
			utente.setUsername(rs.getString("username"));
			utente.setPassword(rs.getString("password"));
			utente.setNome(rs.getString("nome"));
			utente.setCognome(rs.getString("cognome"));
			utente.setDataNascita(rs.getDate("data_nascita"));
			return utente;
		}

	}

}