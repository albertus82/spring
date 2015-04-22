package it.albertus.spring.service;

import it.albertus.spring.model.Utente;

public interface UtenteService {

	Utente autenticazione(String username, String password);
	
}