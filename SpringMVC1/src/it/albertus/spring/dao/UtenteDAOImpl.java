package it.albertus.spring.dao;

import it.albertus.spring.model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UtenteDAOImpl extends BaseDAO implements UtenteDAO {

	@Override
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
		catch (Exception e) {
			utente = null; // Autenticazione fallita (per qualsiasi motivo).
		}
		
		return utente;
	}
	
	private class UtenteRowMapper implements RowMapper<Utente> {

		@Override
		public Utente mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utente utente = new Utente();
			utente.setUsername( rs.getString("username"));
			utente.setPassword(rs.getString("password"));
			utente.setNome(rs.getString("nome"));
			utente.setCognome(rs.getString("cognome"));
			utente.setDataNascita(rs.getDate("data_nascita"));
			return utente;
		}
		
	}

}