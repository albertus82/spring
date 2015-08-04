package it.albertus.spring.web;

import it.albertus.spring.model.Utente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@ModelAttribute("lista") // Questo alias sara' usato nella JSP!
	private List<Utente> getUtentiPredefiniti() {
		List<Utente> utenti = new ArrayList<Utente>();
		
		Utente utente = new Utente();
		utente.setNome("Mario");
		utente.setCognome("Rossi");
		utenti.add(utente);
		
		utente = new Utente();
		utente.setNome("Paolo");
		utente.setCognome("Bianchi");
		utenti.add(utente);
		
		return utenti;
	}

	@RequestMapping(value = { "/home"}, method = RequestMethod.GET)
	public String home() {
		return "home";
	}
}