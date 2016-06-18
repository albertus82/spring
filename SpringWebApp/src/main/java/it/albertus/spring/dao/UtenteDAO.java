package it.albertus.spring.dao;

import it.albertus.spring.model.Utente;

public interface UtenteDAO {

	void save(Utente model);

	Utente auth(String username, String password);

}