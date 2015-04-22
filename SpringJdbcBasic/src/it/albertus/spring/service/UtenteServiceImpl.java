package it.albertus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.albertus.spring.dao.UtenteDao;
import it.albertus.spring.model.Utente;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteDao utenteDao;

	@Override
	public Utente autenticazione(String usn, String pwd) {
		return utenteDao.auth(usn, pwd);
	}

}
