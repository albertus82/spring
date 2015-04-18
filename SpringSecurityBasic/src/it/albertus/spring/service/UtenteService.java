package it.albertus.spring.service;

import it.albertus.spring.model.Utente;

public interface UtenteService {

	void save(Utente model);
	
	Utente findById(String username);
	
}