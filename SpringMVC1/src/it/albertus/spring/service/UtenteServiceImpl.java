package it.albertus.spring.service;

import it.albertus.spring.dao.UtenteDAO;
import it.albertus.spring.model.Utente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	UtenteDAO utenteDao;
	
	@Override
	public void save(Utente model) {
		utenteDao.save(model);
	}

	@Override
	public Utente auth(String username, String password) {
		return utenteDao.auth(username, password);
	}

}