package it.albertus.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.albertus.spring.model.Utente;

@Repository
public class UtenteDaoImpl implements UtenteDao {

	private static final Log log = LogFactory.getLog(UtenteDaoImpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public Utente auth(String usn, String pwd) {
		Utente utente = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = dataSource.getConnection();
			ps = c.prepareStatement("SELECT cognome FROM utenti WHERE username = ? AND password = ?");
			ps.setString(1, usn);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			if (rs.next()) {
				utente = new Utente();
				utente.setUsername(usn);
				utente.setPassword(pwd);
				utente.setCognome(rs.getString(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				c.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (utente == null) {
			log.warn("Utente non trovato!");
		}
		else {
			log.info("Utente trovato!");
		}
		return utente;
	}

}
