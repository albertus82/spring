package it.albertus.spring.service;

import it.albertus.spring.model.Utente;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UtenteService extends UserDetailsService {

	void save(Utente model);
	
	Utente loadUserByUsername(String username);
	
}