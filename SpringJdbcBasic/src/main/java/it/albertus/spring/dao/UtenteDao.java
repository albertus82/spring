package it.albertus.spring.dao;

import it.albertus.spring.model.Utente;

public interface UtenteDao {

	Utente auth(String usn, String pwd);

}